package com.ericsson.mmepool.correlations.esper.plugin;

import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;

import com.ericsson.xstream.apeventbeans.celltrace.*;
import com.ericsson.xstream.apeventbeans.ebm.L_ATTACH;

public class CorrelationRowFunctions {

    public static long formatInternalProcInitialCtxtSetupStartTime(final INTERNAL_PROC_INITIAL_CTXT_SETUP event) {
        if (event != null) {
            return formatStartTime(event.getTIMESTAMP_START_HOUR(), event.getTIMESTAMP_START_MINUTE(), event.getTIMESTAMP_START_SECOND(), event.getTIMESTAMP_START_MILLISEC(), event.getTimestamp());
        }
        return -1l;
    }

    public static long formatInternalProcRrcConnSetupStartTime(final INTERNAL_PROC_RRC_CONN_SETUP event) {
        if (event != null) {
            return formatStartTime(event.getTIMESTAMP_START_HOUR(), event.getTIMESTAMP_START_MINUTE(), event.getTIMESTAMP_START_SECOND(), event.getTIMESTAMP_START_MILLISEC(), event.getTimestamp());
        }
        return -1l;
    }

    public static long formatInternalProcS1SigConnSetupStartTime(final INTERNAL_PROC_S1_SIG_CONN_SETUP event) {
        if (event != null) {
            return formatStartTime(event.getTIMESTAMP_START_HOUR(), event.getTIMESTAMP_START_MINUTE(), event.getTIMESTAMP_START_SECOND(), event.getTIMESTAMP_START_MILLISEC(), event.getTimestamp());
        }
        return -1l;
    }

    public static long formatInternalProcUeCtxtReleaseStartTime(final INTERNAL_PROC_UE_CTXT_RELEASE event) {
        if (event != null) {
            return formatStartTime(event.getTIMESTAMP_START_HOUR(), event.getTIMESTAMP_START_MINUTE(), event.getTIMESTAMP_START_SECOND(), event.getTIMESTAMP_START_MILLISEC(), event.getTimestamp());
        }
        return -1l;
    }

    public static long formatInternalProcHoPrepX2OutStartTime(final INTERNAL_PROC_HO_PREP_X2_OUT event) {
        if (event != null) {
            return formatStartTime(event.getTIMESTAMP_START_HOUR(), event.getTIMESTAMP_START_MINUTE(), event.getTIMESTAMP_START_SECOND(), event.getTIMESTAMP_START_MILLISEC(), event.getTimestamp());
        }
        return -1l;
    }

    public static long formatInternalProcHoExecX2OutStartTime(final INTERNAL_PROC_HO_EXEC_X2_OUT event) {
        if (event != null) {
            return formatStartTime(event.getTIMESTAMP_START_HOUR(), event.getTIMESTAMP_START_MINUTE(), event.getTIMESTAMP_START_SECOND(), event.getTIMESTAMP_START_MILLISEC(), event.getTimestamp());
        }
        return -1l;
    }

    public static long formatInternalProcHoPrepX2InStartTime(final INTERNAL_PROC_HO_PREP_X2_IN event) {
        if (event != null) {
            return formatStartTime(event.getTIMESTAMP_START_HOUR(), event.getTIMESTAMP_START_MINUTE(), event.getTIMESTAMP_START_SECOND(), event.getTIMESTAMP_START_MILLISEC(), event.getTimestamp());
        }
        return -1l;
    }

    public static long formatInternalProcHoExecX2InStartTime(final INTERNAL_PROC_HO_EXEC_X2_IN event) {
        if (event != null) {
            return formatStartTime(event.getTIMESTAMP_START_HOUR(), event.getTIMESTAMP_START_MINUTE(), event.getTIMESTAMP_START_SECOND(), event.getTIMESTAMP_START_MILLISEC(), event.getTimestamp());
        }
        return -1l;
    }

    public static long formatInternalProcHoPrepS1OutStartTime(final INTERNAL_PROC_HO_PREP_S1_OUT event) {
        if (event != null) {
            return formatStartTime(event.getTIMESTAMP_START_HOUR(), event.getTIMESTAMP_START_MINUTE(), event.getTIMESTAMP_START_SECOND(), event.getTIMESTAMP_START_MILLISEC(), event.getTimestamp());
        }
        return -1l;
    }

    public static long formatInternalProcHoExecS1OutStartTime(final INTERNAL_PROC_HO_EXEC_S1_OUT event) {
        if (event != null) {
            return formatStartTime(event.getTIMESTAMP_START_HOUR(), event.getTIMESTAMP_START_MINUTE(), event.getTIMESTAMP_START_SECOND(), event.getTIMESTAMP_START_MILLISEC(), event.getTimestamp());
        }
        return -1l;
    }

