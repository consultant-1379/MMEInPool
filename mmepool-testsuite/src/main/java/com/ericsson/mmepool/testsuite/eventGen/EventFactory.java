package com.ericsson.mmepool.testsuite.eventGen;

import com.ericsson.xstream.base.apeventbeans.ApEventBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventFactory {

    private static final Logger logger = LoggerFactory.getLogger(EventFactory.class);
    private static final String CELLTRACE = "com.ericsson.xstream.apeventbeans.celltrace";
    private static final String EBM = "com.ericsson.xstream.apeventbeans.ebm";
    private static final String SGW = "com.ericsson.xstream.apeventbeans.sgw";
    private static final String CTUM = "com.ericsson.xstream.apeventbeans.ctum";
    private static final String REGEX_CELLTRACE = "^INTERNAL_[A-Z_]*|^S1_[A-Z_]*|^X2_[A-Z_]*|^RRC_[A-Z_]*";
    private static final String REGEX_EBM = "^L_[A-Z_]*";
    private static final String REGEX_SGW = "^SESSION_[A-Z]*|^BEARER_[A-Z]*";
    private static final String REGEX_CTUM = "^CTUM";
    private static final String REGEX_TIME = "([0-9]{4})-([0-1][0-9])-([0-3][0-9])\\s([0-1][0-9]|[2][0-3]):([0-5][0-9]):([0-5][0-9])";

    /**
     * Determine if a method is a set method.
     * 
     * @param method
     * @return
     */
    private static boolean isSetter(Method method) {
        logger.debug("isSetter() ------>");
        logger.debug(method.getName() + "(" + method.getName().startsWith("set") + ")");
        if (!method.getName().startsWith("set"))
            return false;
        if (method.getParameterTypes().length != 1)
            return false;
        logger.debug("isSetter() <------");
        return true;
    }

    /**
     * Return a hashmap of all setter methods in the ApEventBean
     * 
     * @param aClass
     * @return
     */
    private static HashMap<String, Method> getAllSetters(Class aClass) {
        logger.debug("getAllSetters() ------>");
        logger.debug("Getting a list of all setter methods.");
        HashMap<String, Method> setters = new HashMap<>();
        for (Method method : aClass.getMethods()) {
            if (isSetter(method)) {
                setters.put(method.getName().toUpperCase(), method);
            }
        }
        logger.debug("getAllSetters() <------");
        return setters;
    }

    /**
     * Invokes the setter methods of the ApEventBean. Methods are identified using RegEx.
     * 
     * @param apEventBean
     * @param regEx
     * @param attributes
     * @param setters
     * @throws java.lang.reflect.InvocationTargetException
     * @throws IllegalAccessException
     */
    private static void invokeApEventBean(ApEventBean apEventBean, String regEx, String attributes, HashMap<String, Method> setters) throws InvocationTargetException, IllegalAccessException {
        logger.debug("invokeApEventBean() ------>");
        logger.debug("Using RegEx[" + regEx + "] to identify which setter methods to invoke.");
        logger.debug("Attribute list : " + attributes);
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(attributes);
        while (matcher.find()) {
            String[] keyValuePair = matcher.group().split("=");
            String key = keyValuePair[0].toUpperCase();
            Method method = setters.get("SET" + key);
            if (method != null) {
                Class parameterType = method.getParameterTypes()[0];
                Object parameterValue = null;

                if (parameterType.equals(Boolean.TYPE)) {
                    parameterValue = Boolean.valueOf(keyValuePair[1]).booleanValue();
                    method.invoke(apEventBean, parameterValue);
                } else if (parameterType.equals(Byte.TYPE)) {

                    parameterValue = Byte.valueOf(keyValuePair[1]).byteValue();
                    method.invoke(apEventBean, parameterValue);

                } else if (parameterType.equals(Character.TYPE)) {
                    parameterValue = keyValuePair[1].charAt(0);
                    method.invoke(apEventBean, parameterValue);

                } else if (parameterType.equals(Double.TYPE)) {
                    parameterValue = Double.valueOf(keyValuePair[1]).doubleValue();
                    method.invoke(apEventBean, parameterValue);

                } else if (parameterType.equals(Float.TYPE)) {
                    parameterValue = Float.valueOf(keyValuePair[1]).floatValue();
                    method.invoke(apEventBean, parameterValue);
                } else if (parameterType.equals(Integer.TYPE)) {
                    parameterValue = Integer.valueOf(keyValuePair[1]).intValue();
                    method.invoke(apEventBean, parameterValue);
                } else if (parameterType.equals(Long.TYPE)) {
                    parameterValue = Long.valueOf(keyValuePair[1]).longValue();
                    method.invoke(apEventBean, parameterValue);

                } else if (parameterType.equals(Short.TYPE)) {
                    parameterValue = Integer.valueOf(keyValuePair[1]).intValue();
                    method.invoke(apEventBean, parameterValue);

                } else if (parameterType.equals(String.class)) {
                    parameterValue = keyValuePair[1];
                    method.invoke(apEventBean, parameterValue);
                } else if (parameterType.equals(byte[].class)) {
                    //remove {} from string,then split into an array of strings {1,2,3,4,5,6}
                    String[] stringValues = keyValuePair[1].substring(1, keyValuePair[1].length() - 1).split(",");
                    byte[] byteValues = new byte[stringValues.length];
                    for (int i = 0; i < stringValues.length; i++) {
                        byteValues[i] = Byte.valueOf(stringValues[i]);
                    }
                    parameterValue = byteValues;
                    method.invoke(apEventBean, parameterValue);
                } else if (parameterType.equals(int[].class)) {
                    //remove {} from string,then split into an array of strings {1,2,3,4,5,6}
                    String[] stringValues = keyValuePair[1].substring(1, keyValuePair[1].length() - 1).split(",");
                    int[] intValues = new int[stringValues.length];
                    for (int i = 0; i < stringValues.length; i++) {
                        intValues[i] = Integer.valueOf(stringValues[i]);
                    }
                    parameterValue = intValues;
                    method.invoke(apEventBean, parameterValue);
                } else if (parameterType.equals(String[].class)) {
                    //remove {} from string,then split into an array of strings {1,2,3,4,5,6}
                    String[] stringValues = keyValuePair[1].substring(1, keyValuePair[1].length() - 1).split(",");
                    parameterValue = stringValues;
                    method.invoke(apEventBean, parameterValue);
                } else if (parameterType.equals(long[].class)) {
                    //remove {} from string,then split into an array of strings {1,2,3,4,5,6}
                    String[] stringValues = keyValuePair[1].substring(1, keyValuePair[1].length() - 1).split(",");
                    long[] longValues = new long[stringValues.length];
                    for (int i = 0; i < stringValues.length; i++) {
                        longValues[i] = Long.valueOf(stringValues[i]);
                    }
                    parameterValue = longValues;
                    method.invoke(apEventBean, parameterValue);
                }
            } else {
                logger.error("Key(" + key + ") does not exist for event " + apEventBean.getClass().getName());

            }

        }
        logger.debug("invokeApEventBean() <------");
    }

    /**
     * Sets the timestamp of the ApEventBean
     * 
     * @param apEventBean
     * @param attributes
     * @throws java.text.ParseException
     */
    private static void setApEventBeanTime(ApEventBean apEventBean, String attributes) throws ParseException {
        logger.debug("setApEventBeanTime() ------>");
        logger.debug("Setting the event time.");
        //Use RegEx to identify the event time [yyyy-MM-dd HH:mm:ss].
        logger.debug("Using RegEx[" + REGEX_TIME + "] to identify Event time.");
        Pattern pattern = Pattern.compile(REGEX_TIME);
        Matcher matcher = pattern.matcher(attributes);
        if (matcher.find()) {
            String time = matcher.group(0);
            logger.debug("Using Event time : " + time);
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = formatter.parse(time);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            apEventBean.setTimestamp(calendar.getTimeInMillis());
        }
        logger.debug("setApEventBeanTime() <------");
    }

    /**
     * Creates an ApEventBean.
     * 
     * @param eventName
     * @param attributes
     * @return
     */
    public static ApEventBean createEventBean(String eventName, String attributes) {
        return createEventBean(eventName, null, attributes);
    }

    /**
     * Creates an ApEventBean.
     * 
     * @param eventName
     * @param version
     * @param attributes
     * @return
     */
    public static ApEventBean createEventBean(String eventName, String version, String attributes) {
        logger.debug("createEventBean() ------>");
        logger.debug("Obtaining the fully qualified class name.");
        String className = getClassName(eventName, version);

        Class aClass = null;
        ApEventBean obj = null;
        try {
            logger.debug("Obtaining Class Object for class name : " + className);
            aClass = Class.forName(className);
            //Using the default constructor, use shorthand method to create object.
            logger.debug("Instantiating Class Object.");
            obj = (ApEventBean) aClass.newInstance();
            //Set the event time.
            setApEventBeanTime(obj, attributes);
            //Gets a list of all setter methods.
            HashMap<String, Method> setters = getAllSetters(aClass);
            //Invokes all setter methods based on name=value pair.
            invokeApEventBean(obj, "[\\w]+=[\\w]+", attributes, setters);
            //invoke all setter methods based on name={value,value,value}
            invokeApEventBean(obj, "[\\w]+=[{][,\\w]*[}]", attributes, setters);

        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException", e);
        } catch (InstantiationException e) {
            logger.error("InstantiationException", e);
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException", e);
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException", e);
        } catch (ParseException e) {
            logger.error("ParseException", e);
        }
        logger.debug("createEventBean() ------>");
        return obj;
    }

    public static String getSchemaType(String eventName) {
        logger.debug("getSchemaType() ------>");
        logger.debug("Using  RegEx[" + REGEX_CELLTRACE + "] to identify eNodeB event.");
        Pattern pattern = Pattern.compile(REGEX_CELLTRACE);
        Matcher matcher = pattern.matcher(eventName);
        logger.debug("getSchemaType() <------");
        // INTERNAL_EVENT_UE_MOBILITY_EVAL
        // INTERNAL_PROC_ERAB_RELEASE
        // INTERNAL_PER_RADION_CELL_MEASUREMENT
        // INTERNAL_PROC_S1_SIG_CONN_SETUP
        if (matcher.find()) {
            return "celltrace";
        }
        logger.debug("Using RegEx[" + REGEX_EBM + "] to identify MME event.");
        pattern = Pattern.compile(REGEX_EBM);
        matcher = pattern.matcher(eventName);
        // L_ATTACH
        // L_DEDICATED_BEARER_ACTIVATE
        // L_DETACH
        // L_SERVICE_REQUEST
        if (matcher.find()) {
            return "ebm";
        }
        //Use RegEx to identify SGW events
        logger.debug("Using RegEx[" + REGEX_SGW + "] to identify SGW event.");
        pattern = Pattern.compile(REGEX_SGW);
        matcher = pattern.matcher(eventName);
        // SESSION_CREATION
        // SESSION_DELETION
        // BEARER_CREATION
        // BEARER_MODIFICATION
        if (matcher.find()) {
            return "sgw";
        }
        return null;
    }

    /**
     * Return the fully qualified class name
     * 
     * @param eventName
     *            The EventName
     * @param version
     *            schema version for the ApEventBean
     * @return
     */
    private static String getClassName(String eventName, String version) {
        logger.debug("getClassName() ------>");
        String className = "";
        logger.debug("Using  RegEx[" + REGEX_CELLTRACE + "] to identify eNodeB event.");
        Pattern pattern = Pattern.compile(REGEX_CELLTRACE);
        Matcher matcher = pattern.matcher(eventName);
        // INTERNAL_EVENT_UE_MOBILITY_EVAL
        // INTERNAL_PROC_ERAB_RELEASE
        // INTERNAL_PER_RADION_CELL_MEASUREMENT
        // INTERNAL_PROC_S1_SIG_CONN_SETUP
        if (matcher.find()) {
            className = CELLTRACE;
        }
        logger.debug("Using RegEx[" + REGEX_EBM + "] to identify MME event.");
        pattern = Pattern.compile(REGEX_EBM);
        matcher = pattern.matcher(eventName);
        // L_ATTACH
        // L_DEDICATED_BEARER_ACTIVATE
        // L_DETACH
        // L_SERVICE_REQUEST
        if (matcher.find()) {
            className = EBM;
        }
        //Use RegEx to identify SGW events
        logger.debug("Using RegEx[" + REGEX_SGW + "] to identify SGW event.");
        pattern = Pattern.compile(REGEX_SGW);
        matcher = pattern.matcher(eventName);
        // SESSION_CREATION
        // SESSION_DELETION
        // BEARER_CREATION
        // BEARER_MODIFICATION
        if (matcher.find()) {
            className = SGW;
        }
        logger.debug("Using RegEx[" + REGEX_CTUM + "] to identify CTUM event.");
        pattern = Pattern.compile(REGEX_CTUM);
        matcher = pattern.matcher(eventName);
        // CTUM
        if (matcher.find()) {
            className = CTUM;
        }
        if (version != null && !version.isEmpty()) {
            className = className + "_" + version + "." + eventName;
        } else {
            className = className + "." + eventName;
        }
        logger.debug("Fully Qualified Classname :: " + className);
        logger.debug("getClassName() <------");
        return className;
    }

    public static void main(String[] args) {

        // Set up a simple configuration that logs on the console.
        //        PropertyConfigurator.configure("log4j.properties");

        String event = "2012-05-21 16:06:30,subNetwork=abc,ne=lte01,name=lte01,version=12b,SCANNER_ID=123,RBS_MODULE_ID=1,GLOBAL_CELL_ID=10,ENBS1APID=12,MMES1APID=11,GUMMEI={1,23,34},RAC_UE_REF=10,TRACE_RECORDING_SESSION_REFERENCE={1,2,3},MOBILITY_TRIGGER=1,TRIGGERING_MEAS_ID=1,MOBILITY_DECISION=1,MEAS_ID_BITMAP=1,REDIRECT_RELEASE_INFO=1,RELEASE_WITH_REDIRECT_REASON=1,SPID_VALUE=1,SERVING_RSRP=1,SERVING_RSRQ=1,NEIGHBOR_RSRP=1,NEIGHBOR_RSRQ=1";

        ApEventBean apEventBean = createEventBean("INTERNAL_EVENT_UE_MOBILITY_EVAL", "11b", event);
        System.out.println(apEventBean.getAbsoluteName());
        System.out.println(apEventBean.getCSVString());

        event = "2012-05-21 16:06:30,subNetwork=abc,ne=lte01,name=lte01,version=12b,ENODEB_ID_MACRO_ENODEB_ID=123456789,ENODEB_ID_HOME_ENODEB_ID=5678943231,IMSI=310150123456789,IMEISV=03,GUMMEI_PLMN_IDENTITY=3456624,GUMMEI_MME_GROUP_ID=6673452,GUMMEI_MME_CODE=78,MME_UE_S1AP_ID=1234,ENB_UE_S1AP_ID=567";
        apEventBean = createEventBean("CTUM", event);
        System.out.println(apEventBean.getAbsoluteName());
        System.out.println(apEventBean.getCSVString());
    }

}
