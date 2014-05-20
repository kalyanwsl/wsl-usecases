package com.whiteskylabs.jip;

import org.apache.log4j.Logger;
import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

public class PauseTenSec implements Callable {
	private static Logger log= Logger.getLogger(PauseTenSec.class.getName().toString());
	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		log.info("Pause for 10 Seconds");
		Thread.currentThread().sleep(10000);
		return eventContext.getMessage();
	}

}
