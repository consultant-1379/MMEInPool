package com.ericsson.mmepool.e2e;

import com.ericsson.oss.itpf.sdk.resources.Resources;

import com.ericsson.oss.services.eps.EpsInstanceManager;
import com.ericsson.oss.services.eps.modules.ModuleManager;

import com.ericsson.mmepool.testsuite.eps.util.EpsTestUtil;
import com.ericsson.mmepool.testsuite.hazelcast.listener.HazelcastInputListener;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SimpleEsperFlowTest {

    private final EpsTestUtil epsTestUtil = new EpsTestUtil();
    private EpsInstanceManager epsInstanceManager;

    @Before
    public void setup() throws Exception {
        epsInstanceManager = epsTestUtil.createEpsInstanceInNewThread();
    }

    @After
    public void tearDown() {
        epsTestUtil.shutdownEpsInstance();
    }

    @Test
    public void test_deploy_simple_module() throws Exception {
        final InputStream moduleInputStream = Resources.getClasspathResource("/flows/simple_esper_flow.xml").getInputStream();
        Assert.assertNotNull(moduleInputStream);
        final HazelcastInputListener listener = new HazelcastInputListener(10);
        Assert.assertEquals(0, listener.getReceivedMessages().size());
        final HazelcastInstance hz = epsTestUtil.createHazelcastInstance();
        final ITopic hazelcastSendTopic = hz.getTopic("eps-input-topic");
        final ITopic hazelcastReceiveTopic = hz.getTopic("eps-output-topic");
        hazelcastReceiveTopic.addMessageListener(listener);
        epsTestUtil.deployModule(moduleInputStream);
        final ModuleManager moduleManager = epsInstanceManager.getModuleManager();
        Assert.assertEquals(1, moduleManager.getDeployedModulesCount());
        for (int i = 0; i < 10; i++) {
            final Map<String, String> evenMap = new HashMap<String, String>();
            evenMap.put("str", "even");
            hazelcastSendTopic.publish(evenMap);
            final Map<String, String> oddMap = new HashMap<String, String>();
            oddMap.put("str", "odd");
            hazelcastSendTopic.publish(oddMap);
        }
        Assert.assertTrue(listener.LATCH.await(1, TimeUnit.SECONDS));
        Assert.assertEquals(10, listener.getReceivedMessages().size());
        hz.getLifecycleService().shutdown();
        moduleManager.undeployAllModules();
        Assert.assertEquals(0, moduleManager.getDeployedModulesCount());
    }

    @Test
    public void test_deploy_module_with_esper_configuration() throws Exception {
        final InputStream moduleInputStream = Resources.getClasspathResource("/flows/esper_flow_with_configuration.xml").getInputStream();
        Assert.assertNotNull(moduleInputStream);
        final HazelcastInputListener listener = new HazelcastInputListener(10);
        Assert.assertEquals(0, listener.getReceivedMessages().size());
        final HazelcastInstance hz = epsTestUtil.createHazelcastInstance();
        final ITopic hazelcastSendTopic = hz.getTopic("eps-input-topic");
        final ITopic hazelcastReceiveTopic = hz.getTopic("eps-output-topic");
        hazelcastReceiveTopic.addMessageListener(listener);
        epsTestUtil.deployModule(moduleInputStream);
        final ModuleManager moduleManager = epsInstanceManager.getModuleManager();
        Assert.assertEquals(1, moduleManager.getDeployedModulesCount());
        for (int i = 0; i < 10; i++) {
            final Map<String, String> evenMap = new HashMap<String, String>();
            evenMap.put("str", "even");
            hazelcastSendTopic.publish(evenMap);
            final Map<String, String> oddMap = new HashMap<String, String>();
            oddMap.put("str", "odd");
            hazelcastSendTopic.publish(oddMap);
        }
        Assert.assertTrue(listener.LATCH.await(1, TimeUnit.SECONDS));
        Assert.assertEquals(10, listener.getReceivedMessages().size());
        hz.getLifecycleService().shutdown();
        moduleManager.undeployAllModules();
        Assert.assertEquals(0, moduleManager.getDeployedModulesCount());
    }

}
