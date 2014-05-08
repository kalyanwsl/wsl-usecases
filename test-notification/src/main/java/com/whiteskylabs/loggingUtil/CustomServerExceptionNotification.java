package com.whiteskylabs.loggingUtil;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.mule.api.context.notification.ServerNotification;
import org.mule.api.context.notification.ServerNotificationListener;

/**
 * @author Mohammad Rafiq
 * 
 */

public class CustomServerExceptionNotification implements
		ServerNotificationListener<ServerNotification>{

	
	List<Object> notificationArrayL = new ArrayList<Object>();
	private static Logger log = Logger.getLogger(CustomServerExceptionNotification.class.getName());

	public void onNotification( ServerNotification notification) {
		log.info("--------ServerNotificationListener------------------");
		log.info("getActionName " + notification.getActionName());
		log.info("getType " + notification.getType());
		log.info("getClass " + notification.getClass());
		log.debug("getSource " + notification.getSource().toString());
		
		
		log.info("EVENT_NAME " + notification.EVENT_NAME);
		
		log.info("--------------------------");
	}

	/*@Override
	public void onNotification(ServerNotification notification) {
		// TODO Auto-generated method stub
		
	}*/
	
}
