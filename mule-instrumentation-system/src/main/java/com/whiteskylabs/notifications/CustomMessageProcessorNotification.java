package com.whiteskylabs.notifications;

import org.mule.api.context.notification.MessageProcessorNotificationListener;
import org.mule.context.notification.MessageProcessorNotification;

import com.whiteskylabs.notificationsreport.MessageProcessorNotificationReporter;

public class CustomMessageProcessorNotification extends MessageProcessorNotificationReporter implements
		MessageProcessorNotificationListener<MessageProcessorNotification> {

	

	@Override
	public void onNotification(MessageProcessorNotification mpnotification) {
		logMessageProcessorReport(mpnotification);
	}
}
