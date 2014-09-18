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
	public String getPropValue(String key) throws InstrumentationException   {
		String value = null;
		try {
			Properties prop = new Properties();
			String propFileName = InstrumentationConstants.INSTRUMENTATION_PROPERTIES_FILE_NAME;
			

			InputStream inputStream = getClass().getClassLoader()
					.getResourceAsStream(propFileName);
			// Load property file
			prop.load(inputStream);

			if (inputStream == null) {
				throw new InstrumentationException("Property file '"
						+ propFileName + "' not found in the classpath.");
			}

			value = prop.getProperty(key);
			inputStream.close();
		} catch (IOException ioe) {
			throw new InstrumentationException(ioe.getMessage(), ioe);
		}
		return value;

	}

}
