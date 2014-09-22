package com.whiteskylabs.loggermanager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.whiteskylabs.common.InstrumentationConstants;
import com.whiteskylabs.exceptions.InstrumentationException;

/**
 * Loads Instrumentation properties file and reads specified property value
 */
public class InstrumentationProperties {

	/**
	 * Get Property value
	 * 
	 * @param key
	 * @return
	 * @throws InstrumentationException
	 */

	private static Properties prop;
	private InputStream inputStream;

	private void loadProperties() throws InstrumentationException {

		try {
			String propFileName =InstrumentationConstants.INSTRUMENTATION_PROPERTIES_FILE_NAME;
			if (prop == null || prop.isEmpty()) {
				prop = new Properties();
				inputStream = getClass().getClassLoader().getResourceAsStream(
						propFileName);
				prop.load(inputStream);

				if (inputStream == null) {
					throw new InstrumentationException("Property file '"
							+ propFileName + "' not found in the classpath.");
				} else {
					inputStream.close();
				}
			}
		} catch (IOException ioe) {
			throw new InstrumentationException(ioe.getMessage(), ioe);
		}
	}

	public String getPropValue(String key) throws InstrumentationException {
		loadProperties();
		return prop.getProperty(key);
	}

}