package com.whiteskylabs.loggermanager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class InstrumentationProperties {

	public String getPropValue(String key) throws IOException {

		Properties prop = new Properties();

		String propFileName = "Instrumentation.properties";

		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream(propFileName);

		prop.load(inputStream);

		if (inputStream == null) {
			throw new FileNotFoundException("property file '" + propFileName
					+ "' not found in the classpath");
		}

		return prop.getProperty(key);

	}

}
