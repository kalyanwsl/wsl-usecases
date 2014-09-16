package com.whiteskylabs.notifications;

import java.io.IOException;

import org.mule.api.context.notification.EndpointMessageNotificationListener;
import org.mule.context.notification.EndpointMessageNotification;

import com.whiteskylabs.common.InstrumentationConstants;
import com.whiteskylabs.notificationsreport.EndpointNotificationReporter;

public class CustomEndpointNotification extends EndpointNotificationReporter implements
		EndpointMessageNotificationListener<EndpointMessageNotification> {
	
	@Override
	public void onNotification(EndpointMessageNotification endnotification) {

		try {
			
			// If Endpoint component logging flag is enabled 
			if (Boolean.valueOf(getPropValue(InstrumentationConstants.IS_ENDPOINT_LOGGING_ENABLED))) {
				logEndpointNotificationReport(endnotification);
				
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
