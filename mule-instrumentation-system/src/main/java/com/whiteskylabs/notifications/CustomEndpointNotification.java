package com.whiteskylabs.notifications;

import org.apache.log4j.Logger;
import org.mule.api.context.notification.EndpointMessageNotificationListener;
import org.mule.context.notification.EndpointMessageNotification;

import com.whiteskylabs.common.InstrumentationConstants;
import com.whiteskylabs.exceptions.InstrumentationException;
import com.whiteskylabs.notificationsreport.EndpointNotificationReporter;

/**
 *	Initializes when Endpoint component is invoked.
 */
public class CustomEndpointNotification extends EndpointNotificationReporter implements
		EndpointMessageNotificationListener<EndpointMessageNotification> {
	
	private static Logger log = Logger
			.getLogger(CustomEndpointNotification.class.getName());
	
	@Override
	public void onNotification(EndpointMessageNotification endnotification) {

		try {
			
			// If Endpoint component logging flag is enabled 
			if (Boolean.valueOf(getPropValue(InstrumentationConstants.IS_ENDPOINT_LOGGING_ENABLED))) {
				logEndpointNotificationReport(endnotification);
				
			}
		} catch (InstrumentationException  ie) {
			
//			ie.printStackTrace();
			log.error(ie.getMessage(),ie);
		}
	}
}
