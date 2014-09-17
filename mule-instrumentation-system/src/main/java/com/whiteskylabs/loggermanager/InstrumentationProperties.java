package com.whiteskylabs.loggermanager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.whiteskylabs.common.InstrumentationConstants;

/**
 * Loads Instrumentation properties file and reads specified property value
 */
public class InstrumentationProperties {

	/**
	 * Get Property value
	 * 
	 * @param key
	 * @return
	 * @throws IOException
	 */
	private static Properties prop;
	private InputStream inputStream;
	
	private void loadProperties() throws IOException  {
		String propFileName = "Instrumentation.properties";
		if (prop == null || prop.isEmpty()) {
			prop = new Properties();
			inputStream = getClass().getClassLoader().getResourceAsStream(
					propFileName);
			prop.load(inputStream);

			if (inputStream == null) {
				throw new FileNotFoundException("property file '"
						+ propFileName + "' not found in the classpath");
			}else
			{
				inputStream.close();
			}
		}
	}

	public String getPropValue(String key) throws IOException {
		loadProperties();
		return prop.getProperty(key);

	}

}
