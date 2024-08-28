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
package com.ericsson.mmepool.testsuite.util;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class DateFormatter {

	private static java.text.DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS");
	
	public static String format(long timestamp){
		return dateFormatter.format(timestamp);
	}
	
	 
	public static void setTimeZone(TimeZone timeZone) {
		dateFormatter.setTimeZone(timeZone);
	}
}
