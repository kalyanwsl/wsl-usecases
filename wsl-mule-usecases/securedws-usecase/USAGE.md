[Purpose](#purpose)  
[Running the application](#running-the-application)  
[Resources](#resources)

Purpose
===========

This document illustrates how to implement a soap web service with username and password.


**Use-case**: Soap webservice with username and password

Running the application
=======================

1. Right click on the project and select **Run As Mule Application**.
2. Check the console to see output when the application starts.

	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	+ Started app 'securedws-usecase'           +
	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
3. use the wsdl:  http://localhost:8081/security?wsdl
	
4. The request soap message is available in src/main/resources folder.

5. if correct username and password set then successful response is received.

	
Resources
===========

 [ws security implementation] (http://www.mulesoft.org/documentation/display/current/SOAP+Web+Service+Security+Example)


	
