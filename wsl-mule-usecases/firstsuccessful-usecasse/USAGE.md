[Purpose](#purpose)  
[Running the application](#running-the-application)  
[Resources](#resources)

Purpose
===========

This document illustrates how to first successful routing message processor works.


**Use-case**: Trigger the flow with Http and check how first successful iterates through the child message processor by changing the failure expression and testing under different condition.

Running the application
=======================

1. Right click on example-on-mule-interceptor.mflow and select **Run As Mule Application**.
2. Check the console to see output when the application starts.

	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	+ Started app 'firstsuccessful-usecasse'  	           +
	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
3. Hit the endpoint at<http://localhost:8081/router>.
	
4. The first successful iterates till first time it is successful and it never throws any error even if the child processor has failed. If all the child processor has failed it gives error of org.mule.api.routing.CouldNotRouteOutboundMessageException .

	
Resources
===========

? [Using Interceptors] (http://www.mulesoft.org/documentation/display/current/Routing+Message+Processors#RoutingMessageProcessors-FirstSuccessful)


	
