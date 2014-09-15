package com.whiteskylabs.loggermanager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class InstrumentationProperties {
	
	private static Logger log = Logger
			.getLogger(InstrumentationProperties.class.getName());
	
	private String propertyFileName;
	
	public String getPropertyFileName() {
		return propertyFileName;
	}

	public void setPropertyFileName(String propertyFileName) {
		this.propertyFileName = propertyFileName;
	}

	public String getPropValue(String key) throws IOException {
	 
	 String result = "";
	 
     Properties prop = new Properties();
     
   //  String propFileName = "Instrumentation.properties";
     
 //log.info("## the properties file name is ## "+propertyFileName);
     
     
     String propFileName=propertyFileName.toString();
     
		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream(propFileName);
    
     prop.load(inputStream);
     
     if (inputStream == null) {
         throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
     }
     
    // log.info("## the propertie value is  ##:"+prop.getProperty(key));
     
     return prop.getProperty(key);

	}
	 
}
