package com.whiteskylabs.notifications;

import java.util.Date;

import org.apache.log4j.Logger;
import org.mule.api.context.notification.ExceptionNotificationListener;
import org.mule.context.notification.ExceptionNotification;

public class CustomExceptionNotification implements ExceptionNotificationListener<ExceptionNotification>{
	
	private CustomEndpointNotification messageId;
	private CustomMessageProcessorNotification flowName;
	
	private static Logger log = Logger.getLogger(CustomExceptionNotification.class.getName());
	
	@Override
	public void onNotification(
			org.mule.context.notification.ExceptionNotification excenotification) {

		
		Date invocationTime = new Date(excenotification.getTimestamp());
		String exceptionMessge = excenotification.getException().getMessage();
		Throwable exception = excenotification.getException();
		
		if(log.isDebugEnabled()){
			log.debug("Exception occured in flow '"+flowName.getFlowName()+"' at "+invocationTime+" for Message ID '"+messageId.getmessageId()+"'and Exception is "+exception.getStackTrace());
		}
		else if(log.isInfoEnabled()){
			
			StackTraceElement[] exceptionTrace=exception.getStackTrace();
			for (StackTraceElement i : exceptionTrace)
			{
				log.info("############ Exception Trace #############");
			    log.info("exception is "+i.toString());
			    log.info("############ Exception Trace #############");
			}
			log.info("Exception occured in flow '"+flowName.getFlowName()+"' at "+invocationTime+" for Message ID '"+messageId.getmessageId()+"'and Exception is "+exception.getStackTrace());
		}

		
	}

	public CustomEndpointNotification getMessageId() {
		return messageId;
	}

	public void setMessageId(CustomEndpointNotification messageId) {
		this.messageId = messageId;
	}

	public CustomMessageProcessorNotification getFlowName() {
		return flowName;
	}

	public void setFlowName(CustomMessageProcessorNotification flowName) {
		this.flowName = flowName;
	}

}
