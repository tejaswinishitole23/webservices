# Understanding the project.

## Basics
* Server will read web.xml.
* Because of following entry - org.springframework.web.context.ContextLoaderListener as listener, Context Loader will start.
* This will look for contextConfigLocation information and will get pointed to beans.xml file.
* beans.xml has 
    1. end-point beans 
    2. imports cxf.xml which would load CXF related beans.

*How to write wsdl first Soap Service:*
1. First write wsdl, and then use it to generate code using wsdl2java in gradle.
2. Generated code has end-point interface implemented with all the annotations.
3. Implement a concrete end-point class extending that interface. There are important annotations on this class that comes from wsdl. 

	/* LEARN - 
	 * portName and serviceName are kept under wsdl:service in wsdl.
	 * endpointInterface - fully qualified interface name
	 * targetNamespace - defined in wsdl under targetNamespace at top
	 *  */
	@WebService(portName="CalculatorSOAP", serviceName="Calculator",
	endpointInterface="com.rk.service.calculator.Calculator", 
	targetNamespace="http://www.rk.com/service/Calculator/")
	public class CalculatorWS implements Calculator {...}
	
## What to learn from this project

1. **In and Out Interceptors** Read the jpg in notes and understand the in and out interceptors and phases. Each phase has specific purpose.

2. **PreStreamInterceptor**
Raw payload information can be extracted immediately in the first interceptor in PRE_STREAM using InputStream format.
Note that after InputStream is read in further interceptors, cursor moves to end and you can't get anything out of it.

3. **PreInvokeInterceptor**
See how to read header. You can extract STring and Document. Document can be used for extracting exact information that you are expecting.

4. **PrintInterceptorsAndMessageFormats**
This class simply shows that you can iterate through interceptors and how formats change as message passes through them

5. **SetupInterceptor** shows how to alter headers for outgoing message. here we create a new header, and also copy incoming header to outgoing message. 

6. **CalculatorWS**
How to implement your service.
How to access outside soap message and other information using Resource **WebServiceContext**

Note - 

* Interceptors are singletons.
* Interceptors are required to be added on to bus. either in xml or programatically.

## Other points
Use PhaseInterceptorChain.getCurrentMessage(); to get hold of current message under processing.