    public static long formatInternalProcHoPrepS1InStartTime(final INTERNAL_PROC_HO_PREP_S1_IN event) {
        if (event != null) {
            return formatStartTime(event.getTIMESTAMP_START_HOUR(), event.getTIMESTAMP_START_MINUTE(), event.getTIMESTAMP_START_SECOND(), event.getTIMESTAMP_START_MILLISEC(), event.getTimestamp());
        }
        return -1l;
    }

    public static long formatInternalProcHoExecS1InStartTime(final INTERNAL_PROC_HO_EXEC_S1_IN event) {
        if (event != null) {
            return formatStartTime(event.getTIMESTAMP_START_HOUR(), event.getTIMESTAMP_START_MINUTE(), event.getTIMESTAMP_START_SECOND(), event.getTIMESTAMP_START_MILLISEC(), event.getTimestamp());
        }
        return -1l;
    }

    public static long formatInternalProcErabSetupStartTime(final INTERNAL_PROC_ERAB_SETUP event) {
        if (event != null) {
            return formatStartTime(event.getTIMESTAMP_START_HOUR(), event.getTIMESTAMP_START_MINUTE(), event.getTIMESTAMP_START_SECOND(), event.getTIMESTAMP_START_MILLISEC(), event.getTimestamp());
        }
        return -1l;
    }

    public static long formatInternalProcErabReleaseStartTime(final INTERNAL_PROC_ERAB_RELEASE event) {
        if (event != null) {
            return formatStartTime(event.getTIMESTAMP_START_HOUR(), event.getTIMESTAMP_START_MINUTE(), event.getTIMESTAMP_START_SECOND(), event.getTIMESTAMP_START_MILLISEC(), event.getTimestamp());
        }
        return -1l;
    }

    static long formatStartTime(final byte hour, final byte minute, final byte second, final int milisec, final long stopTime) {
        if ((-1 != hour) && (-1 != minute) && (-1 != second) && (-1 != milisec) && (-1 != stopTime)) {
            final MutableDateTime mdt = new MutableDateTime(stopTime, DateTimeZone.UTC);
            final boolean sameDay = (hour <= mdt.hourOfDay().get());
            mdt.setHourOfDay(hour);
            mdt.setMinuteOfHour(minute);
            mdt.setSecondOfMinute(second);
            mdt.setMillisOfSecond(milisec);
            if (!sameDay) {
                mdt.setDayOfMonth(mdt.getDayOfMonth() - 1);
            }
            return mdt.getMillis();
        }
        return -1l;
    }

    public static boolean isAttachBearerCauseIsValid(final L_ATTACH attach) {
        final byte defaultBearerId = attach.getPDN_INFO_DEFAULT_BEARER_ID();
        final int positionOfTheDefaultBearerId = getPositionOfTheDefaultBearerId(defaultBearerId, attach);

        if (positionOfTheDefaultBearerId == -1) {
            return false;
        }

        final int[] bearerCauseArray = attach.getBEARERS_BEARER_CAUSE();
        if (bearerCauseArray.length > positionOfTheDefaultBearerId) {//it is not all the time 16 element array, if position is 0 array should be at least 1 element length
            return bearerCauseArray[positionOfTheDefaultBearerId] == 0; // check if bearerCause is 0 for that default PDN
        }
        return false;
    }

    private static int getPositionOfTheDefaultBearerId(final byte defaultBearerId, final L_ATTACH attach) {
        final byte[] allDefaultBearerIds = attach.getBEARERS_DEFAULT_BEARER_ID();
        int i = 0;
        for (final byte b : allDefaultBearerIds) {
            if (defaultBearerId == b) {
                return i;
            }
            i++;
        }
        return -1;
    }



