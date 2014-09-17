package com.whiteskylabs.notifications;

import org.apache.log4j.Logger;
import org.mule.api.context.notification.MessageProcessorNotificationListener;
import org.mule.context.notification.MessageProcessorNotification;

import com.whiteskylabs.common.InstrumentationConstants;
import com.whiteskylabs.exceptions.InstrumentationException;
import com.whiteskylabs.notificationsreport.MessageProcessorNotificationReporter;

/**
 *	Initializes when Message Processor component is invoked.
 */
public class CustomMessageProcessorNotification extends
		MessageProcessorNotificationReporter implements
		MessageProcessorNotificationListener<MessageProcessorNotification> {

	private static Logger log = Logger
			.getLogger(CustomMessageProcessorNotification.class.getName());
	
	@Override
	public void onNotification(MessageProcessorNotification mpnotification) {

		try {
			// If Message processor logging flag enabled and
			// discard if message processor is an endpoint.
			if (Boolean
					.parseBoolean(getPropValue(InstrumentationConstants.IS_MESSAGE_PROCESSOR_LOGGING_ENABLED))
					&& !mpnotification
							.getProcessor()
							.toString()
							.contains(
									InstrumentationConstants.DEFAULT_OUTBOUND_ENDPOINT)) {
				logMessageProcessorReport(mpnotification);

			}
		} catch (InstrumentationException ie) {
//			ie.printStackTrace();
			log.error(ie.getMessage(),ie);
		}
	}
}
