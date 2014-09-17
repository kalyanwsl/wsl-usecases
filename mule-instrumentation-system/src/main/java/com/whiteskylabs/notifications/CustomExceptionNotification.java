package com.whiteskylabs.notifications;

import org.apache.log4j.Logger;
import org.mule.api.context.notification.ExceptionNotificationListener;
import org.mule.context.notification.ExceptionNotification;

import com.whiteskylabs.exceptions.InstrumentationException;
import com.whiteskylabs.notificationsreport.ExceptionNotificationReporter;

/**
 *	Initializes when Exception is occurred.
 */
public class CustomExceptionNotification extends ExceptionNotificationReporter
		implements ExceptionNotificationListener<ExceptionNotification> {

	private static Logger log = Logger
			.getLogger(CustomExceptionNotification.class.getName());
	@Override
	public void onNotification(
			org.mule.context.notification.ExceptionNotification execptionNotification) {

		// initiates exceptions to log
		try {
			logExceptionNotificationReport(execptionNotification);
		} catch (InstrumentationException ie) {
//			ie.printStackTrace();
			log.error(ie.getMessage(),ie);
		}
			
		
	}
}
