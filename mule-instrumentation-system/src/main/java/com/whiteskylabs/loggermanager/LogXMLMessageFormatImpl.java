package com.whiteskylabs.loggermanager;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang.StringEscapeUtils;

import com.whiteskylabs.exceptions.InstrumentationException;

/**
 * Convert InstrumentationBO object into XML message
 */
public class LogXMLMessageFormatImpl implements ILogMessageFormat {

	@Override
	public String generateLogMessage(InstrumentationBO instrumentationBO)
			throws InstrumentationException {
		String xmlMessage = null;
		try {
			JAXBContext jaxbContext = JAXBContext
					.newInstance(InstrumentationBO.class);

			// Generate XML log message from instrumentation.
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty("com.sun.xml.bind.xmlDeclaration",
					Boolean.FALSE);
			// Make XML pretty format.
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			
			StringWriter stringWriter = new StringWriter();

			// Perform Marshaling operation
			jaxbMarshaller.marshal(instrumentationBO, stringWriter);

			//Avoid the conversion of < to &lt; and > to &gt; etc
			xmlMessage = StringEscapeUtils.unescapeHtml(stringWriter.toString());
			
			// Close writer object.
			stringWriter.close();
			
			return xmlMessage;
			
		} catch (JAXBException jbe) {
			throw new InstrumentationException(jbe.getMessage(), jbe);
		} catch (IOException ioe) {
			throw new InstrumentationException(ioe.getMessage(), ioe);
		}
	}

}
