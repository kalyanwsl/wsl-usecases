[Purpose](#purpose)  
[Prerequisites](#prerequisites)  
[Running the application](#running-the-application)  
[Resources](#resources)

Purpose
=======

This document illustrates how to use aggregators(Collection Aggregator and Message Chunk Aggregator).
Collection Aggregator is used to aggregator the splitted collection object based on correlation id.
Message Chunk Aggregator is used to aggregator the splitted message(in byte format) based on correlation id.

Prerequisites
=============

* Mule Studio to import and run the application.
* Configure FTP Server(Filezilla) to work with Message Chunk Aggregator usecase

Running the application
=======================

1. Right click on the project and select **Run As Mule Application**.

2. Hit the endpoint at<http://localhost:8084/collection-aggregator> to invoke Collection Aggregator use case.

3. To trigger Message Chunk Aggregator usecase, drop a file in input folder as configured in File inbound connector.
			  
Resources
===========
		  
●     [Mule Aggregators](http://www.mulesoft.org/documentation/display/current/Routing+Message+Processors#RoutingMessageProcessors-RoutingMessageProcessors-All)	

