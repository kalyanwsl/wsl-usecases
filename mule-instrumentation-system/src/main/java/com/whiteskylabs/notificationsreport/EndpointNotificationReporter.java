package com.whiteskylabs.notificationsreport;

import java.io.IOException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.mule.context.notification.EndpointMessageNotification;

import com.whiteskylabs.common.InstrumentationConstants;
import com.whiteskylabs.loggermanager.InstrumentationBO;
import com.whiteskylabs.loggermanager.InstrumentationLoggerFactory;
import com.whiteskylabs.loggermanager.InstrumentationProperties;

/**
 *	Log component data when Endpoint is invoked. 
 */
public class EndpointNotificationReporter extends InstrumentationProperties {

	private static Logger log = Logger
			.getLogger(EndpointNotificationReporter.class.getName());
	
	/** log Endpoint data when it is invoked  
	 * @param endnotification Endpoint notification object
	 * @throws IOException
	 * @throws Exception
	 */
	public void logEndpointNotificationReport(
			EndpointMessageNotification endnotification) throws IOException,
			Exception {

		// Get Endpoint notification details.
		String endpointClassName = endnotification.getImmutableEndpoint()
				.getName();
		String flowName = endnotification.getFlowConstruct().getName();
		Date timeStamp = new Date(endnotification.getTimestamp());
		String messageID = endnotification.getSource().getUniqueId();
		String payload = endnotification.getSource().getPayloadAsString();
		String actionName = endnotification.getActionName();

		// Prepare Instrumentation Object with Endpoint notification data.
		InstrumentationBO instrumentationBO = new InstrumentationBO();

		instrumentationBO.setComponent(endpointClassName);
		instrumentationBO.setFlowName(flowName);
		instrumentationBO.setTimeStamp(timeStamp.toString());
		instrumentationBO.setMessageID(messageID);
		instrumentationBO.setActionName(actionName);
		
		// Set payload to instrumentaion object if payload flag is enabled.
		if (Boolean
				.parseBoolean(getPropValue(InstrumentationConstants.IS_PAYLOAD_LOGGING_ENABLED))) {
			instrumentationBO.setPayload(payload);
		}

		InstrumentationLoggerFactory instrumentationLoggerFactory = new InstrumentationLoggerFactory();
		log.info(instrumentationLoggerFactory.getLogMessage(instrumentationBO));

	}

}
