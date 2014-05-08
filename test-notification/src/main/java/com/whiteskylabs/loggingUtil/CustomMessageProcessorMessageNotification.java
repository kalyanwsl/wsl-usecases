package com.whiteskylabs.loggingUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mule.api.MuleException;
import org.mule.api.context.notification.MessageProcessorNotificationListener;
import org.mule.context.notification.MessageProcessorNotification;

/**
 * @author Vikram Uppala
 * 
 */

@SuppressWarnings("unused")
public class CustomMessageProcessorMessageNotification implements
		MessageProcessorNotificationListener<MessageProcessorNotification> {

	private static Logger log = Logger
			.getLogger(CustomMessageProcessorMessageNotification.class);

	/*
	 * The below method(onNotification) gets invoked when ever a Message
	 * processor is invoked in the mule flow.
	 */
	@Override
	public void onNotification(final MessageProcessorNotification notification)
	{
		Logutil logutil = new Logutil();
		

		if(Logger.getRootLogger().getLevel().toString().equals("INFO"))
		{
			log.info(logutil.getMPLogInfomationInfo(notification));
		}
		
		else
		{
			log.debug(logutil.getMPLogInfomationInDebug(notification));
		}
		
	}


}
