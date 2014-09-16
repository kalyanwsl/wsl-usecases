package com.whiteskylabs.loggermanager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.whiteskylabs.common.InstrumentationConstants;

public class InstrumentationProperties {

	/** Get Property value
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String getPropValue(String key) throws IOException {

		Properties prop = new Properties();
		String propFileName = InstrumentationConstants.INSTRUMENTATION_PROPERTIES_FILE_NAME;


		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream(propFileName);
		// Load property file
		prop.load(inputStream);

		if (inputStream == null) {
			throw new FileNotFoundException("Property file '" + propFileName
					+ "' not found in the classpath.");
		}

		return prop.getProperty(key);

	}

}
