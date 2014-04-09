[Purpose](#purpose)  
[Prerequisites](#prerequisites)  
[Running the application](#running-the-application)  
[Resources](#resources)

Purpose
=======

This usecase is implemented to handle sftp outbound endpoint connection exception and place the current file in 'error-out' folder


Prerequisites
=============

* Mule Studio to import and run the application.
* Configure FTP Server(Filezilla) and SFTP(CrushFTP) to work with this usecase.

Running the application
=======================

1. Right click on the project and select **Run As Mule Application**.
2. Check the console to see output when the application starts.

	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	+ Started app 'untilsuccessful'  	           +
	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
3. To trigger file-to-sftp-error-handling usecase, drop a file in input folder as configured in File inbound connector.
			  
Resources
===========
		  
●     [File connector](http://www.mulesoft.org/documentation/display/current/File+Transport+Reference)
●     [SFTP connector](http://www.mulesoft.org/connectors/sftp-connector)

