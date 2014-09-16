package com.whiteskylabs.loggermanager;

import javax.xml.bind.JAXBException;

public interface ILogMessageFormat {

	/** Generate Log Message
	 * @param instrumentationBO Contains all properties of the request.
	 * @return
	 * @throws JAXBException
	 */
	public String generateLogMessage(InstrumentationBO instrumentationBO) throws JAXBException;
}
