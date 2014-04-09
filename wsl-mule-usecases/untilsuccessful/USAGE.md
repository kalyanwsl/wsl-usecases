[Purpose](#purpose)  
[Running the application](#running-the-application)  
[Resources](#resources)

Purpose
===========

This document illustrates how to until successful routing message processor works.


**Use-case**: Trigger the flow with Http and check how until successful works.

Running the application
=======================

1. Right click on the project and select **Run As Mule Application**.
2. Check the console to see output when the application starts.

	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	+ Started app 'untilsuccessful'  	           +
	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
3. Hit the endpoint at<http://localhost:8081/until>.
	
4. The until-successful scope processes messages through the processors within it until the process succeeds. The retry works accordingly mentioned in the scope.

	
Resources
===========

 [until-successful implementation] (http://www.mulesoft.org/documentation/display/current/Until+Successful+Scope)


	
