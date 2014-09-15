package com.whiteskylabs.notifications;

import java.io.IOException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.mule.api.context.notification.MessageProcessorNotificationListener;
import org.mule.context.notification.MessageProcessorNotification;

import com.whiteskylabs.loggermanager.LogMessageFormat;

public class CustomMessageProcessorNotification implements
		MessageProcessorNotificationListener<MessageProcessorNotification> {

	private InstrumentationProperties props;
	

	public InstrumentationProperties getProps() {
		return props;
	}

	public void setProps(InstrumentationProperties props) {
		this.props = props;
	}	
	private static Logger log = Logger
			.getLogger(CustomMessageProcessorNotification.class.getName());

	@Override
	public void onNotification(MessageProcessorNotification mpnotification) {


		try {
		
		if(props.getPropValue("enableMessageProcessorLogging").equalsIgnoreCase("true") && !mpnotification.getProcessor().toString().contains("DefaultOutboundEndpoint"))
		{
			String messageProcessorName = mpnotification.getProcessor().getClass().getName();
			String flowName = mpnotification.getSource().getFlowConstruct().getName();
			Date timeStamp = new Date(mpnotification.getTimestamp());
			String messageId = mpnotification.getSource().getMessage().getUniqueId();
			String payload =  mpnotification.getSource().getMessage().getPayloadAsString();
			
			LogMessageFormat logMessageFormat = new LogMessageFormat();
			
				if (log.isDebugEnabled())
					{
							log.debug(logMessageFormat.getLogMessage(messageProcessorName, messageId,
										timeStamp.toString(), flowName, payload,Boolean.valueOf(props
										.getPropValue("enablePayloadLogging")), props
										.getPropValue("loggingFormat")));
					}
				else if (log.isInfoEnabled() && mpnotification.getActionName().equals("message processor pre invoke"))
					{
					log.info(logMessageFormat.getLogMessage(
							messageProcessorName, messageId,
							timeStamp.toString(), flowName, payload, Boolean.valueOf(props
								.getPropValue("enablePayloadLogging")), props
								.getPropValue("loggingFormat")));
					}
			
			
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
