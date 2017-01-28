package com.whiteskylabs.junit.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.mule.DefaultMuleMessage;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.transport.PropertyScope;
import org.mule.construct.Flow;
import org.mule.tck.junit4.FunctionalTestCase;

public class Usecase2JUnitInvoker extends FunctionalTestCase {

	protected String getConfigResources() {
		return "jip-usecase2.xml";
	}

	/**
	 * TestCase: invokes 'jip-usecase1' flow and logs the payload.
	 * 
	 */
	@Test
	public void invokeJUsecase2() throws Exception {

		Flow flow = (Flow) getFlowConstruct("jip-usecase2");

		// Preparing MuleMessage with inboundProperties
		MuleMessage message = new DefaultMuleMessage("", muleContext);
		message.setProperty("insertCount", "2", PropertyScope.INBOUND);

		System.out.println("Invoking flow: " + flow.getName());
		MuleEvent event = getTestEvent(message, flow);
		MuleEvent result = flow.process(event);

		System.out.println("check for the response not null");
		assertNotNull(result.getMessage().getPayloadAsString());
		System.out.println("Payload: "
				+ result.getMessage().getPayloadAsString());

	}
}
