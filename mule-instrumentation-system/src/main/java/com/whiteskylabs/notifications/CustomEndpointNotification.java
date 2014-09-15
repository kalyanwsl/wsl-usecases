package com.whiteskylabs.notifications;

import java.util.Date;

import org.apache.log4j.Logger;
import org.mule.api.context.notification.EndpointMessageNotificationListener;
import org.mule.context.notification.EndpointMessageNotification;

import com.whiteskylabs.loggermanager.LogMessageFormat;

public class CustomEndpointNotification implements
		EndpointMessageNotificationListener<EndpointMessageNotification> {
	private String messageId;
	private String enablePayload;
	private String enableEndpoint;
	private String isJSONFormat;
	private String isXMLFormat;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getEnablePayload() {
		return enablePayload;
	}

	public String getEnableEndpoint() {
		return enableEndpoint;
	}

	public void setEnableEndpoint(String enableEndpoint) {
		this.enableEndpoint = enableEndpoint;
	}

	public void setEnablePayload(String enablePayload) {
		this.enablePayload = enablePayload;
	}

	public String getmessageId() {
		return messageId;
	}

	public void setmessageId(String messageId) {
		this.messageId = messageId;
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
			.getLogger(CustomEndpointNotification.class.getName());

	@Override
	public void onNotification(EndpointMessageNotification endnotification) {

		if (enableEndpoint.equalsIgnoreCase("true")) {

			String endpointClassName = endnotification.getImmutableEndpoint()
					.getName();
			String flowName = endnotification.getFlowConstruct().getName();
			Date timeStamp = new Date(endnotification.getTimestamp());
			messageId = endnotification.getSource().getUniqueId();
			setmessageId(messageId);

			String payload = (String) endnotification.getSource().getPayload();
			LogMessageFormat logMessageFormat = new LogMessageFormat();
			
			if (enablePayload.equalsIgnoreCase("true")) {
				if(isJSONFormat.equalsIgnoreCase("true")){
					log.info(logMessageFormat.getJSONLogMessage(endpointClassName,
							messageId, timeStamp.toString(), flowName, payload, true));
				}
				else if(isXMLFormat.equalsIgnoreCase("true")){
					log.info(logMessageFormat.getXMLLogMessage(endpointClassName,
							messageId, timeStamp.toString(), flowName, payload, true));
				}
			} else if (getEnablePayload() == "false") {
				if(isJSONFormat.equalsIgnoreCase("true")){
					log.info(logMessageFormat.getJSONLogMessage(endpointClassName,
							messageId, timeStamp.toString(), flowName, payload, false));
				}
				else if(isXMLFormat.equalsIgnoreCase("true")){
					log.info(logMessageFormat.getXMLLogMessage(endpointClassName,
							messageId, timeStamp.toString(), flowName, payload, false));
				}
			}
		}

	}
}
