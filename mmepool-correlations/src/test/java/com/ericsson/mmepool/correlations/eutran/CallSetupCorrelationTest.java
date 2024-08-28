package com.ericsson.mmepool.correlations.eutran;



import static org.junit.Assert.*;
import static com.ericsson.mmepool.testsuite.eventGen.EventsCreator.*;
import static com.ericsson.mmepool.testsuite.util.DateFormatter.*;
import com.ericsson.mmepool.correlations.esper.listener.beans.eutran.session.LTE_EUTRAN_SESSION_CALL_SETUP;

import com.ericsson.mmepool.testsuite.esper.engine.EsperEngine;
import com.ericsson.xstream.apeventbeans.celltrace.*;

import com.ericsson.xstream.base.apeventbeans.ApEventBean;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;

public class CallSetupCorrelationTest {
    private static final Logger logger = LoggerFactory.getLogger(CallSetupCorrelationTest.class);
    public static List<LTE_EUTRAN_SESSION_CALL_SETUP> callSetupEventList = new ArrayList<LTE_EUTRAN_SESSION_CALL_SETUP>();
    private static String eplName = "LTE_EUTRAN_SESSION_CALL_SETUP_CORRELATION.epl";
    private static String timeZone = "UTC";
    private static String[] statementArray = { "CALL_SETUP", "FAILED_RRC_CONNECTION", "FAILED_S1_SIG_CONNECTION", "FAILED_INITIAL_CTXT_SETUP" };
    private final InstantListener sessionListener = new InstantListener();
    private EsperEngine cepHandler = null;

    @Before
    public void setUp() throws Exception {
        setTimeZone(TimeZone.getTimeZone(timeZone));
        cepHandler = new EsperEngine(eplName);
        cepHandler.configureCEP(sessionListener, statementArray);
        callSetupEventList.clear();
    }

