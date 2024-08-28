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
package com.ericsson.mmepool.testsuite.eps.util;

import com.ericsson.oss.services.eps.EpsInstanceManager;
import com.ericsson.oss.services.eps.core.util.EpsProvider;
import com.ericsson.oss.services.eps.modules.ModuleManager;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.io.InputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EpsTestUtil {

    private final EpsProvider provider = EpsProvider.getInstance();
    private ExecutorService execService = Executors.newSingleThreadExecutor();
    private EpsInstanceManager epsInstanceManager;

    public EpsInstanceManager createEpsInstanceInNewThread() throws Exception {

        // Added for tests that share EpsTestUtil instances through inheritance,
        // like S1 and X2 correlations.
        if (execService.isShutdown()) {
            execService = Executors.newSingleThreadExecutor();
        }

        final Future<EpsInstanceManager> epsFuture = execService.submit(new Callable<EpsInstanceManager>() {

            @Override
            public EpsInstanceManager call() throws Exception {
                epsInstanceManager = EpsInstanceManager.getInstance();
                epsInstanceManager.start();
                return epsInstanceManager;
            }

        });

        return epsFuture.get();
    }

    public EpsInstanceManager getEpsInstanceManager() {
        return epsInstanceManager;
    }

    public void shutdownEpsInstance() {
        EpsInstanceManager.getInstance().stop();
        execService.shutdownNow();
        provider.clean();
    }

    public HazelcastInstance createHazelcastInstance() {
        final Config cfg = new Config();
        return Hazelcast.newHazelcastInstance(cfg);
    }

    public String deployModule(final InputStream is) {
        if (epsInstanceManager == null) {
            throw new IllegalStateException("EPS not started!");
        }
        final ModuleManager manager = epsInstanceManager.getModuleManager();
        return manager.deployModule(is);
    }

}
