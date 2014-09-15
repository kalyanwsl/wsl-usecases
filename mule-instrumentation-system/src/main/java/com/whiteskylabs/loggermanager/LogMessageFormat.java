package com.whiteskylabs.loggermanager;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class LogMessageFormat {

	public String getXMLLogMessage(String component,
			String messageId, String timeStamp, String flowName, String payload, boolean isPayloadEnabled)
			{

		MISLogger misLogger = new MISLogger();
		String stripMessage = null;
		
		misLogger.setComponent(component);
		misLogger.setFlowName(flowName);
		misLogger.setMessageID(messageId);
		if(isPayloadEnabled){
			misLogger.setPayload(payload);
		}
		misLogger.setTimeStamp(timeStamp);

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(MISLogger.class);
		
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			StringWriter writer = new StringWriter();
			jaxbMarshaller.marshal(misLogger, writer);
			
			String xmlMessage = writer.toString();
			stripMessage = xmlMessage.substring(xmlMessage.indexOf('>', 1)+1, xmlMessage.length());
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return stripMessage;
	}

	public String getJSONLogMessage(String component,
			String messageId, String timeStamp, String flowName, String payload, boolean isPayloadEnabled) {

		JSONObject json = new JSONObject();
		
		json.put("flowName", flowName);
		json.put("component", component);
		json.put("messageID", messageId);
		json.put("timeStamp", timeStamp);
		if(isPayloadEnabled){
			json.put("payload", payload);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(json.toJSONString());
		String prettyJsonString = gson.toJson(je);
		
		return prettyJsonString;
	}
	
	public String getLogMessage(String component,
			String messageId, String timeStamp, String flowName, String payload, boolean isPayloadEnabled,String loggingFormat) {
		
		if(loggingFormat.equalsIgnoreCase("json"))
			return getJSONLogMessage(component,messageId,timeStamp,flowName,payload,isPayloadEnabled);
		else if (loggingFormat.equalsIgnoreCase("xml"))
			return getXMLLogMessage(component,messageId,timeStamp,flowName,payload,isPayloadEnabled);
		else
			return "invalid logging format.";
		
	}
	
}
