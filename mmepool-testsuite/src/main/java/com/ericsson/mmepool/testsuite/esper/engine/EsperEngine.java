package com.ericsson.mmepool.testsuite.esper.engine;

import com.ericsson.oss.itpf.sdk.resources.Resources;
import com.ericsson.xstream.apeventbeans.celltrace.INTERNAL_PROC_ERAB_RELEASE;
import com.ericsson.xstream.apeventbeans.celltrace_13a_ab3.INTERNAL_PROC_ERAB_SETUP;
import com.ericsson.xstream.base.apeventbeans.ApEventBean;
import com.espertech.esper.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: eeimho Date: 09/09/13 Time: 17:37 To change this template use File | Settings | File Templates.
 */
public class EsperEngine {
    private static final Logger logger = LoggerFactory.getLogger(EsperEngine.class);
    Map<Integer, INTERNAL_PROC_ERAB_RELEASE> releaseMap = new HashMap<Integer, INTERNAL_PROC_ERAB_RELEASE>();
    Map<Integer, INTERNAL_PROC_ERAB_SETUP> setUpMap = new HashMap<Integer, INTERNAL_PROC_ERAB_SETUP>();
    private EPServiceProvider serviceProvider = null;
    private EPRuntime epRuntime = null;
    private String epl = null;
    private Configuration configuration = null;

    public EsperEngine(String ruleFileName) {
        this.epl = ruleFileName;
    }

    private static InputStream getInputStream(String fileName) throws FileNotFoundException {
        InputStream inputStream = Resources.getClasspathResource(fileName).getInputStream();
        if (inputStream == null) {
            throw new FileNotFoundException("Could not find file " + fileName + " on the classpath");
        }
        return inputStream;
    }

    public void sendEvents(List<ApEventBean> events) {
        for (ApEventBean event : events) {
            //print(event);
            sendEvent(event);
        }
        System.out.println(events.size() + " has sent");

        for (final Map.Entry<Integer, INTERNAL_PROC_ERAB_SETUP> e : setUpMap.entrySet()) {
            INTERNAL_PROC_ERAB_SETUP setup = e.getValue();
            System.out.println(setup.getERAB_SETUP_SUCC_BITMAP());
        }

        for (final Map.Entry<Integer, INTERNAL_PROC_ERAB_RELEASE> e : releaseMap.entrySet()) {
            INTERNAL_PROC_ERAB_RELEASE release = e.getValue();
            System.out.println(release.getERAB_RELEASE_SUCC_BITMAP());
        }
    }

    public void sendEvent(ApEventBean event) {
        epRuntime.sendEvent(event);
    }

    public EventBean[] sendQuery(String query) {
        EPOnDemandQueryResult result = epRuntime.executeQuery(query);
        EventBean[] resultArray = result.getArray();
        return resultArray;
    }

    public void configureCEP(UpdateListener listener, String[] statements) throws Exception {
        configuration = new Configuration();
        configuration.configure("esper/test-esper-conf.xml");
        serviceProvider = EPServiceProviderManager.getProvider(this.epl, configuration);
        logger.info("Deploying epl : " + epl + " with steps , initialization, epl file deployment, statement attachment");
        deployCEP(listener, statements);
        logger.info("epl : " + epl + " is successfully deployed.");
    }

    private void deployCEP(UpdateListener listener, String[] statements) throws Exception {
        serviceProvider.initialize();
        InputStream inputFile = getInputStream(epl);
        serviceProvider.getEPAdministrator().getDeploymentAdmin().readDeploy(inputFile, null, null, null);
        if (listener != null && statements != null)
            listenToStatements(listener, statements);
        epRuntime = serviceProvider.getEPRuntime();
    }

    private void listenToStatements(UpdateListener listener, String[] statements) {
        for (String statement : statements) {
            addStatementAndListener(statement, listener);
        }
    }

    private void addStatementAndListener(String statement, UpdateListener updateListener) {
        EPAdministrator epAdministrator = serviceProvider.getEPAdministrator();
        EPStatement epStatement = epAdministrator.getStatement(statement);
        if (epStatement == null) {
            throw new RuntimeException("Could not find epl statement " + statement);
        }
        logger.info("Adding listener on statement " + statement);
        epStatement.addListener(updateListener);
    }

}
