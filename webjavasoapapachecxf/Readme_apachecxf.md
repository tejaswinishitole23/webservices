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


** project **

Server will read web.xml.
Becuase of following entry - org.springframework.web.context.ContextLoaderListener as listener,
Context Loader will start.

This will look for contextConfigLocation information and will get pointed to beans.xml file.

beans.xml has endpoint beans and imports cxf.xml which would load CXF related beans.

