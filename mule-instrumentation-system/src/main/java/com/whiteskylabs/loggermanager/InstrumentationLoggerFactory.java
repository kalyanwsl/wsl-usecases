package com.whiteskylabs.loggermanager;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.whiteskylabs.common.InstrumentationConstants;

public class InstrumentationLoggerFactory extends InstrumentationProperties {

	public String getLogMessage(InstrumentationBO instrumentationBO)
			throws JAXBException, IOException {

		if (getPropValue(InstrumentationConstants.LOGGING_FORMAT).equals(
				InstrumentationConstants.JSON_FORMAT)) {
			ILogMessageFormat logJSONMessageFormat = new LogJSONMessageFormatImpl();
			return logJSONMessageFormat.generateLogMessage(instrumentationBO);
			
		} else {			
			ILogMessageFormat logXMLMessageFormat = new LogXMLMessageFormatImpl();
			return logXMLMessageFormat.generateLogMessage(instrumentationBO);
		}

	}

}
