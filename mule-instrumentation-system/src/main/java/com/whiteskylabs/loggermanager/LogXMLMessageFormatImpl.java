package com.whiteskylabs.loggermanager;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.sun.xml.internal.bind.marshaller.DataWriter;
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
			
			//Avoid the conversion of < to &lt; and > to &gt; etc
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			DataWriter dataWriter = new DataWriter(printWriter, "UTF-8", new JaxbCharacterEscapeHandler());
			 
			// Perform Marshaling operation
			jaxbMarshaller.marshal(instrumentationBO, dataWriter);

			xmlMessage = stringWriter.toString();
			
			// Close writer object.
			stringWriter.close();
			printWriter.close();
			
			return xmlMessage;
			
		} catch (JAXBException jbe) {
			throw new InstrumentationException(jbe.getMessage(), jbe);
		} catch (IOException ioe) {
			throw new InstrumentationException(ioe.getMessage(), ioe);
		}
	}

}
