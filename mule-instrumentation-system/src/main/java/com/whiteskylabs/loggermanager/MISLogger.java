package com.whiteskylabs.loggermanager;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="mis-logger")
public class MISLogger {

	String flowName;
	String component;
	String messageID;
	String timeStamp;
	
//	@XmlJavaTypeAdapter(value=CDATAAdapter.class)
	String payload;
	
	public String getFlowName() {
		return flowName;
	}
	@XmlElement
	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getComponent() {
		return component;
	}
	@XmlElement
	public void setComponent(String component) {
		this.component = component;
	}
	
	public String getMessageID() {
		return messageID;
	}
	@XmlElement
	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}
	
	public String getTimeStamp() {
		return timeStamp;
	}
	@XmlElement
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public String getPayload() {
		return payload;
	}
	@XmlElement
	public void setPayload(String payload) {
		this.payload = payload;
	}
	
	
	
}
