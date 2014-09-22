package com.whiteskylabs.notificationsreport;

import java.util.Date;

import org.apache.log4j.Logger;
import org.mule.context.notification.EndpointMessageNotification;

import com.whiteskylabs.common.ComponentPayloadHandler;
import com.whiteskylabs.common.InstrumentationConstants;
import com.whiteskylabs.exceptions.InstrumentationException;
import com.whiteskylabs.loggermanager.InstrumentationBO;
import com.whiteskylabs.loggermanager.InstrumentationLoggerFactory;
import com.whiteskylabs.loggermanager.InstrumentationProperties;

/**
 * Log component data when Endpoint is invoked.
 */
public class EndpointNotificationReporter extends InstrumentationProperties {

	private static Logger log = Logger
			.getLogger(EndpointNotificationReporter.class.getName());

	/**
	 * log Endpoint data when it is invoked
	 * 
	 * @param endnotification
	 *            Endpoint notification object
	 * @throws InstrumentationException
	 */
	public void logEndpointNotificationReport(
			EndpointMessageNotification endnotification)
			throws InstrumentationException {

		// Get Endpoint notification details.
		String endpointClassName = endnotification.getImmutableEndpoint()
				.getName();
		String flowName = endnotification.getFlowConstruct().getName();
		Date timeStamp = new Date(endnotification.getTimestamp());
		String messageID = endnotification.getSource().getUniqueId();

		String actionName = endnotification.getActionName();

		// Prepare Instrumentation Object with Endpoint notification data.
		InstrumentationBO instrumentationBO = new InstrumentationBO();

		instrumentationBO.setMessageID(messageID);
		instrumentationBO.setFlowName(flowName);
		instrumentationBO.setComponent(endpointClassName);
		instrumentationBO.setActionName(actionName);
		instrumentationBO.setTimeStamp(timeStamp.toString());

		// Set payload to instrumentation object if payload flag is enabled.
		if (Boolean
				.parseBoolean(getPropValue(InstrumentationConstants.IS_PAYLOAD_LOGGING_ENABLED))) {

			ComponentPayloadHandler componentPayloadHandler = new ComponentPayloadHandler();

			// Get Endpoint component payload.
			String payload = componentPayloadHandler
					.getComponentPayloadAsString(endnotification.getSource()
							.getPayload());
			instrumentationBO.setPayload(payload);
		}

		InstrumentationLoggerFactory instrumentationLoggerFactory = new InstrumentationLoggerFactory();
		log.info(instrumentationLoggerFactory.getLogMessage(instrumentationBO));

	}

}
