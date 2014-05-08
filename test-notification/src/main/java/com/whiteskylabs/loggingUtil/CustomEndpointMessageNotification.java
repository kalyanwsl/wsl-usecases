package com.whiteskylabs.loggingUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.mule.api.context.notification.EndpointMessageNotificationListener;
import org.mule.context.notification.EndpointMessageNotification;

import org.mule.api.context.notification.MessageProcessorNotificationListener;

/**
 * @author Vikram Uppala
 * 
 */

@SuppressWarnings("unused")
public class CustomEndpointMessageNotification implements
EndpointMessageNotificationListener<EndpointMessageNotification> 
{

	public static String uniquieId=null;
	
	public static String getUniquieId() {
		return uniquieId;
	}

	public static void setUniquieId(String uniquieId) {
		CustomEndpointMessageNotification.uniquieId = uniquieId;
	}

	private static Logger log = Logger.getLogger(CustomEndpointMessageNotification.class.getName());

	@Override
	public void onNotification(final EndpointMessageNotification notification) {
		
		log.info(" the logger level is "+Logger.getRootLogger().getLevel().toString());
		
		uniquieId=notification.getSource().getUniqueId();
		
		if(Logger.getRootLogger().getLevel().toString().equals("INFO"))
		{
		
		if(notification.getActionName().equals("receive"))
		{
			log.info(":::Flow  "+notification.getFlowConstruct().getName()+" started at   "+currentDate()+" and its instance id is "+uniquieId+":::");
		}
		
			if(notification.getActionName().toString().startsWith("end"))
			{
				log.info("Endpoint " +notification.getEndpoint()+"  Processed Sucessfully and instance id is "+uniquieId);
			
			}
			 
	
		if(notification.getActionName().equals("response"))
		{
			log.info(":::Flow   "+notification.getFlowConstruct().getName()+" sucessfully completed at  "+currentDate()+" for the instance id "+uniquieId+":::");
		}
	}
	
}
	
	private String currentDate()
	{
		
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
	String formattedDate = sdf.format(date);
	return formattedDate;
	}
}


