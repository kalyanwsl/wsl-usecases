package com.wsl.ws;
import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
public class SOAPTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) { try {
        // Create SOAP Connection
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        // Send SOAP Message to SOAP Server
        String url = "http://localhost:8081/soapws";
        SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), url);

        // Process the SOAP Response
        printSOAPResponse(soapResponse);

        soapConnection.close();
    } catch (Exception e) {
        System.err.println("Error occurred while sending SOAP Request to Server");
        e.printStackTrace();
    }}
	private static SOAPMessage createSOAPRequest() throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI = "http://ws.wsl.com/";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("ws", serverURI);

        /*
        Constructed SOAP Request Message:
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://ws.wsl.com/">
		   <soapenv:Header/>
		   <soapenv:Body>
		      <ws:getUserDetails>
		         <!--Optional:-->
		         <ID>2</ID>
		      </ws:getUserDetails>
		   </soapenv:Body>
		</soapenv:Envelope>
         */

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("getUserDetails", "ws");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("ID");
        soapBodyElem1.addTextNode("1");       
        soapMessage.saveChanges();
        /* Print the request message */
       // System.out.print("Request SOAP Message = "+soapMessage);
        //soapMessage.writeTo(System.out);
        //System.out.println();

        return soapMessage;
    }

    /**
     * Method used to print the SOAP Response
     */
    private static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        System.out.print("\nResponse SOAP Message = ");
        StreamResult result = new StreamResult(System.out);
        transformer.transform(sourceContent, result);
    }
}
