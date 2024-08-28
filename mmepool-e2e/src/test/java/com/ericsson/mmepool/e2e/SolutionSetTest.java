package com.ericsson.mmepool.e2e;

import com.ericsson.oss.itpf.sdk.resources.Resources;

import com.ericsson.oss.services.eps.modules.ModuleManager;

import static com.ericsson.mmepool.testsuite.eventGen.EventsCreator.*;
import com.ericsson.mmepool.testsuite.eps.util.EpsTestUtil;
import com.ericsson.mmepool.testsuite.hazelcast.listener.HazelcastInputListener;
import com.ericsson.xstream.apeventbeans.celltrace.INTERNAL_PROC_INITIAL_CTXT_SETUP;
import com.ericsson.xstream.apeventbeans.celltrace.INTERNAL_PROC_RRC_CONN_SETUP;
import com.ericsson.xstream.apeventbeans.celltrace.INTERNAL_PROC_S1_SIG_CONN_SETUP;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.services.eps.EpsInstanceManager;

import java.io.InputStream;

public class SolutionSetTest {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final EpsTestUtil epsTestUtil = new EpsTestUtil();
    private EpsInstanceManager epsInstanceManager;

    @Before
    public void setup() throws Exception {
        epsInstanceManager = epsTestUtil.createEpsInstanceInNewThread();
    }

    @After
    public void tearDown() {
        epsTestUtil.shutdownEpsInstance();
        epsInstanceManager = null;
    }

    @Test
    public void test_deployment() throws Exception {
        final InputStream moduleInputStream = Resources.getClasspathResource("/flows/mmepool_call_setup.xml").getInputStream();
        Assert.assertNotNull(moduleInputStream);
        final HazelcastInputListener listener = new HazelcastInputListener(1);

        Assert.assertEquals(0, listener.getReceivedMessages().size());

        final HazelcastInstance hz = epsTestUtil.createHazelcastInstance();
        final ITopic hazelcastSendTopic = hz.getTopic("call_setup_topic_input");
        final ITopic hazelcastReceiveTopic = hz.getTopic("call_setup_topic_output");

        hazelcastReceiveTopic.addMessageListener(listener);
        final String id = epsTestUtil.deployModule(moduleInputStream);
        Assert.assertNotNull(id);
        final ModuleManager moduleManager = epsInstanceManager.getModuleManager();
        Assert.assertEquals(1, moduleManager.getDeployedModulesCount());

        hazelcastReceiveTopic.removeMessageListener(listener);
        moduleManager.undeployAllModules();
        Assert.assertEquals(0, moduleManager.getDeployedModulesCount());
        hz.getLifecycleService().shutdown();

    }

    @Test
    public void test_solutionSet() throws Exception {
        final InputStream moduleInputStream = Resources.getClasspathResource("/flows/mmepool_call_setup.xml").getInputStream();
        Assert.assertNotNull(moduleInputStream);
        final HazelcastInputListener listener = new HazelcastInputListener(1);
        Assert.assertEquals(0, listener.getReceivedMessages().size());

        final HazelcastInstance hz = epsTestUtil.createHazelcastInstance();
        final ITopic hazelcastSendTopic = hz.getTopic("call_setup_topic_input");
        final ITopic hazelcastReceiveTopic = hz.getTopic("call_setup_topic_output");

        hazelcastReceiveTopic.addMessageListener(listener);
        final String id = epsTestUtil.deployModule(moduleInputStream);
        Assert.assertNotNull(id);
        final ModuleManager moduleManager = epsInstanceManager.getModuleManager();
        Assert.assertEquals(1, moduleManager.getDeployedModulesCount());

        final INTERNAL_PROC_RRC_CONN_SETUP a = createRrcConnSetup(1l, 1l, SUCCESS);
        a.setTimestamp(1);
        final INTERNAL_PROC_S1_SIG_CONN_SETUP b = createS1SigConnSetup(1l, 1l, SUCCESS);
        b.setTimestamp(2);
        final byte[] erabSetupResult = new byte[] { 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        final INTERNAL_PROC_INITIAL_CTXT_SETUP c = createInitialCtxtSetup(1l, 1l, SUCCESS, erabSetupResult);
        c.setTimestamp(3);

        hazelcastSendTopic.publish(a);
        hazelcastSendTopic.publish(b);
        hazelcastSendTopic.publish(c);

        listener.LATCH.await();

        //        Assert.assertFalse(listener.LATCH.await(2, TimeUnit.SECONDS));
        Assert.assertEquals(1, listener.getReceivedMessages().size());

        hazelcastReceiveTopic.removeMessageListener(listener);
        moduleManager.undeployAllModules();
        Assert.assertEquals(0, moduleManager.getDeployedModulesCount());
        hz.getLifecycleService().shutdown();

    }

}