    public static int getSuccessfulCtxtERABCount(final INTERNAL_PROC_INITIAL_CTXT_SETUP contextSetup) {
        int numberOfSuccesfulErabs = 0;
        numberOfSuccesfulErabs = contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_1() == 0 ? ++numberOfSuccesfulErabs : numberOfSuccesfulErabs;
        numberOfSuccesfulErabs = contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_2() == 0 ? ++numberOfSuccesfulErabs : numberOfSuccesfulErabs;
        numberOfSuccesfulErabs = contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_3() == 0 ? ++numberOfSuccesfulErabs : numberOfSuccesfulErabs;
        numberOfSuccesfulErabs = contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_4() == 0 ? ++numberOfSuccesfulErabs : numberOfSuccesfulErabs;
        numberOfSuccesfulErabs = contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_5() == 0 ? ++numberOfSuccesfulErabs : numberOfSuccesfulErabs;
        numberOfSuccesfulErabs = contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_6() == 0 ? ++numberOfSuccesfulErabs : numberOfSuccesfulErabs;
        numberOfSuccesfulErabs = contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_7() == 0 ? ++numberOfSuccesfulErabs : numberOfSuccesfulErabs;
        numberOfSuccesfulErabs = contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_8() == 0 ? ++numberOfSuccesfulErabs : numberOfSuccesfulErabs;
        numberOfSuccesfulErabs = contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_9() == 0 ? ++numberOfSuccesfulErabs : numberOfSuccesfulErabs;
        numberOfSuccesfulErabs = contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_10() == 0 ? ++numberOfSuccesfulErabs : numberOfSuccesfulErabs;
        numberOfSuccesfulErabs = contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_11() == 0 ? ++numberOfSuccesfulErabs : numberOfSuccesfulErabs;
        numberOfSuccesfulErabs = contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_12() == 0 ? ++numberOfSuccesfulErabs : numberOfSuccesfulErabs;
        numberOfSuccesfulErabs = contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_13() == 0 ? ++numberOfSuccesfulErabs : numberOfSuccesfulErabs;
        numberOfSuccesfulErabs = contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_14() == 0 ? ++numberOfSuccesfulErabs : numberOfSuccesfulErabs;
        numberOfSuccesfulErabs = contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_15() == 0 ? ++numberOfSuccesfulErabs : numberOfSuccesfulErabs;
        numberOfSuccesfulErabs = contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_16() == 0 ? ++numberOfSuccesfulErabs : numberOfSuccesfulErabs;
        return numberOfSuccesfulErabs;
    }

    public static byte[] formatArrayENB_S1HO_PREP_IN_RESULT_ARRAY(final INTERNAL_PROC_HO_PREP_S1_IN prepS1In) {
        return new byte[] { prepS1In.getPROC_HO_PREP_IN_RESULT_1(), prepS1In.getPROC_HO_PREP_IN_RESULT_2(), prepS1In.getPROC_HO_PREP_IN_RESULT_3(), prepS1In.getPROC_HO_PREP_IN_RESULT_4(),
                prepS1In.getPROC_HO_PREP_IN_RESULT_5(), prepS1In.getPROC_HO_PREP_IN_RESULT_6(), prepS1In.getPROC_HO_PREP_IN_RESULT_7(), prepS1In.getPROC_HO_PREP_IN_RESULT_8(),
                prepS1In.getPROC_HO_PREP_IN_RESULT_9(), prepS1In.getPROC_HO_PREP_IN_RESULT_10(), prepS1In.getPROC_HO_PREP_IN_RESULT_11(), prepS1In.getPROC_HO_PREP_IN_RESULT_12(),
                prepS1In.getPROC_HO_PREP_IN_RESULT_13(), prepS1In.getPROC_HO_PREP_IN_RESULT_14(), prepS1In.getPROC_HO_PREP_IN_RESULT_15(), prepS1In.getPROC_HO_PREP_IN_RESULT_16() };
    }

    public static byte[] formatArrayENB_S1HO_PREP_IN_ERAB_ID_SETUP_REQ_ARRAY(final INTERNAL_PROC_HO_PREP_S1_IN prepS1In) {
        return new byte[] { prepS1In.getERAB_ID_SETUP_REQ_1(), prepS1In.getERAB_ID_SETUP_REQ_2(), prepS1In.getERAB_ID_SETUP_REQ_3(), prepS1In.getERAB_ID_SETUP_REQ_4(),
                prepS1In.getERAB_ID_SETUP_REQ_5(), prepS1In.getERAB_ID_SETUP_REQ_6(), prepS1In.getERAB_ID_SETUP_REQ_7(), prepS1In.getERAB_ID_SETUP_REQ_8(), prepS1In.getERAB_ID_SETUP_REQ_9(),
                prepS1In.getERAB_ID_SETUP_REQ_10(), prepS1In.getERAB_ID_SETUP_REQ_11(), prepS1In.getERAB_ID_SETUP_REQ_12(), prepS1In.getERAB_ID_SETUP_REQ_13(), prepS1In.getERAB_ID_SETUP_REQ_14(),
                prepS1In.getERAB_ID_SETUP_REQ_15(), prepS1In.getERAB_ID_SETUP_REQ_16() };
    }

