package com.whiteskylabs.notificationsreport;

import java.util.Date;

import org.apache.log4j.Logger;
import org.mule.context.notification.MessageProcessorNotification;

import com.whiteskylabs.common.InstrumentationConstants;
import com.whiteskylabs.loggermanager.InstrumentationBO;
import com.whiteskylabs.loggermanager.InstrumentationLoggerFactory;
import com.whiteskylabs.loggermanager.InstrumentationProperties;

public class MessageProcessorNotificationReporter extends
		InstrumentationProperties {

	private static Logger log = Logger
			.getLogger(MessageProcessorNotificationReporter.class.getName());

	public void logMessageProcessorReport(
			MessageProcessorNotification mpnotification) throws Exception {

		String messageProcessorName = mpnotification.getProcessor().getClass()
				.getName();
		String flowName = mpnotification.getSource().getFlowConstruct()
				.getName();
		Date timeStamp = new Date(mpnotification.getTimestamp());
		String messageID = mpnotification.getSource().getMessage()
				.getUniqueId();
		String payload = mpnotification.getSource().getMessage()
				.getPayloadAsString();
		String actionName = mpnotification.getActionName();
		
		//Prepare Instrumentation Object with Message Processor notification data.
		InstrumentationBO instrumentationBO = new InstrumentationBO();

		instrumentationBO.setComponent(messageProcessorName);
		instrumentationBO.setFlowName(flowName);
		instrumentationBO.setTimeStamp(timeStamp.toString());
		instrumentationBO.setMessageID(messageID);
		instrumentationBO.setActionName(actionName);
		
		if (Boolean
				.parseBoolean(getPropValue(InstrumentationConstants.IS_PAYLOAD_LOGGING_ENABLED))) {
			instrumentationBO.setPayload(payload);
		}

		InstrumentationLoggerFactory instrumentationLoggerFactory = new InstrumentationLoggerFactory();

		if (log.isDebugEnabled()) { 
			log.debug(instrumentationLoggerFactory.getLogMessage(instrumentationBO));
			
		} else if (log.isInfoEnabled()
				&& mpnotification.getActionName().equals(
						InstrumentationConstants.IS_MESSAGE_PROCESSOR_PRE_INVOKE)) {
			log.info(instrumentationLoggerFactory.getLogMessage(instrumentationBO));
			
		
			
		}

	}

}
