package com.whiteskylabs.munit;

import static junit.framework.Assert.assertNotNull;

import org.json.simple.JSONObject;
import org.junit.Test;
import org.mule.DefaultMuleMessage;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.transport.PropertyScope;
import org.mule.munit.runner.functional.FunctionalMunitSuite;

public class RestMockingTest extends FunctionalMunitSuite {

	
	@Test
	public void testRestMocking() throws MuleException, Exception{
		MuleMessage muleMessage = createHTTPPayload();

		System.out.println(muleMessage.getOutboundPropertyNames());
		
		if(muleMessage.getOutboundProperty("http.method").equals("GET")){
			// Mock rest component and return JSON Object.
			whenMessageProcessor("resources").ofNamespace("jersey").thenReturn(muleMessageWithPayload(generateJSONOBject()));
		}
		
		// invoke 'rest-mockingFlow1' flow which has rest component.
		MuleEvent responseEvent = runFlow("rest-mockingFlow1", testEvent(muleMessage));
		
		// Check for null.
		assertNotNull(responseEvent.getMessageAsString());
		
		// Display the mock payload.
		System.out.println(responseEvent.getMessageAsString());
	}
	
	public JSONObject generateJSONOBject(){
		
		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObjectWine = new JSONObject();
		
		
		jsonObject.put("country", "USA");
		jsonObject.put("description", "With hints of ginger and spice, this wine makes an excellent complement to light appetizer and dessert fare for a holiday gathering.");
		jsonObject.put("grapes", "Pinot Noir");
		jsonObject.put("id", "9");
		jsonObject.put("name", "BLOCK NINE");
		jsonObject.put("picture", "block_nine.jpg");
		jsonObject.put("region", "California");
		jsonObject.put("year", "2009");

		jsonObjectWine.put("wine", jsonObject);
		
		return jsonObjectWine;
	}
	
	public MuleMessage createHTTPPayload(){
		
		MuleMessage muleMessage = new DefaultMuleMessage("", muleContext);
		
		muleMessage.setProperty("http.method", "GET", PropertyScope.OUTBOUND);
		muleMessage.setProperty("http.path", "hello", PropertyScope.OUTBOUND);
		
		String payload = "/kalyan";
		
		muleMessage.setPayload(payload);
		
		return muleMessage;
		
	}
	
}
