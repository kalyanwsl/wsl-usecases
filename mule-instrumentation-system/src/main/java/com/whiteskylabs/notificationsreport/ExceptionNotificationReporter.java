package com.whiteskylabs.notificationsreport;

import org.apache.log4j.Logger;
import org.mule.context.notification.ExceptionNotification;

import com.whiteskylabs.exceptions.InstrumentationException;
import com.whiteskylabs.loggermanager.InstrumentationBO;
import com.whiteskylabs.loggermanager.InstrumentationLoggerFactory;
import com.whiteskylabs.loggermanager.InstrumentationProperties;

/**
 *	Log details when Exception is occured. 
 */
public class ExceptionNotificationReporter extends InstrumentationProperties {

	private static Logger log = Logger
			.getLogger(ExceptionNotificationReporter.class.getName());

	
	/**
	 * Log Exeception when an Exception is occurred.
	 * 
	 * @param execNotificationObj
	 *            Exception Notification object
	 * @throws InstrumentationException
	 */
	public void logExceptionNotificationReport(
			ExceptionNotification execNotificationObj) throws InstrumentationException {

		String execptionMessage = execNotificationObj.getException()
				.getMessage();
		Throwable execptionCause = execNotificationObj.getException()
				.getCause();
		StackTraceElement[] stackTrace = execNotificationObj.getException()
				.getStackTrace();
		String execptionStackTrace = "";

		// Prepare Instrumentation Object with Endpoint notification data.
		InstrumentationBO instrumentationBO = new InstrumentationBO();

		// Extracting stackTraces into single string
		for (StackTraceElement stackTraceElement : stackTrace) {
			execptionStackTrace += stackTraceElement + "\n";
		}

		instrumentationBO.setExecptionCause(execptionCause.toString());
		instrumentationBO.setExecptionMessage(execptionMessage);
		instrumentationBO.setExecptionStackTrace(execptionStackTrace);

		InstrumentationLoggerFactory instrumentationLoggerFactory = new InstrumentationLoggerFactory();
		log.error(instrumentationLoggerFactory.getLogMessage(instrumentationBO));

	}

}
