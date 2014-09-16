package com.whiteskylabs.loggermanager;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class LogXMLMessageFormatImpl implements ILogMessageFormat {

	@Override
	public String generateLogMessage(InstrumentationBO instrumentationBO)
			throws JAXBException {

		JAXBContext jaxbContext = JAXBContext
				.newInstance(InstrumentationBO.class);

		// Generate XML log message from instrumentation.
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty("com.sun.xml.bind.xmlDeclaration",
				Boolean.FALSE);
		// Make XML pretty format.
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
				Boolean.TRUE);
		StringWriter writer = new StringWriter();
		jaxbMarshaller.marshal(instrumentationBO, writer);

		return writer.toString();
	}

}
