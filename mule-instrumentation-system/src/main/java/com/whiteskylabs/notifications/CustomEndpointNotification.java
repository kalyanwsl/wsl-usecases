package com.whiteskylabs.notifications;

import org.mule.api.context.notification.EndpointMessageNotificationListener;
import org.mule.context.notification.EndpointMessageNotification;

import com.whiteskylabs.notificationsreport.EndpointNotificationReporter;

public class CustomEndpointNotification extends EndpointNotificationReporter implements
		EndpointMessageNotificationListener<EndpointMessageNotification> {



	@Override
	public void onNotification(EndpointMessageNotification endnotification) {
			logEndpointNotificationReport(endnotification);
	}
}
