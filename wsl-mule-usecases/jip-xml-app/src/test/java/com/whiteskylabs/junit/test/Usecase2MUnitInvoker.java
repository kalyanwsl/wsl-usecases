package com.whiteskylabs.junit.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.mule.DefaultMuleMessage;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.transport.PropertyScope;
import org.mule.munit.runner.functional.FunctionalMunitSuite;

import com.sun.tools.internal.xjc.api.Property;

public class Usecase2MUnitInvoker extends FunctionalMunitSuite {

	@Override
	protected String getConfigResources() {
		return "jip-usecase2.xml";
	}

	/**
	 * TestCase: invokes 'jip-usecase2' flow and logs the payload
	 */
	@Test
	public void invokeMUsecase2() throws MuleException,Exception {
		
		System.out.println("mocking insert2db");
		whenEndpointWithAddress("jdbc://insert-record").thenReturn(muleMessageWithPayload("mocked response"));
		System.out.println("running flow \'jip-usecase2\'");
		
		MuleMessage message=  new DefaultMuleMessage("",muleContext);
		message.setProperty("insertCount", "2",PropertyScope.INBOUND);
		
		System.out.println("insertCount: "+message.getInboundProperty("insertCount"));
		MuleEvent response= runFlow("jip-usecase2", testEvent(message));
		
		System.out.println("check for the response not null");
		assertNotNull(response.getMessage().getPayload().toString());
		System.out.println("Payload: "+response.getMessage().getPayload().toString());
		
		
	}
}