    @Test
    public void shouldOutputOneCorrelatedEventWhenInputSuccessfulABC() throws Exception {

        final INTERNAL_PROC_RRC_CONN_SETUP a = createRrcConnSetup(1l, 1l, SUCCESS);
        a.setTimestamp(1);
        final INTERNAL_PROC_S1_SIG_CONN_SETUP b = createS1SigConnSetup(1l, 1l, SUCCESS);
        b.setTimestamp(2);
        final byte[] erabSetupResult = new byte[] { 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        final INTERNAL_PROC_INITIAL_CTXT_SETUP c = createInitialCtxtSetup(1l, 1l, SUCCESS, erabSetupResult);
        c.setTimestamp(3);
        cepHandler.sendEvent(a);
        cepHandler.sendEvent(b);
        cepHandler.sendEvent(c);

        assertEquals(1, callSetupEventList.size());
        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent = callSetupEventList.get(0);
        assertEquals(3, outputEvent.getTimestamp());
        verifyRrcConnSetupFields(outputEvent, 1l, SUCCESS);
        verifyS1SigConnSetupFields(outputEvent, 1l, SUCCESS);
        verifyInitialCtxtSetupFields(outputEvent, 1l, SUCCESS, erabSetupResult);

    }

    @Test
    public void shouldOutputOneCorrelatedEventWhenInputSuccessfulBAC() throws Exception {

        final INTERNAL_PROC_S1_SIG_CONN_SETUP b = createS1SigConnSetup(1l, 1l, SUCCESS);
        b.setTimestamp(1);
        final INTERNAL_PROC_RRC_CONN_SETUP a = createRrcConnSetup(1l, 1l, SUCCESS);
        a.setTimestamp(2);
        final byte[] erabSetupResult = new byte[] { 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        final INTERNAL_PROC_INITIAL_CTXT_SETUP c = createInitialCtxtSetup(1l, 1l, SUCCESS, erabSetupResult);
        c.setTimestamp(3);
        cepHandler.sendEvent(b);
        cepHandler.sendEvent(a);
        cepHandler.sendEvent(c);

        assertEquals(1, callSetupEventList.size());
        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent = callSetupEventList.get(0);
        assertEquals(3, outputEvent.getTimestamp());
        verifyRrcConnSetupFields(outputEvent, 1l, SUCCESS);
        verifyS1SigConnSetupFields(outputEvent, 1l, SUCCESS);
        verifyInitialCtxtSetupFields(outputEvent, 1l, SUCCESS, erabSetupResult);
    }

    @Test
    public void shouldOutputOneCorrelatedEventWhenInputSuccessfulAC() throws Exception {

        final INTERNAL_PROC_RRC_CONN_SETUP a = createRrcConnSetup(1l, 1l, SUCCESS);
        a.setTimestamp(1);
        final byte[] erabSetupResult = new byte[] { 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        final INTERNAL_PROC_INITIAL_CTXT_SETUP c = createInitialCtxtSetup(1l, 1l, SUCCESS, erabSetupResult);
        c.setTimestamp(2);
        cepHandler.sendEvent(a);
        cepHandler.sendEvent(c);

        assertEquals(1, callSetupEventList.size());
        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent = callSetupEventList.get(0);
        assertEquals(2, outputEvent.getTimestamp());
        verifyRrcConnSetupFields(outputEvent, 1l, SUCCESS);
        assertEquals(DEFAULT_VALUE, outputEvent.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        verifyInitialCtxtSetupFields(outputEvent, 1l, SUCCESS, erabSetupResult);
    }

    @Test
    public void shouldOutputOneCorrelatedEventWhenInputSuccessfulBC() throws Exception {

        final INTERNAL_PROC_S1_SIG_CONN_SETUP b = createS1SigConnSetup(1l, 1l, SUCCESS);
        b.setTimestamp(1);
        final byte[] erabSetupResult = new byte[] { 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        final INTERNAL_PROC_INITIAL_CTXT_SETUP c = createInitialCtxtSetup(1l, 1l, SUCCESS, erabSetupResult);
        c.setTimestamp(2);
        cepHandler.sendEvent(b);
        cepHandler.sendEvent(c);

        assertEquals(1, callSetupEventList.size());
        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent = callSetupEventList.get(0);
        assertEquals(2, outputEvent.getTimestamp());
        assertEquals(DEFAULT_VALUE, outputEvent.getRRC_CONN_SETUP_RRC_RESULT());
        verifyS1SigConnSetupFields(outputEvent, 1l, SUCCESS);
        verifyInitialCtxtSetupFields(outputEvent, 1l, SUCCESS, erabSetupResult);
    }

    @Test
    public void shouldOutputCOnlyWhenInputSuccessfulC() throws Exception {

        final byte[] erabSetupResult = new byte[] { 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        final INTERNAL_PROC_INITIAL_CTXT_SETUP c = createInitialCtxtSetup(1l, 1l, SUCCESS, erabSetupResult);
        cepHandler.sendEvent(c);

        assertEquals(1, callSetupEventList.size());
        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent = callSetupEventList.get(0);
        assertEquals(DEFAULT_VALUE, outputEvent.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals(DEFAULT_VALUE, outputEvent.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        verifyInitialCtxtSetupFields(outputEvent, 1l, SUCCESS, erabSetupResult);
    }

    @Test
    public void shouldOutputNothingWhenInputSuccessfulAB() throws Exception {

        final INTERNAL_PROC_RRC_CONN_SETUP a = createRrcConnSetup(1l, 1l, SUCCESS);
        a.setTimestamp(1);
        final INTERNAL_PROC_S1_SIG_CONN_SETUP b = createS1SigConnSetup(1l, 1l, SUCCESS);
        b.setTimestamp(2);
        cepHandler.sendEvent(a);
        cepHandler.sendEvent(b);

        assertEquals(0, callSetupEventList.size());
    }

    @Test
    public void shouldOutputCOnlyWhenInputSuccessfulCBA() throws Exception {

        final byte[] erabSetupResult = new byte[] { 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        final INTERNAL_PROC_INITIAL_CTXT_SETUP c = createInitialCtxtSetup(1l, 1l, SUCCESS, erabSetupResult);
        c.setTimestamp(1);
        final INTERNAL_PROC_S1_SIG_CONN_SETUP b = createS1SigConnSetup(1l, 1l, SUCCESS);
        b.setTimestamp(2);
        final INTERNAL_PROC_RRC_CONN_SETUP a = createRrcConnSetup(1l, 1l, SUCCESS);
        a.setTimestamp(3);

        cepHandler.sendEvent(c);
        cepHandler.sendEvent(b);
        cepHandler.sendEvent(a);

        assertEquals(1, callSetupEventList.size());
        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent = callSetupEventList.get(0);
        assertEquals(1, outputEvent.getTimestamp());
        verifyInitialCtxtSetupFields(outputEvent, 1l, SUCCESS, erabSetupResult);
        assertEquals(DEFAULT_VALUE, outputEvent.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        assertEquals(DEFAULT_VALUE, outputEvent.getRRC_CONN_SETUP_RRC_RESULT());
    }

    @Test
    public void shouldCorrelateA2WithCWhenInputAAC() throws Exception {
        final INTERNAL_PROC_RRC_CONN_SETUP a1 = createRrcConnSetup(1l, 1l, SUCCESS);
        a1.setTimestamp(1);
        a1.setRRC_ESTABL_CAUSE((byte) 1);
        final INTERNAL_PROC_RRC_CONN_SETUP a2 = createRrcConnSetup(1l, 1l, SUCCESS);
        a2.setTimestamp(2);
        a2.setRRC_ESTABL_CAUSE((byte) 2);
        final byte[] erabSetupResult = new byte[] { 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        final INTERNAL_PROC_INITIAL_CTXT_SETUP c = createInitialCtxtSetup(1l, 1l, SUCCESS, erabSetupResult);
        c.setTimestamp(3);

        cepHandler.sendEvent(a1);
        cepHandler.sendEvent(a2);
        cepHandler.sendEvent(c);

        assertEquals(1, callSetupEventList.size());
        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent = callSetupEventList.get(0);
        assertEquals(3, outputEvent.getTimestamp());
        assertEquals(SUCCESS, outputEvent.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals((byte) 2, outputEvent.getRRC_CONN_SETUP_RRC_ESTABL_CAUSE());
        verifyInitialCtxtSetupFields(outputEvent, 1l, SUCCESS, erabSetupResult);
        assertEquals((byte) 2, outputEvent.getRRC_CONN_SETUP_RRC_ESTABL_CAUSE());
        assertEquals(DEFAULT_VALUE, outputEvent.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
    }

    @Test
    public void shouldCorrelateB2WithCWhenInputBBC() throws Exception {
        final INTERNAL_PROC_S1_SIG_CONN_SETUP b1 = createS1SigConnSetup(1l, 1l, SUCCESS);
        b1.setTimestamp(1);
        b1.setRRC_ESTABL_CAUSE((byte) 1);
        final INTERNAL_PROC_S1_SIG_CONN_SETUP b2 = createS1SigConnSetup(1l, 1l, SUCCESS);
        b2.setTimestamp(2);
        b2.setRRC_ESTABL_CAUSE((byte) 2);
        final byte[] erabSetupResult = new byte[] { 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        final INTERNAL_PROC_INITIAL_CTXT_SETUP c = createInitialCtxtSetup(1l, 1l, SUCCESS, erabSetupResult);
        c.setTimestamp(3);

        cepHandler.sendEvent(b1);
        cepHandler.sendEvent(b2);
        cepHandler.sendEvent(c);

        assertEquals(1, callSetupEventList.size());
        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent = callSetupEventList.get(0);
        assertEquals(3, outputEvent.getTimestamp());
        verifyInitialCtxtSetupFields(outputEvent, 1l, SUCCESS, erabSetupResult);
        assertEquals(SUCCESS, outputEvent.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        assertEquals((byte) 2, outputEvent.getS1_SIG_CONN_SETUP_RRC_ESTABL_CAUSE());
        assertEquals(DEFAULT_VALUE, outputEvent.getRRC_CONN_SETUP_RRC_RESULT());
    }

    @Test
    public void shouldOutputTwoEventsCorrelateA2WithC1AndA2WithC2WhenInputAACC() throws Exception {
        final INTERNAL_PROC_RRC_CONN_SETUP a1 = createRrcConnSetup(1l, 1l, SUCCESS);
        a1.setTimestamp(1);
        a1.setRRC_ESTABL_CAUSE((byte) 1);
        final INTERNAL_PROC_RRC_CONN_SETUP a2 = createRrcConnSetup(1l, 1l, SUCCESS);
        a2.setTimestamp(2);
        a2.setRRC_ESTABL_CAUSE((byte) 2);
        final byte[] erabSetupResult = new byte[] { 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        final INTERNAL_PROC_INITIAL_CTXT_SETUP c1 = createInitialCtxtSetup(1l, 1l, SUCCESS, erabSetupResult);
        c1.setTimestamp(3);
        c1.setSERVING_PLMN_ID(new byte[] { 1 });
        final INTERNAL_PROC_INITIAL_CTXT_SETUP c2 = createInitialCtxtSetup(1l, 1l, SUCCESS, erabSetupResult);
        c2.setTimestamp(4);
        c2.setSERVING_PLMN_ID(new byte[] { 2 });

        cepHandler.sendEvent(a1);
        cepHandler.sendEvent(a2);
        cepHandler.sendEvent(c1);
        cepHandler.sendEvent(c2);

        assertEquals(2, callSetupEventList.size());
        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent1 = callSetupEventList.get(0);
        assertEquals(3, outputEvent1.getTimestamp());
        assertEquals(SUCCESS, outputEvent1.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());
        assertArrayEquals(new byte[] { 1 }, outputEvent1.getINITIAL_CTXT_SETUP_SERVING_PLMN_ID());
        assertEquals(SUCCESS, outputEvent1.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals((byte) 2, outputEvent1.getRRC_CONN_SETUP_RRC_ESTABL_CAUSE());
        assertEquals(DEFAULT_VALUE, outputEvent1.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());

        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent2 = callSetupEventList.get(1);
        assertEquals(4, outputEvent2.getTimestamp());
        assertEquals(SUCCESS, outputEvent2.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());
        assertArrayEquals(new byte[] { 2 }, outputEvent2.getINITIAL_CTXT_SETUP_SERVING_PLMN_ID());
        assertEquals(SUCCESS, outputEvent2.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals((byte) 2, outputEvent2.getRRC_CONN_SETUP_RRC_ESTABL_CAUSE());
        assertEquals(DEFAULT_VALUE, outputEvent2.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
    }

    @Test
    public void shouldOutputTwoEventsCorrelateB2WithC1AndB2WithC2WhenInputBBCC() throws Exception {
        final INTERNAL_PROC_S1_SIG_CONN_SETUP b1 = createS1SigConnSetup(1l, 1l, SUCCESS);
        b1.setTimestamp(1);
        b1.setRRC_ESTABL_CAUSE((byte) 1);
        final INTERNAL_PROC_S1_SIG_CONN_SETUP b2 = createS1SigConnSetup(1l, 1l, SUCCESS);
        b2.setTimestamp(2);
        b2.setRRC_ESTABL_CAUSE((byte) 2);
        final byte[] erabSetupResult = new byte[] { 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        final INTERNAL_PROC_INITIAL_CTXT_SETUP c1 = createInitialCtxtSetup(1l, 1l, SUCCESS, erabSetupResult);
        c1.setTimestamp(3);
        c1.setSERVING_PLMN_ID(new byte[] { 1 });
        final INTERNAL_PROC_INITIAL_CTXT_SETUP c2 = createInitialCtxtSetup(1l, 1l, SUCCESS, erabSetupResult);
        c2.setTimestamp(4);
        c2.setSERVING_PLMN_ID(new byte[] { 2 });

        cepHandler.sendEvent(b1);
        cepHandler.sendEvent(b2);
        cepHandler.sendEvent(c1);
        cepHandler.sendEvent(c2);

        assertEquals(2, callSetupEventList.size());
        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent1 = callSetupEventList.get(0);
        assertEquals(3, outputEvent1.getTimestamp());
        assertEquals(SUCCESS, outputEvent1.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());
        assertArrayEquals(new byte[] { 1 }, outputEvent1.getINITIAL_CTXT_SETUP_SERVING_PLMN_ID());
        assertEquals(SUCCESS, outputEvent1.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        assertEquals((byte) 2, outputEvent1.getS1_SIG_CONN_SETUP_RRC_ESTABL_CAUSE());
        assertEquals(DEFAULT_VALUE, outputEvent1.getRRC_CONN_SETUP_RRC_RESULT());

        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent2 = callSetupEventList.get(1);
        assertEquals(4, outputEvent2.getTimestamp());
        assertEquals(SUCCESS, outputEvent2.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());
        assertArrayEquals(new byte[] { 2 }, outputEvent2.getINITIAL_CTXT_SETUP_SERVING_PLMN_ID());
        assertEquals(SUCCESS, outputEvent2.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        assertEquals((byte) 2, outputEvent2.getS1_SIG_CONN_SETUP_RRC_ESTABL_CAUSE());
        assertEquals(DEFAULT_VALUE, outputEvent2.getRRC_CONN_SETUP_RRC_RESULT());
    }

    @Test
    public void shouldOutputFailedCWhenInputSuccessfulABFailedC() throws Exception {

        final INTERNAL_PROC_RRC_CONN_SETUP a = createRrcConnSetup(1l, 1l, SUCCESS);
        a.setTimestamp(1);
        final INTERNAL_PROC_S1_SIG_CONN_SETUP b = createS1SigConnSetup(1l, 1l, SUCCESS);
        b.setTimestamp(2);
        final byte[] erabSetupResult = new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        final INTERNAL_PROC_INITIAL_CTXT_SETUP failedC = createInitialCtxtSetup(1l, 1l, FAILURE, erabSetupResult);
        failedC.setTimestamp(3);
        cepHandler.sendEvent(a);
        cepHandler.sendEvent(b);
        cepHandler.sendEvent(failedC);

        assertEquals(1, callSetupEventList.size());
        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent = callSetupEventList.get(0);
        assertEquals(3, outputEvent.getTimestamp());
        assertEquals(DEFAULT_VALUE, outputEvent.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals(DEFAULT_VALUE, outputEvent.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        assertEquals(FAILURE, outputEvent.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());
    }

    @Test
    public void shouldCorrelateBWithCWhenInputFailedASuccessfulBC() throws Exception {

        final INTERNAL_PROC_RRC_CONN_SETUP failedA = createRrcConnSetup(1l, 1l, FAILURE);
        failedA.setTimestamp(1);
        final INTERNAL_PROC_S1_SIG_CONN_SETUP b = createS1SigConnSetup(1l, 1l, SUCCESS);
        b.setTimestamp(2);
        final byte[] erabSetupResult = new byte[] { 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        final INTERNAL_PROC_INITIAL_CTXT_SETUP c = createInitialCtxtSetup(1l, 1l, SUCCESS, erabSetupResult);
        c.setTimestamp(3);
        cepHandler.sendEvent(failedA);
        cepHandler.sendEvent(b);
        cepHandler.sendEvent(c);

        assertEquals(2, callSetupEventList.size());
        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent1 = callSetupEventList.get(0);
        assertEquals(1, outputEvent1.getTimestamp());
        assertEquals(FAILURE, outputEvent1.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals(DEFAULT_VALUE, outputEvent1.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        assertEquals(DEFAULT_VALUE, outputEvent1.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());
        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent2 = callSetupEventList.get(1);
        assertEquals(3, outputEvent2.getTimestamp());
        assertEquals(DEFAULT_VALUE, outputEvent2.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals(SUCCESS, outputEvent2.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        assertEquals(SUCCESS, outputEvent2.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());
    }

    @Test
    public void shouldCorrelateAWithCWhenInputSuccessfulAFailedBSuccessfulC() throws Exception {

        final INTERNAL_PROC_RRC_CONN_SETUP a = createRrcConnSetup(1l, 1l, SUCCESS);
        a.setTimestamp(1);
        final INTERNAL_PROC_S1_SIG_CONN_SETUP failedB = createS1SigConnSetup(1l, 1l, FAILURE);
        failedB.setTimestamp(2);
        final byte[] erabSetupResult = new byte[] { 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        final INTERNAL_PROC_INITIAL_CTXT_SETUP c = createInitialCtxtSetup(1l, 1l, SUCCESS, erabSetupResult);
        c.setTimestamp(3);
        cepHandler.sendEvent(a);
        cepHandler.sendEvent(failedB);
        cepHandler.sendEvent(c);

        assertEquals(2, callSetupEventList.size());
        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent1 = callSetupEventList.get(0);
        assertEquals(2, outputEvent1.getTimestamp());
        assertEquals(DEFAULT_VALUE, outputEvent1.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals(FAILURE, outputEvent1.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        assertEquals(DEFAULT_VALUE, outputEvent1.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());
        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent2 = callSetupEventList.get(1);
        assertEquals(3, outputEvent2.getTimestamp());
        assertEquals(SUCCESS, outputEvent2.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals(DEFAULT_VALUE, outputEvent2.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        assertEquals(SUCCESS, outputEvent2.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());
    }

    @Test
    public void shouldOutputThreeEventsWhenInputFailedABSuccessfulC() throws Exception {

        final INTERNAL_PROC_RRC_CONN_SETUP failedA = createRrcConnSetup(1l, 1l, FAILURE);
        failedA.setTimestamp(1);
        final INTERNAL_PROC_S1_SIG_CONN_SETUP failedB = createS1SigConnSetup(1l, 1l, FAILURE);
        failedB.setTimestamp(2);
        final byte[] erabSetupResult = new byte[] { 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        final INTERNAL_PROC_INITIAL_CTXT_SETUP c = createInitialCtxtSetup(1l, 1l, SUCCESS, erabSetupResult);
        c.setTimestamp(3);
        cepHandler.sendEvent(failedA);
        cepHandler.sendEvent(failedB);
        cepHandler.sendEvent(c);

        assertEquals(3, callSetupEventList.size());
        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent1 = callSetupEventList.get(0);
        assertEquals(1, outputEvent1.getTimestamp());
        assertEquals(FAILURE, outputEvent1.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals(DEFAULT_VALUE, outputEvent1.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        assertEquals(DEFAULT_VALUE, outputEvent1.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());
        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent2 = callSetupEventList.get(1);
        assertEquals(2, outputEvent2.getTimestamp());
        assertEquals(DEFAULT_VALUE, outputEvent2.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals(FAILURE, outputEvent2.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        assertEquals(DEFAULT_VALUE, outputEvent2.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());
        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent3 = callSetupEventList.get(2);
        assertEquals(3, outputEvent3.getTimestamp());
        assertEquals(DEFAULT_VALUE, outputEvent3.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals(DEFAULT_VALUE, outputEvent3.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        assertEquals(SUCCESS, outputEvent3.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());
    }

    @Test
    public void shouldOutputOneCorrelatedEventsWhenInputFailedASuccessfulASuccessfulC() throws Exception {

        final INTERNAL_PROC_RRC_CONN_SETUP failedA = createRrcConnSetup(1l, 1l, FAILURE);
        failedA.setTimestamp(1);
        failedA.setRRC_ESTABL_CAUSE((byte) 1);
        final INTERNAL_PROC_RRC_CONN_SETUP successfulA = createRrcConnSetup(1l, 1l, SUCCESS);
        successfulA.setTimestamp(2);
        successfulA.setRRC_ESTABL_CAUSE((byte) 2);
        final byte[] erabSetupResult = new byte[] { 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        final INTERNAL_PROC_INITIAL_CTXT_SETUP successfulC = createInitialCtxtSetup(1l, 1l, SUCCESS, erabSetupResult);
        successfulC.setTimestamp(3);

        cepHandler.sendEvent(failedA);
        cepHandler.sendEvent(successfulA);
        cepHandler.sendEvent(successfulC);

        assertEquals(2, callSetupEventList.size());
        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent1 = callSetupEventList.get(0);
        assertEquals(1, outputEvent1.getTimestamp());
        assertEquals(FAILURE, outputEvent1.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals(DEFAULT_VALUE, outputEvent1.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        assertEquals(DEFAULT_VALUE, outputEvent1.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());
        assertEquals((byte) 1, outputEvent1.getRRC_CONN_SETUP_RRC_ESTABL_CAUSE());
        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent2 = callSetupEventList.get(1);
        assertEquals(3, outputEvent2.getTimestamp());
        assertEquals(SUCCESS, outputEvent2.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals(DEFAULT_VALUE, outputEvent2.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        assertEquals(SUCCESS, outputEvent2.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());
        assertEquals((byte) 2, outputEvent2.getRRC_CONN_SETUP_RRC_ESTABL_CAUSE());
    }

    @Test
    public void shouldOutputOneCorrelatedEventsWhenInputFailedBSuccessfulBSuccessfulC() throws Exception {

        final INTERNAL_PROC_S1_SIG_CONN_SETUP failedB = createS1SigConnSetup(1l, 1l, FAILURE);
        failedB.setTimestamp(1);
        failedB.setRRC_ESTABL_CAUSE((byte) 1);
        final INTERNAL_PROC_S1_SIG_CONN_SETUP successfulB = createS1SigConnSetup(1l, 1l, SUCCESS);
        successfulB.setTimestamp(2);
        successfulB.setRRC_ESTABL_CAUSE((byte) 2);
        final byte[] erabSetupResult = new byte[] { 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        final INTERNAL_PROC_INITIAL_CTXT_SETUP successfulC = createInitialCtxtSetup(1l, 1l, SUCCESS, erabSetupResult);
        successfulC.setTimestamp(3);

        cepHandler.sendEvent(failedB);
        cepHandler.sendEvent(successfulB);
        cepHandler.sendEvent(successfulC);

        assertEquals(2, callSetupEventList.size());
        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent1 = callSetupEventList.get(0);
        assertEquals(1, outputEvent1.getTimestamp());
        assertEquals(DEFAULT_VALUE, outputEvent1.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals(FAILURE, outputEvent1.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        assertEquals(DEFAULT_VALUE, outputEvent1.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());
        assertEquals((byte) 1, outputEvent1.getS1_SIG_CONN_SETUP_RRC_ESTABL_CAUSE());
        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent2 = callSetupEventList.get(1);
        assertEquals(3, outputEvent2.getTimestamp());
        assertEquals(DEFAULT_VALUE, outputEvent2.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals(SUCCESS, outputEvent2.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        assertEquals(SUCCESS, outputEvent2.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());
        assertEquals((byte) 2, outputEvent2.getS1_SIG_CONN_SETUP_RRC_ESTABL_CAUSE());
    }

    /*
     * Scenario: the input events sequence is: (in format "<Timestamp>:<Event Type><Key Values>") 0:B2 1:C4 2:A9- 3:A6 4:A3 5:B2 6:A5 7:A11+ 8:A1 9:B3 10:C3 11:C1 12:C2 13:B5 14:A11- 15:A8 16:C11+
     * 17:B4 18:A10+ 19:B9- 20:A4 21:B7 22:B8 23:A8 24:C9+ 25:C10- 26:C8 27:B7 (A: INTERNAL_PROC_RRC_CONN_SETUP event; B: INTERNAL_PROC_S1_SIG_CONN_SETUP event; C: INTERNAL_PROC_INITIAL_CTXT_SETUP
     * event; "+" means successful event and "-" means failed event)
     * 
     * Eleven events should be output as below (triggered when event C (1:C4, 10:C3, 11:C1, 12:C2, 16:C11+, 24:C9+, 26:C8) or failed event occurs): [1] 1:C4; [2] 2:A9-; [3] 10:C3 with 4:A3 and 9:B3;
     * [4] 11:C1 with 8:A1; [5] 12:C2 with 5:B2; [6] 14:A11-; [7] 16:C11+ with A11+; [8] 19:B9-; [9] 24:C9+; [10] 25:C10-; [11] 26:C8 with 22:B8 and 23:A8;
     */
    @Test
    public void shouldOutputElevenEventsInThisComplexScenario() throws Exception {
        final byte[] successfulErabSetupResult = new byte[] { 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        final byte[] unsuccessfulErabSetupResult = new byte[] { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        final ApEventBean[] events = new ApEventBean[28];
        events[0] = createS1SigConnSetup(360l, 2l, SUCCESS);
        final INTERNAL_PROC_S1_SIG_CONN_SETUP b2_1 = (INTERNAL_PROC_S1_SIG_CONN_SETUP) events[0];
        b2_1.setRRC_ESTABL_CAUSE((byte) 1);
        events[1] = createInitialCtxtSetup(4l, 1989l, SUCCESS, successfulErabSetupResult);
        events[2] = createRrcConnSetup(2012l, 9l, FAILURE);
        events[3] = createRrcConnSetup(6l, 1l, SUCCESS);
        events[4] = createRrcConnSetup(3l, 2013l, SUCCESS);
        events[5] = createS1SigConnSetup(360l, 2l, SUCCESS);
        final INTERNAL_PROC_S1_SIG_CONN_SETUP b2_2 = (INTERNAL_PROC_S1_SIG_CONN_SETUP) events[5];
        b2_2.setRRC_ESTABL_CAUSE((byte) 2);
        events[6] = createRrcConnSetup(1l, 5l, SUCCESS);
        events[7] = createRrcConnSetup(11l, 1l, SUCCESS);
        events[8] = createRrcConnSetup(1l, 1l, SUCCESS);
        events[9] = createS1SigConnSetup(3l, 2013l, SUCCESS);
        events[10] = createInitialCtxtSetup(3l, 2013l, SUCCESS, successfulErabSetupResult);
        events[11] = createInitialCtxtSetup(1l, 1l, SUCCESS, successfulErabSetupResult);
        events[12] = createInitialCtxtSetup(360l, 2l, SUCCESS, successfulErabSetupResult);
        events[13] = createS1SigConnSetup(1l, 5l, SUCCESS);
        events[14] = createRrcConnSetup(11l, 1l, FAILURE);
        events[15] = createRrcConnSetup(1l, 8l, SUCCESS);
        final INTERNAL_PROC_RRC_CONN_SETUP a8_1 = (INTERNAL_PROC_RRC_CONN_SETUP) events[15];
        a8_1.setRRC_ESTABL_CAUSE((byte) 1);
        events[16] = createInitialCtxtSetup(11l, 1l, SUCCESS, successfulErabSetupResult);
        events[17] = createS1SigConnSetup(4l, 1989l, SUCCESS);
        events[18] = createRrcConnSetup(1l, 10l, SUCCESS);
        events[19] = createS1SigConnSetup(2012l, 9l, FAILURE);
        events[20] = createRrcConnSetup(4l, 1989l, SUCCESS);
        events[21] = createS1SigConnSetup(7l, 7l, SUCCESS);
        events[22] = createS1SigConnSetup(1l, 8l, SUCCESS);
        events[23] = createRrcConnSetup(1l, 8l, SUCCESS);
        final INTERNAL_PROC_RRC_CONN_SETUP a8_2 = (INTERNAL_PROC_RRC_CONN_SETUP) events[23];
        a8_2.setRRC_ESTABL_CAUSE((byte) 2);
        events[24] = createInitialCtxtSetup(2012l, 9l, SUCCESS, successfulErabSetupResult);
        events[25] = createInitialCtxtSetup(1l, 10l, FAILURE, unsuccessfulErabSetupResult);
        events[26] = createInitialCtxtSetup(1l, 8l, SUCCESS, successfulErabSetupResult);
        events[27] = createS1SigConnSetup(7l, 7l, SUCCESS);

        for (int i = 0; i < events.length; i++) {
            events[i].setTimestamp(i);
            cepHandler.sendEvent(events[i]);
        }

        assertEquals(11, callSetupEventList.size());

        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent1 = callSetupEventList.get(0);
        assertEquals(1, outputEvent1.getTimestamp());
        assertEquals(DEFAULT_VALUE, outputEvent1.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals(DEFAULT_VALUE, outputEvent1.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        assertEquals(SUCCESS, outputEvent1.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());

        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent2 = callSetupEventList.get(1);
        assertEquals(2, outputEvent2.getTimestamp());
        assertEquals(FAILURE, outputEvent2.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals(DEFAULT_VALUE, outputEvent2.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        assertEquals(DEFAULT_VALUE, outputEvent2.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());

        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent3 = callSetupEventList.get(2);
        assertEquals(10, outputEvent3.getTimestamp());
        assertEquals(SUCCESS, outputEvent3.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals(SUCCESS, outputEvent3.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        assertEquals(SUCCESS, outputEvent3.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());

        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent4 = callSetupEventList.get(3);
        assertEquals(11, outputEvent4.getTimestamp());
        assertEquals(SUCCESS, outputEvent4.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals(DEFAULT_VALUE, outputEvent4.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        assertEquals(SUCCESS, outputEvent4.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());

        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent5 = callSetupEventList.get(4);
        assertEquals(12, outputEvent5.getTimestamp());
        assertEquals(DEFAULT_VALUE, outputEvent5.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals(SUCCESS, outputEvent5.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        assertEquals((byte) 2, outputEvent5.getS1_SIG_CONN_SETUP_RRC_ESTABL_CAUSE());
        assertEquals(SUCCESS, outputEvent5.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());

        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent6 = callSetupEventList.get(5);
        assertEquals(14, outputEvent6.getTimestamp());
        assertEquals(FAILURE, outputEvent6.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals(DEFAULT_VALUE, outputEvent6.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        assertEquals(DEFAULT_VALUE, outputEvent6.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());

        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent7 = callSetupEventList.get(6);
        assertEquals(16, outputEvent7.getTimestamp());
        assertEquals(SUCCESS, outputEvent7.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals(DEFAULT_VALUE, outputEvent7.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        assertEquals(SUCCESS, outputEvent7.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());

        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent8 = callSetupEventList.get(7);
        assertEquals(19, outputEvent8.getTimestamp());
        assertEquals(DEFAULT_VALUE, outputEvent8.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals(FAILURE, outputEvent8.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        assertEquals(DEFAULT_VALUE, outputEvent8.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());

        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent9 = callSetupEventList.get(8);
        assertEquals(24, outputEvent9.getTimestamp());
        assertEquals(DEFAULT_VALUE, outputEvent9.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals(DEFAULT_VALUE, outputEvent9.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        assertEquals(SUCCESS, outputEvent9.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());

        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent10 = callSetupEventList.get(9);
        assertEquals(25, outputEvent10.getTimestamp());
        assertEquals(DEFAULT_VALUE, outputEvent10.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals(DEFAULT_VALUE, outputEvent10.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        assertEquals(FAILURE, outputEvent10.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());

        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent11 = callSetupEventList.get(10);
        assertEquals(26, outputEvent11.getTimestamp());
        assertEquals(SUCCESS, outputEvent11.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals((byte) 2, outputEvent11.getRRC_CONN_SETUP_RRC_ESTABL_CAUSE());
        assertEquals(SUCCESS, outputEvent11.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        assertEquals(SUCCESS, outputEvent11.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());
    }

    @Test
    public void shouldOutputCorrectStarttimeAndDurationWhenCorrelationOccurs() throws Exception {

        final Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        final long stoptime = cal.getTimeInMillis();

        cal.add(Calendar.MILLISECOND, -60);
        cal.add(Calendar.SECOND, -9);
        final byte startHour = (byte) cal.get(Calendar.HOUR_OF_DAY);
        final byte startMinute = (byte) cal.get(Calendar.MINUTE);
        final byte startSecond = (byte) cal.get(Calendar.SECOND);
        final int startMillisecond = cal.get(Calendar.MILLISECOND);
        final long starttime = cal.getTimeInMillis();

        final INTERNAL_PROC_RRC_CONN_SETUP a = createRrcConnSetup(1l, 1l, SUCCESS);
        a.setTIMESTAMP_START_HOUR(startHour);
        a.setTIMESTAMP_START_MINUTE(startMinute);
        a.setTIMESTAMP_START_SECOND(startSecond);
        a.setTIMESTAMP_START_MILLISEC(startMillisecond);
        a.setTimestamp(stoptime);
        final INTERNAL_PROC_S1_SIG_CONN_SETUP b = createS1SigConnSetup(1l, 1l, SUCCESS);
        b.setTIMESTAMP_START_HOUR(startHour);
        b.setTIMESTAMP_START_MINUTE(startMinute);
        b.setTIMESTAMP_START_SECOND(startSecond);
        b.setTIMESTAMP_START_MILLISEC(startMillisecond);
        b.setTimestamp(stoptime);
        final byte[] erabSetupResult = new byte[] { 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        final INTERNAL_PROC_INITIAL_CTXT_SETUP c = createInitialCtxtSetup(1l, 1l, SUCCESS, erabSetupResult);
        c.setTIMESTAMP_START_HOUR(startHour);
        c.setTIMESTAMP_START_MINUTE(startMinute);
        c.setTIMESTAMP_START_SECOND(startSecond);
        c.setTIMESTAMP_START_MILLISEC(startMillisecond);
        c.setTimestamp(stoptime);
        cepHandler.sendEvent(a);
        cepHandler.sendEvent(b);
        cepHandler.sendEvent(c);

        assertEquals(1, callSetupEventList.size());
        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent = callSetupEventList.get(0);
        assertEquals(starttime, outputEvent.getRRC_CONN_SETUP_TIMESTAMP_START());
        assertEquals(9060l, outputEvent.getRRC_CONN_SETUP_DURATION());
        assertEquals(starttime, outputEvent.getS1_SIG_CONN_SETUP_TIMESTAMP_START());
        assertEquals(9060l, outputEvent.getS1_SIG_CONN_SETUP_DURATION());
        assertEquals(starttime, outputEvent.getINITIAL_CTXT_SETUP_TIMESTAMP_START());
        assertEquals(9060l, outputEvent.getINITIAL_CTXT_SETUP_DURATION());
    }

    @Test
    public void shouldOutputCorrectStarttimeAndDurationWhenCorrelationOccursAcrossTwoDays() throws Exception {

        final int startYear = 2013;
        final int startMonth = 3;
        final int startDate = 1;
        final byte startHour = 23;
        final byte startMinute = 59;
        final byte startSecond = 58;
        final int startMillisec = 120;
        final int stopYear = 2013;
        final int stopMonth = 3;
        final int stopDate = 2;
        final byte stopHour = 0;
        final byte stopMinute = 0;
        final byte stopSecond = 1;
        final int stopMillisec = 280;
        final int duration = 3160;

        final Calendar startCal = Calendar.getInstance();
        startCal.setTimeZone(TimeZone.getTimeZone("UTC"));
        startCal.set(startYear, startMonth, startDate, startHour, startMinute, startSecond);
        startCal.set(Calendar.MILLISECOND, startMillisec);
        final long starttime = startCal.getTimeInMillis();
        final Calendar stopCal = Calendar.getInstance();
        stopCal.setTimeZone(TimeZone.getTimeZone("UTC"));
        stopCal.set(stopYear, stopMonth, stopDate, stopHour, stopMinute, stopSecond);
        stopCal.set(Calendar.MILLISECOND, stopMillisec);
        final long stoptime = stopCal.getTimeInMillis();

        final INTERNAL_PROC_RRC_CONN_SETUP a = createRrcConnSetup(1l, 1l, SUCCESS);
        a.setTIMESTAMP_START_HOUR(startHour);
        a.setTIMESTAMP_START_MINUTE(startMinute);
        a.setTIMESTAMP_START_SECOND(startSecond);
        a.setTIMESTAMP_START_MILLISEC(startMillisec);
        a.setTimestamp(stoptime);
        final INTERNAL_PROC_S1_SIG_CONN_SETUP b = createS1SigConnSetup(1l, 1l, SUCCESS);
        b.setTIMESTAMP_START_HOUR(startHour);
        b.setTIMESTAMP_START_MINUTE(startMinute);
        b.setTIMESTAMP_START_SECOND(startSecond);
        b.setTIMESTAMP_START_MILLISEC(startMillisec);
        b.setTimestamp(stoptime);
        final byte[] erabSetupResult = new byte[] { 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        final INTERNAL_PROC_INITIAL_CTXT_SETUP c = createInitialCtxtSetup(1l, 1l, SUCCESS, erabSetupResult);
        c.setTIMESTAMP_START_HOUR(startHour);
        c.setTIMESTAMP_START_MINUTE(startMinute);
        c.setTIMESTAMP_START_SECOND(startSecond);
        c.setTIMESTAMP_START_MILLISEC(startMillisec);
        c.setTimestamp(stoptime);
        cepHandler.sendEvent(a);
        cepHandler.sendEvent(b);
        cepHandler.sendEvent(c);

        assertEquals(1, callSetupEventList.size());
        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent = callSetupEventList.get(0);
        assertEquals(starttime, outputEvent.getRRC_CONN_SETUP_TIMESTAMP_START());
        assertEquals(duration, outputEvent.getRRC_CONN_SETUP_DURATION());
        assertEquals(starttime, outputEvent.getS1_SIG_CONN_SETUP_TIMESTAMP_START());
        assertEquals(duration, outputEvent.getS1_SIG_CONN_SETUP_DURATION());
        assertEquals(starttime, outputEvent.getINITIAL_CTXT_SETUP_TIMESTAMP_START());
        assertEquals(duration, outputEvent.getINITIAL_CTXT_SETUP_DURATION());
    }

    @Test
    public void shouldOutputFailedRRCConnectionSetupEvent() throws InterruptedException {
        final INTERNAL_PROC_RRC_CONN_SETUP rrc = createRrcConnSetup(1l, 1l, FAILURE);
        cepHandler.sendEvent(rrc);
        assertEquals(1, callSetupEventList.size());
        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent = callSetupEventList.get(0);
        verifyRrcConnSetupFields(outputEvent, 1l, FAILURE);
        assertEquals(DEFAULT_VALUE, outputEvent.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        assertEquals(DEFAULT_VALUE, outputEvent.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());
    }

    @Test
    public void shouldOutputFailedS1SigConnectionSetupEvent() throws InterruptedException {
        final INTERNAL_PROC_S1_SIG_CONN_SETUP s1 = createS1SigConnSetup(1l, 1l, FAILURE);
        cepHandler.sendEvent(s1);
        assertEquals(1, callSetupEventList.size());
        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent = callSetupEventList.get(0);
        verifyS1SigConnSetupFields(outputEvent, 1l, FAILURE);
        assertEquals(DEFAULT_VALUE, outputEvent.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals(DEFAULT_VALUE, outputEvent.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());
    }

    @Test
    public void shouldOutputFailedInitialCtxtSetupEventWhenResultIsNotZeroAndNoSuccessErabExist() throws InterruptedException {
        final byte[] erabSetupResult = new byte[] { 1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        final INTERNAL_PROC_INITIAL_CTXT_SETUP ctxt = createInitialCtxtSetup(1l, 1l, FAILURE, erabSetupResult);
        cepHandler.sendEvent(ctxt);
        assertEquals(1, callSetupEventList.size());
        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent = callSetupEventList.get(0);
        verifyInitialCtxtSetupFields(outputEvent, 1l, FAILURE, erabSetupResult);
        assertEquals(DEFAULT_VALUE, outputEvent.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals(DEFAULT_VALUE, outputEvent.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
    }

    @Test
    public void shouldNotOuputInitialCtxtSetupEventWhenResultIsNotZeroAndSuccessErabsExist() {
        final INTERNAL_PROC_INITIAL_CTXT_SETUP ctxt = new INTERNAL_PROC_INITIAL_CTXT_SETUP();
        ctxt.setINITIAL_CTXT_RESULT((byte) 1);
        ctxt.setEVENT_ARRAY_ERAB_SETUP_RESULT_3((byte) 0);
        ctxt.setEVENT_ARRAY_ERAB_SETUP_RESULT_7((byte) 2);
        cepHandler.sendEvent(ctxt);
        assertEquals(0, callSetupEventList.size());
    }

    @Test
    public void shouldOuputSuccessfulInitialCtxtSetupEventWhenResultIsZeroAndNoSuccessErabExist() {
        final INTERNAL_PROC_INITIAL_CTXT_SETUP ctxt = new INTERNAL_PROC_INITIAL_CTXT_SETUP();
        ctxt.setINITIAL_CTXT_RESULT((byte) 0);
        ctxt.setEVENT_ARRAY_ERAB_SETUP_RESULT_3((byte) 1);
        ctxt.setEVENT_ARRAY_ERAB_SETUP_RESULT_7((byte) 2);
        cepHandler.sendEvent(ctxt);
        assertEquals(1, callSetupEventList.size());
        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent = callSetupEventList.get(0);
    }

    @Test
    public void shouldOuputSuccessfulInitialCtxtSetupEventWhenResultIsZeroAndSuccessErabsExist() {
        final INTERNAL_PROC_INITIAL_CTXT_SETUP ctxt = new INTERNAL_PROC_INITIAL_CTXT_SETUP();
        ctxt.setINITIAL_CTXT_RESULT((byte) 0);
        ctxt.setEVENT_ARRAY_ERAB_SETUP_RESULT_3((byte) 1);
        ctxt.setEVENT_ARRAY_ERAB_SETUP_RESULT_7((byte) 0);
        cepHandler.sendEvent(ctxt);
        assertEquals(1, callSetupEventList.size());
        final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent = callSetupEventList.get(0);
    }

    private void verifyRrcConnSetupFields(final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent, final long globalCellId, final byte eventResult) {
        assertEquals(RRC_INITIAL_UE_IDENTITY_TYPE, outputEvent.getRRC_CONN_SETUP_INITIAL_UE_IDENTITY_TYPE());
        assertEquals(RRC_INITIAL_UE_IDENTITY, outputEvent.getRRC_CONN_SETUP_INITIAL_UE_IDENTITY());
        assertEquals(RRC_RRC_ESTABL_CAUSE, outputEvent.getRRC_CONN_SETUP_RRC_ESTABL_CAUSE());
        assertEquals(eventResult, outputEvent.getRRC_CONN_SETUP_RRC_RESULT());
        assertEquals(globalCellId, outputEvent.getRRC_CONN_SETUP_GLOBAL_CELL_ID());
    }

    private void verifyS1SigConnSetupFields(final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent, final long globalCellId, final byte eventResult) {
        assertEquals(eventResult, outputEvent.getS1_SIG_CONN_SETUP_SIG_CONN_RESULT());
        assertEquals(globalCellId, outputEvent.getS1_SIG_CONN_SETUP_GLOBAL_CELL_ID());
        assertEquals(S1_RRC_ESTABL_CAUSE, outputEvent.getS1_SIG_CONN_SETUP_RRC_ESTABL_CAUSE());
    }

    private void verifyInitialCtxtSetupFields(final LTE_EUTRAN_SESSION_CALL_SETUP outputEvent, final long globalCellId, final byte eventResult, final byte[] erabSetupResult) {
        assertEquals(eventResult, outputEvent.getINITIAL_CTXT_SETUP_INITIAL_CTXT_RESULT());
        assertEquals(globalCellId, outputEvent.getINITIAL_CTXT_SETUP_GLOBAL_CELL_ID());
        assertArrayEquals(erabSetupResult, outputEvent.getINITIAL_CTXT_SETUP_ERAB_RESULT_ARRAY());
        assertArrayEquals(IC_EVENT_ARRAY_ERAB_SETUP_REQ_QCI, outputEvent.getINITIAL_CTXT_SETUP_ERAB_QCI_ARRAY());
        assertArrayEquals(IC_EVENT_ARRAY_ERAB_SETUP_REQ_ARP, outputEvent.getINITIAL_CTXT_SETUP_ERAB_ARP_ARRAY());
        assertArrayEquals(IC_EVENT_ARRAY_ERAB_SETUP_REQ_PCI, outputEvent.getINITIAL_CTXT_SETUP_ERAB_REQ_PCI_ARRAY());
        assertArrayEquals(IC_EVENT_ARRAY_ERAB_SETUP_REQ_PVI, outputEvent.getINITIAL_CTXT_SETUP_ERAB_REQ_PVI_ARRAY());
        assertArrayEquals(IC_EVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE, outputEvent.getINITIAL_CTXT_SETUP_ERAB_REQ_ATT_ACC_TYPE_ARRAY());
        assertArrayEquals(IC_EVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE, outputEvent.getINITIAL_CTXT_SETUP_ERAB_SUCC_ATT_ACC_TYPE_ARRAY());
        assertArrayEquals(IC_EVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP, outputEvent.getINITIAL_CTXT_SETUP_ERAB_FAILURE_3GPP_CAUSE_GROUP_ARRAY());
        assertArrayEquals(IC_EVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE, outputEvent.getINITIAL_CTXT_SETUP_ERAB_FAILURE_3GPP_CAUSE_ARRAY());
        assertEquals(IC_SERVING_PLMN_ID, outputEvent.getINITIAL_CTXT_SETUP_SERVING_PLMN_ID());
        assertEquals(IC_P_3GPP_CAUSE_GROUP, outputEvent.getINITIAL_CTXT_SETUP_3GPP_CAUSE_GROUP());
        assertEquals(IC_P_3GPP_CAUSE, outputEvent.getINITIAL_CTXT_SETUP_3GPP_CAUSE());
    }

    public class InstantListener implements UpdateListener {
        @Override
        public void update(final EventBean[] newEvents, final EventBean[] oldEvents) {
            if (newEvents != null) {
                logger.info("# of correlated events received : " + newEvents.length);
                int callSetupEventCount = 0;
                for (final EventBean eventBean : newEvents) {
                    if (eventBean.getUnderlying() instanceof LTE_EUTRAN_SESSION_CALL_SETUP) {
                        final LTE_EUTRAN_SESSION_CALL_SETUP callSetupEvent = (LTE_EUTRAN_SESSION_CALL_SETUP) eventBean.getUnderlying();
                        callSetupEventList.add(callSetupEvent);
                        callSetupEventCount++;
                    }
                }
                logger.info("# of CALL SETUP events received : " + callSetupEventCount + " [Total: " + callSetupEventList.size() + "]");
            }
        }
    }
}
