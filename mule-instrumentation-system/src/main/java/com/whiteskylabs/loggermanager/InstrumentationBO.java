package com.whiteskylabs.loggermanager;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * POJO class of Instrumentation logger properties
 *
 */
@XmlRootElement(name="instrumentation-logger")
@XmlType (propOrder={"messageID","flowName","component","actionName","timeStamp","payload","execptionMessage","execptionCause","execptionStackTrace"})
public class InstrumentationBO implements Serializable{

	/**
	 * Serializable unique ID.
	 */
	private static final long serialVersionUID = 1L;
	String messageID;
	String flowName;
	String component;
	String actionName;
	String timeStamp;
	String payload;
	String execptionMessage;
	String execptionCause;
	String execptionStackTrace;
	
	
	public String getMessageID() {
		return messageID;
	}
	@XmlElement (name="MessageID")
	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}
	
	public String getFlowName() {
		return flowName;
	}
	@XmlElement (name="FlowName")
	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getComponent() {
		return component;
	}
	@XmlElement (name="Component")
	public void setComponent(String component) {
		this.component = component;
	}
	
	public String getActionName() {
		return actionName;
	}
	@XmlElement (name="ActionName")
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	
	public String getTimeStamp() {
		return timeStamp;
	}
	@XmlElement (name="TimeStamp")
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public String getPayload() {
		return payload;
	}
	@XmlElement (name="Payload")
	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getExecptionMessage() {
		return execptionMessage;
	}
	@XmlElement (name="ExecptionMessage")
	public void setExecptionMessage(String execptionMessage) {
		this.execptionMessage = execptionMessage;
	}

	public String getExecptionCause() {
		return execptionCause;
	}
	@XmlElement (name="ExecptionCause")
	public void setExecptionCause(String execptionCause) {
		this.execptionCause = execptionCause;
	}

	public String getExecptionStackTrace() {
		return execptionStackTrace;
	}
	@XmlElement (name="ExecptionStackTrace")
	public void setExecptionStackTrace(String execptionStackTrace) {
		this.execptionStackTrace = execptionStackTrace;
	}
	
}