    public static byte[] formatArrayENB_S1HO_PREP_IN_ERAB_ARP_ARRAY(final INTERNAL_PROC_HO_PREP_S1_IN prepS1In) {
        return new byte[] { prepS1In.getERAB_ARP_1(), prepS1In.getERAB_ARP_2(), prepS1In.getERAB_ARP_3(), prepS1In.getERAB_ARP_4(), prepS1In.getERAB_ARP_5(), prepS1In.getERAB_ARP_6(),
                prepS1In.getERAB_ARP_7(), prepS1In.getERAB_ARP_8(), prepS1In.getERAB_ARP_9(), prepS1In.getERAB_ARP_10(), prepS1In.getERAB_ARP_11(), prepS1In.getERAB_ARP_12(),
                prepS1In.getERAB_ARP_13(), prepS1In.getERAB_ARP_14(), prepS1In.getERAB_ARP_15(), prepS1In.getERAB_ARP_16() };
    }

    public static byte[] formatArrayENB_X2HO_PREP_IN_RESULT_ARRAY(final INTERNAL_PROC_HO_PREP_X2_IN prepX2In) {
        return new byte[] { prepX2In.getPROC_HO_PREP_IN_RESULT_1(), prepX2In.getPROC_HO_PREP_IN_RESULT_2(), prepX2In.getPROC_HO_PREP_IN_RESULT_3(), prepX2In.getPROC_HO_PREP_IN_RESULT_4(),
                prepX2In.getPROC_HO_PREP_IN_RESULT_5(), prepX2In.getPROC_HO_PREP_IN_RESULT_6(), prepX2In.getPROC_HO_PREP_IN_RESULT_7(), prepX2In.getPROC_HO_PREP_IN_RESULT_8(),
                prepX2In.getPROC_HO_PREP_IN_RESULT_9(), prepX2In.getPROC_HO_PREP_IN_RESULT_10(), prepX2In.getPROC_HO_PREP_IN_RESULT_11(), prepX2In.getPROC_HO_PREP_IN_RESULT_12(),
                prepX2In.getPROC_HO_PREP_IN_RESULT_13(), prepX2In.getPROC_HO_PREP_IN_RESULT_14(), prepX2In.getPROC_HO_PREP_IN_RESULT_15(), prepX2In.getPROC_HO_PREP_IN_RESULT_16() };
    }

    public static byte[] formatArrayENB_X2HO_PREP_IN_ERAB_ID_SETUP_REQ_ARRRAY(final INTERNAL_PROC_HO_PREP_X2_IN prepX2In) {
        return new byte[] { prepX2In.getERAB_ID_SETUP_REQ_1(), prepX2In.getERAB_ID_SETUP_REQ_2(), prepX2In.getERAB_ID_SETUP_REQ_3(), prepX2In.getERAB_ID_SETUP_REQ_4(),
                prepX2In.getERAB_ID_SETUP_REQ_5(), prepX2In.getERAB_ID_SETUP_REQ_6(), prepX2In.getERAB_ID_SETUP_REQ_7(), prepX2In.getERAB_ID_SETUP_REQ_8(), prepX2In.getERAB_ID_SETUP_REQ_9(),
                prepX2In.getERAB_ID_SETUP_REQ_10(), prepX2In.getERAB_ID_SETUP_REQ_11(), prepX2In.getERAB_ID_SETUP_REQ_12(), prepX2In.getERAB_ID_SETUP_REQ_13(), prepX2In.getERAB_ID_SETUP_REQ_14(),
                prepX2In.getERAB_ID_SETUP_REQ_15(), prepX2In.getERAB_ID_SETUP_REQ_16() };
    }

    public static byte[] formatArrayENB_X2HO_PREP_IN_ERAB_ARP_ARRAY(final INTERNAL_PROC_HO_PREP_X2_IN prepX2In) {
        return new byte[] { prepX2In.getERAB_ARP_1(), prepX2In.getERAB_ARP_2(), prepX2In.getERAB_ARP_3(), prepX2In.getERAB_ARP_4(), prepX2In.getERAB_ARP_5(), prepX2In.getERAB_ARP_6(),
                prepX2In.getERAB_ARP_7(), prepX2In.getERAB_ARP_8(), prepX2In.getERAB_ARP_9(), prepX2In.getERAB_ARP_10(), prepX2In.getERAB_ARP_11(), prepX2In.getERAB_ARP_12(),
                prepX2In.getERAB_ARP_13(), prepX2In.getERAB_ARP_14(), prepX2In.getERAB_ARP_15(), prepX2In.getERAB_ARP_16() };
    }

