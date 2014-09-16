package com.whiteskylabs.notifications;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.mule.api.context.notification.ExceptionNotificationListener;
import org.mule.context.notification.ExceptionNotification;

import com.whiteskylabs.notificationsreport.ExceptionNotificationReporter;

/**
 *	Initializes when Exception is occurred.
 */
public class CustomExceptionNotification extends ExceptionNotificationReporter
		implements ExceptionNotificationListener<ExceptionNotification> {

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
