package com.whiteskylabs.jip;

import org.apache.log4j.Logger;
import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

public class PauseThirtySec implements Callable {
	private static Logger log= Logger.getLogger(PauseThirtySec.class.getName().toString());
	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		log.info("Pause for 30 Seconds");
		Thread.currentThread().sleep(30000);
		return eventContext.getMessage();
	}

}
