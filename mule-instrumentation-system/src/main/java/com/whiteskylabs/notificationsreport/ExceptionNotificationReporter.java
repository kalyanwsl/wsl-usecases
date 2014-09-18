package com.whiteskylabs.notificationsreport;

import org.apache.log4j.Logger;
import org.mule.context.notification.ExceptionNotification;

import com.google.common.base.Throwables;
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
	 * Log Exeception's details when an Exception is occurred.
	 * Exception details are 
	 * 1. Exception Message
	 * 2. Cause of Exception 
	 * 3. PrintStackTrace 
	 * @param execNotificationObj
	 *            Exception Notification object
	 * @throws InstrumentationException
	 */
	public void logExceptionNotificationReport(
			ExceptionNotification execNotificationObj) throws InstrumentationException {

		System.out.println("$$$$$$$$$$$$$$$$4"+this.getClass().getName()+"$$$$$$$$$$$$4");
		String execptionMessage = execNotificationObj.getException()
				.getMessage();
		Throwable execptionCause = execNotificationObj.getException()
				.getCause();
		String execptionStackTrace = Throwables
				.getStackTraceAsString(execNotificationObj.getException());

		// Prepare Instrumentation Object with Endpoint notification data.
		InstrumentationBO instrumentationBO = new InstrumentationBO();

		
		instrumentationBO.setExecptionMessage(execptionMessage);
		if(execptionCause != null){
			instrumentationBO.setExecptionCause(execptionCause.toString());
		}
		instrumentationBO.setExecptionStackTrace(execptionStackTrace);
		
		InstrumentationLoggerFactory instrumentationLoggerFactory = new InstrumentationLoggerFactory();
		log.error(instrumentationLoggerFactory.getLogMessage(instrumentationBO));

	}

}
