package com.whiteskylabs.loggermanager;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.whiteskylabs.common.InstrumentationConstants;

public class InstrumentationLoggerFactory extends InstrumentationProperties {

	/** Prepares Log Message with respective message format
	 * @param instrumentationBO Request notification properties  
	 * @return Log message
	 * @throws JAXBException
	 * @throws IOException
	 */
	public String getLogMessage(InstrumentationBO instrumentationBO)
			throws JAXBException, IOException {

		// If message format configured to JSON prepare JSON log message.
		if (getPropValue(InstrumentationConstants.LOGGING_FORMAT).equals(
				InstrumentationConstants.JSON_FORMAT)) {
			ILogMessageFormat logJSONMessageFormat = new LogJSONMessageFormatImpl();
			return logJSONMessageFormat.generateLogMessage(instrumentationBO);
			
		} 
		// Otherwise prepare XML log message.
		else {			
			ILogMessageFormat logXMLMessageFormat = new LogXMLMessageFormatImpl();
			return logXMLMessageFormat.generateLogMessage(instrumentationBO);
		}

	}

}
