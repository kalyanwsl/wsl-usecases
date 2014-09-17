package com.whiteskylabs.loggermanager;

import com.whiteskylabs.exceptions.InstrumentationException;

/**
 * Generate Log Message.
 */
public interface ILogMessageFormat {

	/**
	 * Generate Log Message with respective message format
	 * 
	 * @param instrumentationBO
	 *            Contains all properties of the request.
	 * @return
	 * @throws InstrumentationException
	 */
	public String generateLogMessage(InstrumentationBO instrumentationBO)
			throws InstrumentationException;
}
