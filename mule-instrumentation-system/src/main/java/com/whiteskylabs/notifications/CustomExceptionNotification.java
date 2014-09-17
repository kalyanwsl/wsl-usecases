package com.whiteskylabs.notifications;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.mule.api.context.notification.ExceptionNotificationListener;
import org.mule.context.notification.ExceptionNotification;

import com.whiteskylabs.notificationsreport.ExceptionNotificationReporter;

/**
 *	Listener class meant for capturing when Exception(s) occurred.
 */
public class CustomExceptionNotification extends ExceptionNotificationReporter
		implements ExceptionNotificationListener<ExceptionNotification> {

	/* Invokes when an Exception event is occurred
	 * (non-Javadoc)
	 * @see org.mule.api.context.notification.ServerNotificationListener#onNotification(org.mule.api.context.notification.ServerNotification)
	 */
	@Override
	public void onNotification(
			org.mule.context.notification.ExceptionNotification execptionNotification) {

		// initiates exceptions to log
		try {
			logExceptionNotificationReport(execptionNotification);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
