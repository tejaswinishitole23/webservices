# This sample project is buid using JAX-WS implementation

Here we have created a very basic web service

We have one basic class that we have marked @WebService ( This is pure jax-ws annotation. nothing related to any framework )

Tomcat needs end-points to be put xml file. so we have put sun-jaxws.xml.

We have empty web.xml

Right click and run on server.
Wsdl - http://localhost:8080/webjavasoapbasic/calc?wsdl

To test put wsdl in SOAPUI . snapshot saved in notes


# SOAP Using Jax-Ws

## What is JAX-WS

Java API for XML Web Services (JAX-WS) is a standardized API for creating and consuming SOAP (Simple Object Access Protocol) web services.

## SOAP

SOAP is an XML specification for sending messages over a network. SOAP messages are independent of any operating system and can use a variety of communication protocols including HTTP and SMTP.

SOAP is XML heavy, hence best used with tools/frameworks. JAX-WS is a framework that simplifies using SOAP. It is part of standard Java.

## Top-Down vs. Bottom-Up
Top-Down aka contract first - In a top-down (contract-first) approach, a WSDL document is created, and the necessary Java classes are generated from the WSDL. 

Bottom-Up aka Java first - In a bottom-up (contract-last) approach, the Java classes are written, and the WSDL is generated from the Java classes.

## Web Services Definition Language (WSDL)
WSDL is a contract definition of the available services. It is a specification of input/output messages, and how to invoke the web service. It is language neutral and is defined in XML.

Different parts of wsdl
#### Definitions
The definitions element is the root element of all WSDL documents. It defines the name, the namespace, etc. of the service and, as you can see, can be quite spacious:

	<definitions xmlns="http://schemas.xmlsoap.org/wsdl/"
	  xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
	  xmlns:tns="http://jaxws.baeldung.com/"
	  xmlns:wsam="http://www.w3.org/2007/05/addressing/metadata"
	  xmlns:wsp="http://www.w3.org/ns/ws-policy"
	  xmlns:wsp1_2="http://schemas.xmlsoap.org/ws/2004/09/policy"
	  xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd"
	  xmlns:xsd="http://www.w3.org/2001/XMLSchema"
	  targetNamespace="http://jaxws.baeldung.com/"
	  name="EmployeeService">
	  
	</definitions>