    public static byte[] formatArrayERAB_SETUP_ERAB_FAILURE_3GPP_CAUSE_ARRAY(final INTERNAL_PROC_ERAB_SETUP erabSetup) {
        return new byte[] { erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_1(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_2(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_3(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_4(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_5(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_6(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_7(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_8(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_9(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_10(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_11(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_12(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_13(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_14(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_15(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_16() };
    }

    public static byte[] formatArrayERAB_SETUP_ERAB_FAILURE_3GPP_CAUSE_GROUP_ARRAY(final INTERNAL_PROC_ERAB_SETUP erabSetup) {
        return new byte[] { erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_1(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_2(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_3(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_4(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_5(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_6(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_7(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_8(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_9(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_10(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_11(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_12(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_13(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_14(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_15(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_16() };
    }

    public static byte[] formatArrayERAB_SETUP_ERAB_SUCC_ATT_ACC_TYPE_ARRAY(final INTERNAL_PROC_ERAB_SETUP erabSetup) {
        return new byte[] { erabSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_1(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_2(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_3(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_4(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_5(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_6(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_7(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_8(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_9(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_10(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_11(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_12(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_13(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_14(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_15(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_16() };
    }

    public static byte[] formatArrayERAB_SETUP_ERAB_REQ_ATT_ACC_TYPE_ARRAY(final INTERNAL_PROC_ERAB_SETUP erabSetup) {
        return new byte[] { erabSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_1(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_2(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_3(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_4(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_5(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_6(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_7(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_8(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_9(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_10(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_11(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_12(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_13(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_14(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_15(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_16() };
    }

    public static byte[] formatArrayERAB_SETUP_ERAB_REQ_PVI_ARRAY(final INTERNAL_PROC_ERAB_SETUP erabSetup) {
        return new byte[] { erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_1(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_2(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_3(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_4(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_5(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_6(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_7(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_8(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_9(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_10(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_11(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_12(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_13(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_14(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_15(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_16() };
    }

    public static byte[] formatArrayERAB_SETUP_ERAB_REQ_PCI_ARRAY(final INTERNAL_PROC_ERAB_SETUP erabSetup) {
        return new byte[] { erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_1(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_2(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_3(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_4(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_5(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_6(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_7(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_8(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_9(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_10(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_11(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_12(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_13(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_14(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_15(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_16() };
    }

    public static byte[] formatArrayERAB_SETUP_ERAB_ARP_ARRAY(final INTERNAL_PROC_ERAB_SETUP erabSetup) {
        return new byte[] { erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_1(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_2(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_3(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_4(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_5(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_6(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_7(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_8(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_9(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_10(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_11(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_12(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_13(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_14(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_15(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_16() };
    }

    public static int[] formatArrayERAB_SETUP_ERAB_QCI_ARRAY(final INTERNAL_PROC_ERAB_SETUP erabSetup) {
        return new int[] { erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_1(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_2(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_3(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_4(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_5(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_6(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_7(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_8(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_9(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_10(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_11(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_12(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_13(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_14(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_15(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_16() };
    }

    public static byte[] formatArrayERAB_SETUP_ERAB_RESULT_ARRAY(final INTERNAL_PROC_ERAB_SETUP erabSetup) {
        return new byte[] { erabSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_1(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_2(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_3(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_4(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_5(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_6(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_7(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_8(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_9(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_10(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_11(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_12(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_13(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_14(), erabSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_15(),
                erabSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_16() };
    }

    public static byte[] formatArrayERABRELEASE_ERAB_FAILURE_3GPP_CAUSE_ARRAY(final INTERNAL_PROC_ERAB_RELEASE erabRelease) {
        return new byte[] { erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_1(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_2(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_3(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_4(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_5(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_6(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_7(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_8(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_9(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_10(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_11(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_12(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_13(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_14(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_15(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_16() };
    }

    public static byte[] formatArrayERABRELEASE_ERAB_FAILURE_3GPP_CAUSE_GROUP_ARRAY(final INTERNAL_PROC_ERAB_RELEASE erabRelease) {
        return new byte[] { erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_GROUP_1(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_GROUP_2(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_GROUP_3(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_GROUP_4(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_GROUP_5(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_GROUP_6(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_GROUP_7(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_GROUP_8(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_GROUP_9(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_GROUP_10(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_GROUP_11(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_GROUP_12(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_GROUP_13(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_GROUP_14(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_GROUP_15(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_FAILURE_3GPP_CAUSE_GROUP_16() };
    }

    public static byte[] formatArrayERABRELEASE_ERAB_REQ_PVI_ARRAY(final INTERNAL_PROC_ERAB_RELEASE erabRelease) {
        return new byte[] { erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_1(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_2(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_3(),
                erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_4(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_5(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_6(),
                erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_7(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_8(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_9(),
                erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_10(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_11(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_12(),
                erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_13(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_14(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_15(),
                erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_16() };
    }

    public static byte[] formatArrayERABRELEASE_ERAB_REQ_PCI_ARRAY(final INTERNAL_PROC_ERAB_RELEASE erabRelease) {
        return new byte[] { erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_1(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_2(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_3(),
                erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_4(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_5(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_6(),
                erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_7(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_8(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_9(),
                erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_10(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_11(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_12(),
                erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_13(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_14(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_15(),
                erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_16() };
    }

    public static byte[] formatArrayERABRELEASE_ERAB_REQ_ARP_ARRAY(final INTERNAL_PROC_ERAB_RELEASE erabRelease) {
        return new byte[] { erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_1(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_2(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_3(),
                erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_4(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_5(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_6(),
                erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_7(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_8(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_9(),
                erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_10(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_11(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_12(),
                erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_13(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_14(), erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_15(),
                erabRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_16() };
    }

    public static byte[] formatArrayERABRELEASE_ERAB_REQ_3GPP_CAUSE_ARRAY(final INTERNAL_PROC_ERAB_RELEASE erabRelease) {
        return new byte[] { erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_1(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_2(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_3(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_4(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_5(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_6(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_7(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_8(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_9(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_10(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_11(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_12(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_13(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_14(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_15(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_16() };
    }

    public static byte[] formatArrayERABRELEASE_ERAB_REQ_3GPP_CAUSE_GROUP_ARRAY(final INTERNAL_PROC_ERAB_RELEASE erabRelease) {
        return new byte[] { erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_GROUP_1(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_GROUP_2(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_GROUP_3(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_GROUP_4(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_GROUP_5(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_GROUP_6(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_GROUP_7(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_GROUP_8(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_GROUP_9(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_GROUP_10(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_GROUP_11(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_GROUP_12(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_GROUP_13(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_GROUP_14(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_GROUP_15(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_3GPP_CAUSE_GROUP_16() };
    }

    public static int[] formatArrayERABRELEASE_ERAB_REQ_QCI_ARRAY(final INTERNAL_PROC_ERAB_RELEASE erabRelease) {
        return new int[] { erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_1(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_2(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_3(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_4(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_5(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_6(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_7(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_8(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_9(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_10(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_11(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_12(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_13(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_14(), erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_15(),
                erabRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_16() };
    }

    public static byte[] formatArrayINITIAL_CTXT_SETUP_ERAB_RESULT_ARRAY(final INTERNAL_PROC_INITIAL_CTXT_SETUP contextSetup) {
        return new byte[] { contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_1(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_2(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_3(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_4(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_5(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_6(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_7(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_8(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_9(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_10(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_11(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_12(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_13(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_14(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_15(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_RESULT_16() };
    }

    public static int[] formatArrayINITIAL_CTXT_SETUP_ERAB_QCI_ARRAY(final INTERNAL_PROC_INITIAL_CTXT_SETUP contextSetup) {
        return new int[] { contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_1(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_2(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_3(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_4(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_5(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_6(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_7(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_8(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_9(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_10(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_11(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_12(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_13(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_14(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_15(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_QCI_16() };
    }

    public static byte[] formatArrayINITIAL_CTXT_SETUP_ERAB_REQ_PCI_ARRAY(final INTERNAL_PROC_INITIAL_CTXT_SETUP contextSetup) {
        return new byte[] { contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_1(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_2(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_3(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_4(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_5(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_6(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_7(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_8(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_9(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_10(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_11(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_12(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_13(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_14(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_15(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_16() };
    }

    public static byte[] formatArrayINITIAL_CTXT_SETUP_ERAB_REQ_ATT_ACC_TYPE_ARRAY(final INTERNAL_PROC_INITIAL_CTXT_SETUP contextSetup) {
        return new byte[] { contextSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_1(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_2(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_3(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_4(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_5(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_6(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_7(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_8(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_9(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_10(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_11(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_12(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_13(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_14(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_15(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_ATT_ACC_TYPE_16() };
    }

    public static byte[] formatArrayINITIAL_CTXT_SETUP_ERAB_FAILURE_3GPP_CAUSE_GROUP_ARRAY(final INTERNAL_PROC_INITIAL_CTXT_SETUP contextSetup) {
        return new byte[] { contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_1(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_2(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_3(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_4(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_5(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_6(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_7(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_8(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_9(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_10(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_11(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_12(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_13(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_14(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_15(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_GROUP_16() };
    }

    public static byte[] formatArrayINITIAL_CTXT_SETUP_ERAB_ARP_ARRAY(final INTERNAL_PROC_INITIAL_CTXT_SETUP contextSetup) {
        return new byte[] { contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_1(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_2(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_3(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_4(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_5(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_6(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_7(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_8(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_9(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_10(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_11(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_12(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_13(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_14(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_15(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_16() };
    }

    public static byte[] formatArrayINITIAL_CTXT_SETUP_ERAB_REQ_PVI_ARRAY(final INTERNAL_PROC_INITIAL_CTXT_SETUP contextSetup) {
        return new byte[] { contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_1(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_2(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_3(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_4(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_5(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_6(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_7(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_8(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_9(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_10(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_11(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_12(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_13(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_14(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_15(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_16() };
    }

    public static byte[] formatArrayINITIAL_CTXT_SETUP_ERAB_SUCC_ATT_ACC_TYPE_ARRAY(final INTERNAL_PROC_INITIAL_CTXT_SETUP contextSetup) {
        return new byte[] { contextSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_1(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_2(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_3(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_4(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_5(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_6(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_7(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_8(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_9(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_10(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_11(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_12(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_13(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_14(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_15(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_SUCC_ACC_TYPE_16() };
    }

    public static byte[] formatArrayINITIAL_CTXT_SETUP_ERAB_FAILURE_3GPP_CAUSE_ARRAY(final INTERNAL_PROC_INITIAL_CTXT_SETUP contextSetup) {
        return new byte[] { contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_1(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_2(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_3(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_4(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_5(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_6(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_7(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_8(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_9(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_10(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_11(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_12(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_13(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_14(),
                contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_15(), contextSetup.getEVENT_ARRAY_ERAB_SETUP_FAILURE_3GPP_CAUSE_16() };
    }

    public static byte[] formatArrayENB_UECTXTRELEASE_ERAB_DATA_LOST_BITMAP(final INTERNAL_PROC_UE_CTXT_RELEASE contextRelease) {
        return new byte[] { contextRelease.getEVENT_ARRAY_ERAB_DATA_LOST_1(), contextRelease.getEVENT_ARRAY_ERAB_DATA_LOST_2(), contextRelease.getEVENT_ARRAY_ERAB_DATA_LOST_3(),
                contextRelease.getEVENT_ARRAY_ERAB_DATA_LOST_4(), contextRelease.getEVENT_ARRAY_ERAB_DATA_LOST_5(), contextRelease.getEVENT_ARRAY_ERAB_DATA_LOST_6(),
                contextRelease.getEVENT_ARRAY_ERAB_DATA_LOST_7(), contextRelease.getEVENT_ARRAY_ERAB_DATA_LOST_8(), contextRelease.getEVENT_ARRAY_ERAB_DATA_LOST_9(),
                contextRelease.getEVENT_ARRAY_ERAB_DATA_LOST_10(), contextRelease.getEVENT_ARRAY_ERAB_DATA_LOST_11(), contextRelease.getEVENT_ARRAY_ERAB_DATA_LOST_12(),
                contextRelease.getEVENT_ARRAY_ERAB_DATA_LOST_13(), contextRelease.getEVENT_ARRAY_ERAB_DATA_LOST_14(), contextRelease.getEVENT_ARRAY_ERAB_DATA_LOST_15(),
                contextRelease.getEVENT_ARRAY_ERAB_DATA_LOST_16() };
    }

    public static byte[] formatArrayENB_UECTXTRELEASE_HO_OUT_PREP_ERAB_FAIL_BITMAP(final INTERNAL_PROC_UE_CTXT_RELEASE contextRelease) {
        return new byte[] { contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_1(), contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_2(), contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_3(),
                contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_4(), contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_5(), contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_6(),
                contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_7(), contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_8(), contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_9(),
                contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_10(), contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_11(), contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_12(),
                contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_13(), contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_14(), contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_15(),
                contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_16() };
    }

    public static int[] formatArrayENB_UECTXTRELEASE_ERAB_RELEASE_REQ_QCI_ARRAY(final INTERNAL_PROC_UE_CTXT_RELEASE contextRelease) {
        return new int[] { contextRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_1(), contextRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_2(), contextRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_3(),
                contextRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_4(), contextRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_5(), contextRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_6(),
                contextRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_7(), contextRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_8(), contextRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_9(),
                contextRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_10(), contextRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_11(), contextRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_12(),
                contextRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_13(), contextRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_14(), contextRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_15(),
                contextRelease.getEVENT_ARRAY_ERAB_RELEASE_REQ_QCI_16() };
    }

    public static byte[] formatArrayENB_UECTXTRELEASE_ERAB_RELEASE_REQ_ARP_ARRAY(final INTERNAL_PROC_UE_CTXT_RELEASE contextRelease) {
        return new byte[] { contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_1(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_2(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_3(),
                contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_4(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_5(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_6(),
                contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_7(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_8(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_9(),
                contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_10(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_11(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_12(),
                contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_13(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_14(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_15(),
                contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_ARP_16() };
    }

    public static byte[] formatArrayENB_UECTXTRELEASE_ERAB_RELEASE_REQ_PCI_ARRAY(final INTERNAL_PROC_UE_CTXT_RELEASE contextRelease) {
        return new byte[] { contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_1(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_2(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_3(),
                contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_4(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_5(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_6(),
                contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_7(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_8(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_9(),
                contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_10(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_11(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_12(),
                contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_13(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_14(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_15(),
                contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PCI_16() };
    }

    public static byte[] formatArrayENB_UECTXTRELEASE_ERAB_RELEASE_REQ_PVI_ARRAY(final INTERNAL_PROC_UE_CTXT_RELEASE contextRelease) {
        return new byte[] { contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_1(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_2(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_3(),
                contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_4(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_5(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_6(),
                contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_7(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_8(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_9(),
                contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_10(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_11(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_12(),
                contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_13(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_14(), contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_15(),
                contextRelease.getEVENT_ARRAY_ERAB_SETUP_REQ_PVI_16() };
    }

    public static byte[] formatArrayENB_UECTXTRELEASE_HO_OUT_PREP_ERAB_FAIL_AND_DATA_LOST_ARRAY(final INTERNAL_PROC_UE_CTXT_RELEASE contextRelease) {
        return new byte[] { contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_AND_DATA_LOST_1(), contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_AND_DATA_LOST_2(),
                contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_AND_DATA_LOST_3(), contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_AND_DATA_LOST_4(),
                contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_AND_DATA_LOST_5(), contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_AND_DATA_LOST_6(),
                contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_AND_DATA_LOST_7(), contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_AND_DATA_LOST_8(),
                contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_AND_DATA_LOST_9(), contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_AND_DATA_LOST_10(),
                contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_AND_DATA_LOST_11(), contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_AND_DATA_LOST_12(),
                contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_AND_DATA_LOST_13(), contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_AND_DATA_LOST_14(),
                contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_AND_DATA_LOST_15(), contextRelease.getEVENT_ARRAY_HO_OUT_PREP_ERAB_FAIL_AND_DATA_LOST_16() };
    }

    public static byte[] formatArrayENB_UECTXTRELEASE_HO_OUT_EXEC_ERAB_FAIL_AND_DATA_LOST_ARRAY(final INTERNAL_PROC_UE_CTXT_RELEASE contextRelease) {
        return new byte[] { contextRelease.getEVENT_ARRAY_HO_OUT_EXEC_ERAB_FAIL_AND_DATA_LOST_1(), contextRelease.getEVENT_ARRAY_HO_OUT_EXEC_ERAB_FAIL_AND_DATA_LOST_2(),
                contextRelease.getEVENT_ARRAY_HO_OUT_EXEC_ERAB_FAIL_AND_DATA_LOST_3(), contextRelease.getEVENT_ARRAY_HO_OUT_EXEC_ERAB_FAIL_AND_DATA_LOST_4(),
                contextRelease.getEVENT_ARRAY_HO_OUT_EXEC_ERAB_FAIL_AND_DATA_LOST_5(), contextRelease.getEVENT_ARRAY_HO_OUT_EXEC_ERAB_FAIL_AND_DATA_LOST_6(),
                contextRelease.getEVENT_ARRAY_HO_OUT_EXEC_ERAB_FAIL_AND_DATA_LOST_7(), contextRelease.getEVENT_ARRAY_HO_OUT_EXEC_ERAB_FAIL_AND_DATA_LOST_8(),
                contextRelease.getEVENT_ARRAY_HO_OUT_EXEC_ERAB_FAIL_AND_DATA_LOST_9(), contextRelease.getEVENT_ARRAY_HO_OUT_EXEC_ERAB_FAIL_AND_DATA_LOST_10(),
                contextRelease.getEVENT_ARRAY_HO_OUT_EXEC_ERAB_FAIL_AND_DATA_LOST_11(), contextRelease.getEVENT_ARRAY_HO_OUT_EXEC_ERAB_FAIL_AND_DATA_LOST_12(),
                contextRelease.getEVENT_ARRAY_HO_OUT_EXEC_ERAB_FAIL_AND_DATA_LOST_13(), contextRelease.getEVENT_ARRAY_HO_OUT_EXEC_ERAB_FAIL_AND_DATA_LOST_14(),
                contextRelease.getEVENT_ARRAY_HO_OUT_EXEC_ERAB_FAIL_AND_DATA_LOST_15(), contextRelease.getEVENT_ARRAY_HO_OUT_EXEC_ERAB_FAIL_AND_DATA_LOST_16() };
    }

    public static int getNumberOfCharacters(final String value) {
        if (value != null) {
            return value.length();
        }
        return 0;
    }

}
