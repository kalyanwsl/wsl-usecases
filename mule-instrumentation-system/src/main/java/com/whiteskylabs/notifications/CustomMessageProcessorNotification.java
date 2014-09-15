package com.whiteskylabs.notifications;

import java.util.Date;

import org.apache.log4j.Logger;
import org.mule.api.context.notification.MessageProcessorNotificationListener;
import org.mule.context.notification.MessageProcessorNotification;

import com.whiteskylabs.loggermanager.LogMessageFormat;

public class CustomMessageProcessorNotification implements
		MessageProcessorNotificationListener<MessageProcessorNotification> {

	private String flowName;
	private String enablePayload;
	private String enableMessageProcessor;
	private String isJSONFormat;
	private String isXMLFormat;

	public String getEnableMessageProcessor() {
		return enableMessageProcessor;
	}

	public void setEnableMessageProcessor(String enableMessageProcessor) {
		this.enableMessageProcessor = enableMessageProcessor;
	}

	public String getEnablePayload() {
		return enablePayload;
	}

	public void setEnablePayload(String enablePayload) {
		this.enablePayload = enablePayload;
	}

	public String getEnableMessageProcessorLogging() {
		return enableMessageProcessorLogging;
	}

	public void setEnableMessageProcessorLogging(
			String enableMessageProcessorLogging) {
		this.enableMessageProcessorLogging = enableMessageProcessorLogging;
	}

	private String enableMessageProcessorLogging;

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getIsJSONFormat() {
		return isJSONFormat;
	}

	public void setIsJSONFormat(String isJSONFormat) {
		this.isJSONFormat = isJSONFormat;
	}

	public String getIsXMLFormat() {
		return isXMLFormat;
	}

	public void setIsXMLFormat(String isXMLFormat) {
		this.isXMLFormat = isXMLFormat;
	}

	private static Logger log = Logger
			.getLogger(CustomMessageProcessorNotification.class.getName());

	@Override
	public void onNotification(MessageProcessorNotification notification) {

		if (enableMessageProcessor.equalsIgnoreCase("true")
				&& !notification.getProcessor().toString()
						.contains("DefaultOutboundEndpoint")) {

			String messageProcessorName = notification.getProcessor()
					.getClass().getName();
			String flowName = notification.getSource().getFlowConstruct()
					.getName();
			setFlowName(flowName);
			Date timeStamp = new Date(notification.getTimestamp());
			String messageId = notification.getSource().getMessage()
					.getUniqueId();
			String payload = (String) notification.getSource().getMessage()
					.getPayload();
			LogMessageFormat logMessageFormat = new LogMessageFormat();

			if (log.isDebugEnabled() && enablePayload.equalsIgnoreCase("true")) {

				if (isJSONFormat.equalsIgnoreCase("true")) {
					log.debug(logMessageFormat.getJSONLogMessage(
							messageProcessorName, messageId,
							timeStamp.toString(), flowName, payload, true));
				} else if (isXMLFormat.equalsIgnoreCase("true")) {
					log.debug(logMessageFormat.getXMLLogMessage(
							messageProcessorName, messageId,
							timeStamp.toString(), flowName, payload, true));
				}
			} else if (log.isDebugEnabled()
					&& !(enablePayload.equalsIgnoreCase("true"))) {

				if (isJSONFormat.equalsIgnoreCase("true")) {
					log.debug(logMessageFormat.getJSONLogMessage(
							messageProcessorName, messageId,
							timeStamp.toString(), flowName, payload, false));
				} else if (isXMLFormat.equalsIgnoreCase("true")) {
					log.debug(logMessageFormat.getXMLLogMessage(
							messageProcessorName, messageId,
							timeStamp.toString(), flowName, payload, false));
				}
			} else if (log.isInfoEnabled()
					&& enablePayload.equalsIgnoreCase("true")) {
				if (notification.getActionName().equals(
						"message processor pre invoke")) {
					if (isJSONFormat.equalsIgnoreCase("true")) {
						log.info(logMessageFormat.getJSONLogMessage(
								messageProcessorName, messageId,
								timeStamp.toString(), flowName, payload, true));
					} else if (isXMLFormat.equalsIgnoreCase("true")) {
						log.info(logMessageFormat.getXMLLogMessage(
								messageProcessorName, messageId,
								timeStamp.toString(), flowName, payload, true));
					}
				}
			} else if (log.isInfoEnabled()
					&& !(enablePayload.equalsIgnoreCase("true"))) {
				if (notification.getActionName().equals(
						"message processor pre invoke")) {
					if (isJSONFormat.equalsIgnoreCase("true")) {
						log.info(logMessageFormat.getJSONLogMessage(
								messageProcessorName, messageId,
								timeStamp.toString(), flowName, payload, false));
					} else if (isXMLFormat.equalsIgnoreCase("true")) {
						log.info(logMessageFormat.getXMLLogMessage(
								messageProcessorName, messageId,
								timeStamp.toString(), flowName, payload, false));
					}
				}
			}
		}
	}
}
