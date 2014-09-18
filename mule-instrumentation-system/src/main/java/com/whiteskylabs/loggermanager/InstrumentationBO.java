package com.whiteskylabs.loggermanager;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * POJO class of Instrumentation logger properties
 *
 */
@XmlRootElement(name="instrumentation-logger")
public class InstrumentationBO implements Serializable{

	/**
	 * Serializable unique ID.
	 */
	private static final long serialVersionUID = 1L;
	String flowName;
	String component;
	String messageID;
	String timeStamp;
	Object payload;
	String actionName;
	String execptionMessage;
	String execptionCause;
	String execptionStackTrace;
	
	
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
	
	public Object getPayload() {
		return payload;
	}
	@XmlElement
	public void setPayload(Object payload) {
		this.payload = payload;
	}

	public String getActionName() {
		return actionName;
	}
	@XmlElement
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getExecptionMessage() {
		return execptionMessage;
	}
	@XmlElement
	public void setExecptionMessage(String execptionMessage) {
		this.execptionMessage = execptionMessage;
	}
	
	public String getExecptionCause() {
		return execptionCause;
	}
	@XmlElement
	public void setExecptionCause(String execptionCause) {
		this.execptionCause = execptionCause;
	}
	public String getExecptionStackTrace() {
		return execptionStackTrace;
	}
	@XmlElement
	public void setExecptionStackTrace(String execptionStackTrace) {
		this.execptionStackTrace = execptionStackTrace;
	}
	
}
