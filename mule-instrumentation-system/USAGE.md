[Purpose](#purpose)  
[Steps to use Mule Instrumentation utility](#steps-to-use-mule-instrumentation-Utility)

Purpose
=======

Mule Instrumentation utility desingned to log the complete trace of the mule flows. This utility will log information from start of the flow to till end of the flow and also logs the exception trace.

Prerequisites
=============

In order use this utility you'll need:  

* Maven based mule project to add the dependenices of mule instrumentation utility.
* Mule standalone ESB runtime if deploying on Mule standalone instance. 
* Mule Management Console(MMC) if deploying on MMC.


Steps to use Mule Instrumentation utility
===================================
Following steps need to be take care while adding the mule instrumentation utility to the mule project.

1. Add the below dependencies under dependency section of the mule project pom.xml file.

				 <dependency>
			   		<groupId>com.google.code.gson</groupId>
   					<artifactId>gson</artifactId>
   					<version>2.3</version>
  				 </dependency>


2. Download the mule instrumentation utility jar located at the location <Git Hub Location>

3. Configure the downloaded mule-instrumentation-utility-jar to build path of mule project. 

4. Create a log4j.properties file under src/main/resources of mule project and add the below content to it.
			
			log4j.rootLogger=INFO, file
			# Direct log messages to a log file
			log4j.appender.file=org.apache.log4j.RollingFileAppender
			log4j.appender.file.File=${mule.home}/instrumentation-logs/mule-instrumentation-system.log
			log4j.appender.file.MaxFileSize=10MB
			log4j.appender.file.MaxBackupIndex=100
			log4j.appender.file.layout=org.apache.log4j.PatternLayout
			log4j.appender.file.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n

	In the above configuration "log4j.appender.file.File" value will be based on requirement, it indicates where the log file should be created. Currently it is configured to create log files under 'MULE_HOME/instrumentation-logs/' directory.

5. Create a Instrumentation.properties file under src/main/resources of mule project and add the below content to it.

			# provide either true or false to log the endpoint information.
			enableEndpointLogging=true
			# provide either true or false to log the Message processor information.
			enableMessageProcessorLogging=true
			# provide either xml or json for loggingFormat
			loggingFormat=xml
			# provide either true or false to log the current payload
			enablePayloadLogging=true

	above values are configurable.Based on the values set instrumentation utility will log the mule flow trace and will log data in either XML or JSON format.


6. Add  below spring beans configuration to mule configuration file under global section.
			
			<spring:beans>
				<spring:bean name="MuleCustomMessageprocessorNotificationBean"
				class="com.whiteskylabs.notifications.CustomMessageProcessorNotification">
				</spring:bean>
  
				<spring:bean name="MuleCustomExceptionNotificationBean"
				class="com.whiteskylabs.notifications.CustomExceptionNotification">
				</spring:bean>

				<spring:bean name="MuleCustomEndpointNotificationBean"
				class="com.whiteskylabs.notifications.CustomEndpointNotification">
				</spring:bean>
			</spring:beans>

		  	
