package com.whiteskylabs.notificationsreport;

import java.util.Date;

import org.apache.log4j.Logger;
import org.mule.context.notification.MessageProcessorNotification;

import com.whiteskylabs.common.InstrumentationConstants;
import com.whiteskylabs.exceptions.InstrumentationException;
import com.whiteskylabs.loggermanager.InstrumentationBO;
import com.whiteskylabs.loggermanager.InstrumentationLoggerFactory;
import com.whiteskylabs.loggermanager.InstrumentationProperties;

/**
 *	Log component data when Message Processor is invoked. 
 */
public class MessageProcessorNotificationReporter extends
		InstrumentationProperties {

	private static Logger log = Logger
			.getLogger(MessageProcessorNotificationReporter.class.getName());

	/** Log Message Processor data when a message processor is invoked
	 * @param mpnotification Message Processor notification  object
	 * @throws InstrumentationException 
	 */
	public void logMessageProcessorReport(
			MessageProcessorNotification mpnotification) throws InstrumentationException {

		// Get Message Processor details.
		String messageProcessorName = mpnotification.getProcessor().getClass()
				.getName();
		String flowName = mpnotification.getSource().getFlowConstruct()
				.getName();
		Date timeStamp = new Date(mpnotification.getTimestamp());
		String messageID = mpnotification.getSource().getMessage()
				.getUniqueId();
		String actionName = mpnotification.getActionName();
		
		//Prepare Instrumentation Object with Message Processor notification data.
		InstrumentationBO instrumentationBO = new InstrumentationBO();

		instrumentationBO.setComponent(messageProcessorName);
		instrumentationBO.setFlowName(flowName);
		instrumentationBO.setTimeStamp(timeStamp.toString());
		instrumentationBO.setMessageID(messageID);
		instrumentationBO.setActionName(actionName);
		
		// Set payload to instrumentaion object if payload flag is enabled.
		if (Boolean
				.parseBoolean(getPropValue(InstrumentationConstants.IS_PAYLOAD_LOGGING_ENABLED))) {
				Object payload = mpnotification.getSource().getMessage()
						.getPayload();
				instrumentationBO.setPayload(payload.toString());
		}

		InstrumentationLoggerFactory instrumentationLoggerFactory = new InstrumentationLoggerFactory();

		// Log pre invoke and post invoke data of Message Processor at DEBUG level 
		if (log.isDebugEnabled()) { 
			log.debug(instrumentationLoggerFactory.getLogMessage(instrumentationBO));
		} 
		// Log only pre invoke data of Message Processor at INFO level
		else if (log.isInfoEnabled()
				&& mpnotification.getActionName().equals(
						InstrumentationConstants.IS_MESSAGE_PROCESSOR_PRE_INVOKE)) {
			log.info(instrumentationLoggerFactory.getLogMessage(instrumentationBO));
			
		}

	}

}
