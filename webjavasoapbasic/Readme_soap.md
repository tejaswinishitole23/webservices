# What is SOAP?

From wiki - SOAP (abbreviation for Simple Object Access Protocol) is a messaging protocol specification for exchanging structured information in the implementation of web services in computer networks. Its purpose is to provide extensibility, neutrality, verbosity and independence.

SOAP is an XML-based protocol for accessing web services over HTTP. It has some specification which could be used across all applications.

SOAP is known as the Simple Object Access Protocol

SOAP was developed as an intermediate language so that applications built on various programming languages could talk easily to each other and avoid the extreme development effort.

Every programming language can understand the XML markup language. Hence, XML was used as the underlying medium for data exchange.

But there are no standard specifications on use of XML across all programming languages for data exchange. That is where SOAP comes in.

SOAP was designed to work with XML over HTTP and have some sort of specification which could be used across all applications. 

# This sample project

Here we have created a very basic web service

We have one basic class that we have marked @WebService ( This is pure jax-ws annotation. nothing related to any framework )

Tomcat needs end-points to be put xml file. so we have put sun-jaxws.xml.

We have empty web.xml

Right click and run on server.
Wsdl - http://localhost:8080/webjavasoapbasic/calc?wsdl

To test put wsdl in SOAPUI . snapshot saved in notes


