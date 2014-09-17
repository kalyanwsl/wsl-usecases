package com.whiteskylabs.loggermanager;

import com.whiteskylabs.common.InstrumentationConstants;
import com.whiteskylabs.exceptions.InstrumentationException;

/**
 * This class extends InstrumentationProperties class that reads required
 * properties. Prepares log message based on LOGGING_MESSAGE_FORMAT property
 * 
 */
public class InstrumentationLoggerFactory extends InstrumentationProperties {

	/**
	 * Prepares Log Message with respective message format
	 * 
	 * @param instrumentationBO
	 *            Request notification properties
	 * @return Log message
	 * @throws InstrumentationException
	 */
	public String getLogMessage(InstrumentationBO instrumentationBO)
			throws InstrumentationException {

		// If message format configured to JSON prepare JSON log message.
		if (getPropValue(InstrumentationConstants.LOGGING_MESSAGE_FORMAT)
				.equals(InstrumentationConstants.JSON_FORMAT)) {
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
