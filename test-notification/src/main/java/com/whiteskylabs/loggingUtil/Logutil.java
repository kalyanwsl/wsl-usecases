package com.whiteskylabs.loggingUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.mule.api.MuleException;
import org.mule.api.context.notification.ServerNotification;
import org.mule.context.notification.ExceptionNotification;
import org.mule.context.notification.MessageProcessorNotification;

public class Logutil
{
	
	
	private String instanceId;
	private String flowName;
	private String loggingData;
	
	public String getInstanceId() {
		return instanceId;
	}
	public String getFlowName() {
		return flowName;
	}
	
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}
	
	

	public String getMPLogInfomationInDebug(MessageProcessorNotification notification) {

		instanceId=notification.getSource().getId();
		flowName=notification.getSource().getFlowConstruct().getName();
				
		loggingData +="::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::" +"\n";
		loggingData += "flowName:"+ flowName + "\n";
		loggingData += "instanceId: " + instanceId + "\n";
		loggingData += "MessageProcessorName:" + notification.getProcessor()+"\n";
		loggingData += "Action name for the Message Processor:"+ notification.getActionName()+"\n";

		try {
			loggingData += "Payload"+ notification.getSource().getMessageAsString()+"\n";
		} catch (MuleException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		loggingData+="Current time: "+ getCurrentTime()+"\n";
		loggingData +="::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::";
		return loggingData;
		
	}
	
	public String getMPLogInfomationInfo(MessageProcessorNotification notification)
	{
		
		instanceId=notification.getSource().getId();
		
		flowName=notification.getSource().getFlowConstruct().getName();
		
		loggingData +="::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::" +"\n";
		loggingData += "flowName:"+ flowName + "\n";
		loggingData += "instanceId: " + instanceId + "\n";
		loggingData += "MessageProcessorName:" + notification.getProcessor()+"\n";
		loggingData += "Action name for the Message Processor:"+ notification.getActionName()+"\n";		
		loggingData+="Current time: "+ getCurrentTime()+"\n";
		loggingData +="::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::";
		return loggingData;
	}
	
	
	/**
	 * @param notification
	 * @return
	 */
	public String getExceptionLogInfomation(ExceptionNotification notification) {
		
		loggingData += "Exception Occured during the time " +getCurrentTime()+ "\n";
		loggingData +="Type of Exception"+notification.getType()+ "\n";
		loggingData +=":::::::::::::::::Exception Details as Below ::::::::::::::::::::::::::::";
		
		
		
		return loggingData;
	}
	
	/**
	 * @return
	 */
	private String getCurrentTime() {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a");
		String formattedDate = sdf.format(date);
		return formattedDate;
	}
}
