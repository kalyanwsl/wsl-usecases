package com.whiteskylabs.jip.util;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

import com.sun.tools.doclets.formats.html.resources.standard;
import com.whiteskylabs.jip.PauseTenSec;

public class CreateCollection implements Callable {
	private static Logger log = Logger.getLogger(PauseTenSec.class.getName()
			.toString());

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {

		System.out.println("in createCollection class");
		int countVar = 1;
		
		if(eventContext.getMessage().getInboundProperty("insertCount") != null){
			countVar=Integer.parseInt(eventContext.getMessage().getInboundProperty("insertCount").toString());
		}
		
		ArrayList<String> stringList = new ArrayList<String>(countVar);

		for (int loopVar = 0; loopVar < countVar; loopVar++) {
			stringList.add("sample "+loopVar);
		}
		System.out.println("List size: " + stringList.size());
		eventContext.getMessage().setPayload(stringList);
		return eventContext.getMessage();
	}
}
