package com.whiteskylabs.notificationsreport;

import java.io.IOException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.mule.context.notification.EndpointMessageNotification;
import com.whiteskylabs.loggermanager.LogMessageFormat;
import com.whiteskylabs.loggermanager.InstrumentationProperties;

public class EndpointNotificationReporter {
	
	private InstrumentationProperties props;
	private static Logger log = Logger
			.getLogger(EndpointNotificationReporter.class.getName());

	public InstrumentationProperties getProps() {
		return props;
	}

	public void setProps(InstrumentationProperties props) {
		this.props = props;
	}
	
	public void logEndpointNotificationReport(EndpointMessageNotification endnotification)
	{
		try {
			if (props.getPropValue("enableEndpointLogging").equalsIgnoreCase(
					"true")) {
				String endpointClassName = endnotification
						.getImmutableEndpoint().getName();
				String flowName = endnotification.getFlowConstruct().getName();
				Date timeStamp = new Date(endnotification.getTimestamp());
				String messageId = endnotification.getSource().getUniqueId();
				String payload = endnotification.getSource()
						.getPayloadAsString();
				
				LogMessageFormat logMessageFormat = new LogMessageFormat();

				log.info(logMessageFormat.getLogMessage(endpointClassName,
						messageId, timeStamp.toString(), flowName, payload,
						Boolean.valueOf(props
								.getPropValue("enablePayloadLogging")), props
								.getPropValue("loggingFormat")));

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}