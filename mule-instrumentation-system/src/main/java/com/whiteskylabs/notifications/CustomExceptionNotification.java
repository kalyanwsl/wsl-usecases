package com.whiteskylabs.notifications;

import org.apache.log4j.Logger;
import org.mule.api.context.notification.ExceptionNotificationListener;
import org.mule.context.notification.ExceptionNotification;

import com.whiteskylabs.exceptions.InstrumentationException;
import com.whiteskylabs.notificationsreport.ExceptionNotificationReporter;

/**
 *	Listener class meant for capturing when Exception(s) occurred.
 */
public class CustomExceptionNotification extends ExceptionNotificationReporter
		implements ExceptionNotificationListener<ExceptionNotification> {

	private static Logger log = Logger
			.getLogger(CustomExceptionNotification.class.getName());

	/* Invokes when an Exception event is occurred
	 * (non-Javadoc)
	 * @see org.mule.api.context.notification.ServerNotificationListener#onNotification(org.mule.api.context.notification.ServerNotification)
	 */
	@Override
	public void onNotification(
			org.mule.context.notification.ExceptionNotification execptionNotification) {

		// initiates exceptions to log
		try {
			System.out.println("############"+this.getClass().getName()+"#############");
			logExceptionNotificationReport(execptionNotification);
		} catch (InstrumentationException ie) {
			log.error(ie.getMessage(),ie);
		}
			
		
	}
}
