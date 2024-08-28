/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.mmepool.e2e;

import com.ericsson.oss.itpf.sdk.resources.Resources;
import com.ericsson.oss.services.eps.EpsInstanceManager;

import com.ericsson.oss.services.eps.core.module.assembler.impl.ComponentContextHandler;
import com.ericsson.oss.services.eps.modules.ModuleManager;


import com.ericsson.mmepool.handler.control.ControlEventSenderComponent;
import com.ericsson.mmepool.handler.generic.GenericComponent;
import com.ericsson.mmepool.testsuite.eps.util.EpsTestUtil;
import com.ericsson.mmepool.testsuite.hazelcast.listener.HazelcastInputListener;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class SimpleJseStartTest {

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
    public void test_deploy_simple_module() throws Exception {

        final InputStream moduleInputStream = Resources.getClasspathResource("/flows/simple_input_output_flow.xml").getInputStream();
        Assert.assertNotNull(moduleInputStream);
        final HazelcastInputListener listener = new HazelcastInputListener(5);
        Assert.assertEquals(0, listener.getReceivedMessages().size());

        final HazelcastInstance hz = epsTestUtil.createHazelcastInstance();
        final ITopic hazelcastSendTopic = hz.getTopic("eps-topic1");
        final ITopic hazelcastReceiveTopic = hz.getTopic("eps-topic2");

        hazelcastReceiveTopic.addMessageListener(listener);
        final String id = epsTestUtil.deployModule(moduleInputStream);
        Assert.assertNotNull(id);
        final ModuleManager moduleManager = epsInstanceManager.getModuleManager();
        Assert.assertEquals(1, moduleManager.getDeployedModulesCount());


        ComponentContextHandler componentContextHandler = ComponentContextHandler.getInstance();




        hazelcastSendTopic.publish("test1");
        log.debug("Sent event");
        Assert.assertTrue(listener.LATCH.await(1, TimeUnit.SECONDS));
        Assert.assertEquals(5, listener.getReceivedMessages().size());

        hazelcastReceiveTopic.removeMessageListener(listener);
        moduleManager.undeployAllModules();
        Assert.assertEquals(0, moduleManager.getDeployedModulesCount());
        Assert.assertTrue(GenericComponent.isDestroyed());
        Assert.assertTrue(GenericComponent.isInitialized());
        hz.getLifecycleService().shutdown();
    }
    @Test
    public void test_control_event() throws Exception {
        final InputStream moduleInputStream = Resources.getClasspathResource("/flows/simple_input_output_flow.xml").getInputStream();
        Assert.assertNotNull(moduleInputStream);
        final HazelcastInputListener listener = new HazelcastInputListener(5);
        Assert.assertEquals(0, listener.getReceivedMessages().size());

        final HazelcastInstance hz = epsTestUtil.createHazelcastInstance();
        final ITopic hazelcastSendTopic = hz.getTopic("eps-topic1");
        final ITopic hazelcastReceiveTopic = hz.getTopic("eps-topic2");

        hazelcastReceiveTopic.addMessageListener(listener);
        final String id = epsTestUtil.deployModule(moduleInputStream);
        Assert.assertNotNull(id);
        final ModuleManager moduleManager = epsInstanceManager.getModuleManager();
        Assert.assertEquals(1, moduleManager.getDeployedModulesCount());

        hazelcastSendTopic.publish("test1");
        log.debug("Sent event");
        Assert.assertTrue(listener.LATCH.await(1, TimeUnit.SECONDS));
        Assert.assertEquals(5, listener.getReceivedMessages().size());
        log.debug("Verified that all events were received");
        // now send control event and see that nothing passes through
        listener.clear(1);
        Assert.assertTrue(listener.getReceivedMessages().isEmpty());
        Assert.assertFalse(listener.LATCH.await(1, TimeUnit.SECONDS));
        log.debug("Verified that everything is clear - sending control event");
        hazelcastSendTopic.publish(ControlEventSenderComponent.SEND_CONTROL_EVENT_TRIGGER);
        log.debug("Sent control event - now sending regular events which should not be received");
        Thread.sleep(2000);
        // now everything should be paused... try to send few events
        hazelcastSendTopic.publish("test_no_receive1");
        hazelcastSendTopic.publish("test_no_receive2");
        hazelcastSendTopic.publish("test_no_receive3");
        Assert.assertFalse(listener.LATCH.await(1, TimeUnit.SECONDS));
        Assert.assertEquals(0, listener.getReceivedMessages().size());
        hz.getLifecycleService().shutdown();
    }

}