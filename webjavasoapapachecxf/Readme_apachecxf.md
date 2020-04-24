**CXF Website**

http://cxf.apache.org/docs/cxf-architecture.html

Other links
https://access.redhat.com/documentation/en-us/red_hat_jboss_fuse/6.1/html/apache_cxf_development_guide/index
https://cwiki.apache.org/confluence/display/CXF20DOC/Developing+a+Service 

**Defining SOA**

As Java web services are based on the service oriented architecture design pattern, I want to assure we move forward with the same definition of SOA. At a high level, a defined SOA is a way to group business capabilities and provide a contract for consumers to access them. The use of SOA should increase the agility of an organization and reduce the overhead of IT.

**Defining Web Services**
In development, you will commonly implement SOA using web services. Web services are simply service contracts made available over the web. In the Java space, Sun and Oracle have offered developers web service APIs for over a decade. Their goal has been to provide simple APIs that help you as an architect or developer get applications to the market faster. Let's take a look at how that evolution has played out.

**JAX-RPC**
In 2002, Sun released their first web service specification, JSR 101. It's centered around XML-based remote procedure calls and was referred to as JAX-RPC. JAX-RPC leveraged SOAP over HTTP and HTTPS as the protocol in transport across a distributed client service model. Communication was based on XML, the WSDL 1. 0 specification, and XSD to find the format for the XML. JAX-RPC also defined support for asynchronous messages via the JAXM specification, attachments via the SAAJ specification, and basic profile through the WS-I specification.

**JAX-WS**
In 2006, Sun released JSR 224. Specification was referred to as JAX-WS 2. 0. The intention of JAX-WS was to update JAX-RPC to support new specifications and product versions. The JAX-WS release resulted in the deprecation of JAX-RPC. JAX-WS standardized on SOAP 1. 2 and added support for WSDL 2. 0. With JAX-WS came a standardization of JAXB for data binding between XML and Java. The lack of strong offering for data binding in JAX-RPC resulted in multiple frameworks coming to the market that would compete with JAXB. Each promoted their own benefits around performance and simplicity. Changes were made to the XML message format to support other transports such as FTP, JMS, and SMTP. Governing bodies such as Oasis developed standards to address concepts such as service discovery, security, reliability, and messaging. A key concept for web services is versioning, however, Oasis did not define a standard approach for it, leaving developers to decide on their own implementation.

**JAX-RS**
The Web 2. 0 movement brought a major change to web application design. AJAX gained in popularity as a mechanism to call web services directly from the view. Mobile and tablet application development erupted in 2008 and with it came requirements to build or reuse web services to support cross-channel business logic. A new problem arose as JAX-WS web services were less than ideal for AJAX and mobile clients. To solve this problem, an approach first introduced in 2000 by Roy Fielding at the University of California gained popularity as an alternative to WSDL and SOAP-based web services. This approach is called representational state transfer, or REST. To address the increasing popularity of REST, Sun introduced JSR 311 in 2008 with a reference name of JAX-RS. This specification was further improved upon by Oracle in 2013 with the release of JSR 339 as JAX-RS version 2. 0.


# Interceptors
Practically speaking, there are two base classes you may be concerned with. 
The first is the **AbstractPhaseInterceptor**. 
The second is **AbstractSoapInterceptor**. 

The only difference between the first and second is that the later will give you a SoapMessage instead of just a Message class. This allows you to access the SOAP headers and version.

http://cxf.apache.org/docs/interceptors.html 

# Default JAX-WS Incoming interceptor chain (Server):
* AttachmentInInterceptor Parse the mime headers for mime boundaries, finds the "root" part and resets the input stream to it, and stores the other parts in a collection of Attachments
* StaxInInterceptor Creates an XMLStreamReader from the transport InputStream on the Message
* ReadHeadersInterceptor Parses the SOAP headers and stores them on the Message
* SoapActionInInterceptor Parses "soapaction" header and looks up the operation if a unique operation can be found for that action.
* MustUnderstandInterceptor Checks the MustUnderstand headers, its applicability and process it, if required
* SOAPHandlerInterceptor SOAP Handler as per JAX-WS
* LogicalHandlerInInterceptor Logical Handler as per JAX-WS
* CheckFaultInterceptor Checks for fault, if present aborts interceptor chain and invokes fault handler chain
* URIMappingInterceptor (for CXF versions <= 2.x) Can handle HTTP GET, extracts operation info and sets the same in the Message
* DocLiteralnInterceptor Examines the first element in the SOAP body to determine the appropriate Operation (if soapAction did not find one) and calls the Databinding to read in the data.
* SoapHeaderInterceptor Perform databinding of the SOAP headers for headers that are mapped to parameters
* WrapperClassInInterceptor For wrapped doc/lit, the DocLiteralInInterceptor probably read in a single JAXB bean. This interceptor pulls the individual parts out of that bean to construct the Object[] needed to invoke the service.
* SwAInInterceptor For Soap w/ Attachments, finds the appropriate attachments and assigns them to the correct spot in the parameter list.
* HolderInInterceptor For OUT and IN/OUT parameters, JAX-WS needs to create Holder objects. This interceptor creates the Holders and puts them in the parameter list.
* ServiceInvokerInInterceptor Actually invokes the service.

# Default Outgoing chain stack (Server):
* HolderOutInterceptor For OUT and IN/OUT params, pulls the values out of the JAX-WS Holder objects (created in HolderInInterceptor) and adds them to the param list for the out message.
* SwAOutInterceptor For OUT parts that are Soap attachments, pulls them from the list and holds them for later.
* WrapperClassOutInterceptor For doc/lit wrapped, takes the remaining parts and creates a wrapper JAXB bean to represent the whole message.
* SoapHeaderOutFilterInterceptor Removes inbound marked headers
* SoapActionOutInterceptor Sets the SOAP Action
* MessageSenderInterceptor Calls back to the Destination object to have it setup the output streams, headers, etc... to prepare the outgoing transport.
* SoapPreProtocolOutInterceptor This interceptor is responsible for setting up the SOAP version and header, so that this is available to any pre-protocol interceptors that require these to be available.
* AttachmentOutInterceptor If this service uses attachments (either SwA or if MTOM is enabled), it sets up the Attachment marshallers and the mime stuff that is needed.
* StaxOutInterceptor Creates an XMLStreamWriter from the OutputStream on the Message.
* SoapHandlerInterceptor JAX-WS SOAPHandler
* SoapOutInterceptor Writes start element for soap:envelope and complete elements for other header blocks in the message. Adds start element for soap:body too.
* LogicalHandlerOutInterceptor JAX-WS Logical handler stuff
* WrapperOutInterceptor If wrapped doc/lit and not using a wrapper bean or if RPC lit, outputs the wrapper element to the stream.
* BareOutInterceptor Uses the databinding to write the params out.
* SoapOutInterceptor$SoapOutEndingInterceptor Closes the soap:body and soap:envelope
* StaxOutInterceptor$StaxOutEndingInterceptor Flushes the stax stream.
* MessageSenderInt$MessageSenderEnding Closes the exchange, lets the transport know everything is done and should be flushed to the client.

