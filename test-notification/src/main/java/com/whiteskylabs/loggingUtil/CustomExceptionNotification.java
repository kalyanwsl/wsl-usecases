package com.whiteskylabs.loggingUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mule.api.context.notification.ExceptionNotificationListener;
import org.mule.context.notification.ExceptionNotification;

/**
 * @author Vikram Uppala
 * 
 */

public class CustomExceptionNotification implements ExceptionNotificationListener<ExceptionNotification> 
		{

		
		private static Logger log = Logger.getLogger(CustomExceptionNotification.class);

				public void onNotification(final ExceptionNotification notification) 
				{
					
					Logutil logutil= new Logutil();
				
					log.debug(logutil.getExceptionLogInfomation(notification));
					
				}
				
}
