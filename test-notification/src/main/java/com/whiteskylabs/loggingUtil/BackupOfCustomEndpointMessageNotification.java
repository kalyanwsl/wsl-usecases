package com.whiteskylabs.loggingUtil;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.mule.api.context.notification.EndpointMessageNotificationListener;
import org.mule.context.notification.EndpointMessageNotification;

/**
 * @author Mohammad Rafiq
 * 
 */

public class BackupOfCustomEndpointMessageNotification implements
EndpointMessageNotificationListener<EndpointMessageNotification> {

	
	List<Object> notificationArrayL = new ArrayList<Object>();
	private static Logger log = Logger.getLogger(BackupOfCustomEndpointMessageNotification.class.getName());

	@Override
	public void onNotification(final EndpointMessageNotification notification) {
		
		log.info("--------EndpointMessageNotification------------------");
		
		if(notification.getActionName().equals("receive")){
			log.info(":::::::::::::::::Start of Flow:::::::::::::::::::::::::::");
		}
		
		
		log.info("getActionName " + notification.getActionName());
		log.info("getAction " + notification.getAction());
		log.info("getType " + notification.getType());//Always 'trace'
		log.info("getClass " + notification.getClass());//Always 'org.mule.context.notification.EndpointMessageNotification'
		log.info("getSource " + notification.getSource().toString());//Prints Complete current Mule message Object.
		log.info("getSource.getPayload() " + notification.getSource().getPayload());//Payload-- differs based on Endpoint and action. 
		log.info("getSource.getMuleContext() " + notification.getSource().getMuleContext());//Current MuleContext object
		log.info("notification.getFlowConstruct().getName() "+notification.getFlowConstruct().getName());//Give current Flow Name
		log.info("getSource.getMuleContext().getStatistics()" + notification.getSource().getMuleContext().getStatistics());
		log.info("getSource.getMuleContext().getUniqueIdString() " + notification.getSource().getMuleContext().getUniqueIdString());//UniqueID
		log.info("getSource.getMuleContext().getStartDate() " + notification.getSource().getMuleContext().getStartDate());//Prints long number for server start time
		log.info("getSource.getMuleContext()..getRegistry().getRegistryId().toString() " + notification.getSource().getMuleContext().getRegistry().getRegistryId().toString());
		
		log.info("notification.getSource().getMessageRootId()" + notification.getSource().getMessageRootId());
		log.info("notification.getSource().getMuleContext().getConfiguration().getDomainId()" + notification.getSource().getMuleContext().getConfiguration().getDomainId());
		log.info("notification.getSource().getMuleContext().getConfiguration().toString()" + notification.getSource().getMuleContext().getConfiguration().toString());
		//log.info("notification.getSource().getMuleContext().getConfiguration().getDomainId()" + notification.getSource().getMuleContext().get);
		
		org.mule.DefaultMuleContext muleContext = (org.mule.DefaultMuleContext) notification.getSource().getMuleContext();
		muleContext.getStatistics();
		
		log.info("getSource.getInboundProperty(\"MULE_ORIGINATING_ENDPOINT\") " + notification.getSource().getInboundProperty("MULE_ORIGINATING_ENDPOINT"));
		log.info("getSource.getInboundProperty(\"http.context.uri\") " + notification.getSource().getInboundProperty("http.context.uri"));
		log.info("getSource.getOutboundProperty(\"MULE_ENDPOINT\") " + notification.getSource().getOutboundProperty("MULE_ENDPOINT"));
		
		
		log.info("EVENT_NAME " + notification.EVENT_NAME);
	
		if(notification.getActionName().equals("response")){
			log.info(":::::::::::::::::End of Flow:::::::::::::::::::::::::::");
		}	
		log.info("-----------------------------------------------------");
	}
	
}


