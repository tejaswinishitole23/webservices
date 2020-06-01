package in.rk.jaxws.interceptor.in;

import java.util.Iterator;
import java.util.Set;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.springframework.stereotype.Component;
// LEARN - Interceptors are singletons
@Component
public class PrintInterceptorsAndMessageFormats extends AbstractPhaseInterceptor<Message> {

	public PrintInterceptorsAndMessageFormats() {
		super(Phase.RECEIVE);
		System.out.println(this.getClass().getName() +" created");
	}

	/*
	 * This method would iterate through all in-coming interceptors by getting InterceptorChain.
	 * Usually you don't need this. It is done by PhaseInterceptorChain class.
	 * Its done here to find out how message is changing with each interceptor.
	 * */
	@Override
	public void handleMessage(Message message) throws Fault {
		System.out.println("Interceptor:" + this.getClass().getName()+" Object:"+this);
		
		/* Uncomment this line to see all interceptors and formats */
		// printInterceptorsAndFormats(message);
		
	}

	@SuppressWarnings("unused")
	private void printInterceptorsAndFormats(Message message) {
		printAvailableFormats((SoapMessage)message);
		Iterator<Interceptor<? extends Message>> iterator = message.getInterceptorChain().iterator();
		while ( iterator.hasNext() ) {
			@SuppressWarnings("unchecked")
			Interceptor<Message> interceptor = (Interceptor<Message>)iterator.next();
			if ( interceptor.getClass().equals(this.getClass())) {
				continue;
			}
			System.out.println(interceptor.getClass());
			interceptor.handleMessage(message);
			printAvailableFormats((SoapMessage)message);
		}
		
		/* since you iterated through interceptors, all processing is done. at this point, if you let program continue
		 * next interceptor will be called by PhaseInterceptorChain. but message is already processed so it will fail.
		 * if we call abort, interceptor will stop processing. 
		 * Do note that response is already gone to client at this point.
		 */
		message.getInterceptorChain().abort();
	}
	
	private void printAvailableFormats(SoapMessage message) {
		Set<Class<?>> contentFormats = message.getContentFormats();
		Iterator<Class<?>> iterator = contentFormats.iterator();
		while (iterator.hasNext()) {
			Class<?> next = iterator.next();
			System.out.println("contentFormat:" + next.getCanonicalName());
		}
		System.out.println('\n');
	}

}

/* SAMPLE OUTPUT */
/*
Interceptor:com.rk.jaxws.cxf.interceptor.in.PrintInterceptorsAndMessageFormats
contentFormat:org.apache.cxf.io.DelegatingInputStream
contentFormat:java.io.InputStream


class org.apache.cxf.ws.policy.PolicyInInterceptor
contentFormat:org.apache.cxf.io.DelegatingInputStream
contentFormat:java.io.InputStream


class org.apache.cxf.interceptor.AttachmentInInterceptor
contentFormat:org.apache.cxf.io.DelegatingInputStream
contentFormat:java.io.InputStream


class com.rk.jaxws.cxf.interceptor.in.PreStreamInterceptor
App Interceptor:com.rk.jaxws.cxf.interceptor.in.PreStreamInterceptor
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:cal="http://www.rk.com/schema/Calculator">
   <soapenv:Header>
   	<a>
   	<requestId>aslkfaklhglkahgklhagf</requestId>
   	</a>
   
</soapenv:Header>
   <soapenv:Body>
      <cal:calculateDataRequest>
         <cal:operation>+</cal:operation>
         <!--Zero or more repetitions:-->
         <cal:numbers>44</cal:numbers>
         <cal:numbers>25</cal:numbers>

         <!--1 or more repetitions:-->
         <cal:user>
            <cal:userId>3249572</cal:userId>
            <cal:userName>asd</cal:userName>
         </cal:user>
      </cal:calculateDataRequest>
   </soapenv:Body>
</soapenv:Envelope>
Print Headers:
accept-encoding
connection
Content-Length
content-type
host
SOAPAction
user-agent
contentFormat:org.apache.cxf.io.DelegatingInputStream
contentFormat:java.io.InputStream


class org.apache.cxf.transport.https.CertConstraintsInterceptor
contentFormat:org.apache.cxf.io.DelegatingInputStream
contentFormat:java.io.InputStream


class com.rk.jaxws.cxf.interceptor.in.PostStreamInterceptor
App Interceptor:com.rk.jaxws.cxf.interceptor.in.PostStreamInterceptor
contentFormat:org.apache.cxf.io.DelegatingInputStream
contentFormat:java.io.InputStream


class org.apache.cxf.interceptor.StaxInInterceptor
contentFormat:javax.xml.stream.XMLStreamReader
contentFormat:org.apache.cxf.io.DelegatingInputStream
contentFormat:java.io.InputStream


class com.rk.jaxws.cxf.interceptor.in.ReadInterceptor
App Interceptor:com.rk.jaxws.cxf.interceptor.in.ReadInterceptor
contentFormat:javax.xml.stream.XMLStreamReader
contentFormat:org.apache.cxf.io.DelegatingInputStream
contentFormat:java.io.InputStream


class com.rk.jaxws.cxf.interceptor.in.ReadSoapInterceptor
App Interceptor:com.rk.jaxws.cxf.interceptor.in.ReadSoapInterceptor
contentFormat:javax.xml.stream.XMLStreamReader
contentFormat:org.apache.cxf.io.DelegatingInputStream
contentFormat:java.io.InputStream


class org.apache.cxf.frontend.WSDLGetInterceptor
contentFormat:javax.xml.stream.XMLStreamReader
contentFormat:org.apache.cxf.io.DelegatingInputStream
contentFormat:java.io.InputStream


class org.apache.cxf.binding.soap.interceptor.ReadHeadersInterceptor
contentFormat:javax.xml.stream.XMLStreamReader
contentFormat:org.apache.cxf.io.DelegatingInputStream
contentFormat:java.io.InputStream
contentFormat:org.w3c.dom.Node


class org.apache.cxf.binding.soap.interceptor.SoapActionInInterceptor
contentFormat:javax.xml.stream.XMLStreamReader
contentFormat:org.apache.cxf.io.DelegatingInputStream
contentFormat:java.io.InputStream
contentFormat:org.w3c.dom.Node


class org.apache.cxf.binding.soap.interceptor.StartBodyInterceptor
contentFormat:javax.xml.stream.XMLStreamReader
contentFormat:org.apache.cxf.io.DelegatingInputStream
contentFormat:java.io.InputStream
contentFormat:org.w3c.dom.Node


class org.apache.cxf.binding.soap.interceptor.MustUnderstandInterceptor
contentFormat:javax.xml.stream.XMLStreamReader
contentFormat:org.apache.cxf.io.DelegatingInputStream
contentFormat:java.io.InputStream
contentFormat:org.w3c.dom.Node


class org.apache.cxf.binding.soap.interceptor.CheckFaultInterceptor
contentFormat:javax.xml.stream.XMLStreamReader
contentFormat:org.apache.cxf.io.DelegatingInputStream
contentFormat:java.io.InputStream
contentFormat:org.w3c.dom.Node


class org.apache.cxf.jaxb.attachment.JAXBAttachmentSchemaValidationHack
contentFormat:javax.xml.stream.XMLStreamReader
contentFormat:org.apache.cxf.io.DelegatingInputStream
contentFormat:java.io.InputStream
contentFormat:org.w3c.dom.Node


class org.apache.cxf.wsdl.interceptors.DocLiteralInInterceptor
contentFormat:java.util.List
contentFormat:javax.xml.stream.XMLStreamReader
contentFormat:org.apache.cxf.io.DelegatingInputStream
contentFormat:java.io.InputStream
contentFormat:org.w3c.dom.Node


class org.apache.cxf.binding.soap.interceptor.SoapHeaderInterceptor
contentFormat:java.util.List
contentFormat:javax.xml.stream.XMLStreamReader
contentFormat:org.apache.cxf.io.DelegatingInputStream
contentFormat:java.io.InputStream
contentFormat:org.w3c.dom.Node


class org.apache.cxf.interceptor.OneWayProcessorInterceptor
contentFormat:java.util.List
contentFormat:javax.xml.stream.XMLStreamReader
contentFormat:org.apache.cxf.io.DelegatingInputStream
contentFormat:java.io.InputStream
contentFormat:org.w3c.dom.Node


class org.apache.cxf.jaxws.interceptors.WrapperClassInInterceptor
contentFormat:java.util.List
contentFormat:javax.xml.stream.XMLStreamReader
contentFormat:org.apache.cxf.io.DelegatingInputStream
contentFormat:java.io.InputStream
contentFormat:org.w3c.dom.Node


class com.rk.jaxws.cxf.interceptor.in.PreInvokeInterceptor
App Interceptor:com.rk.jaxws.cxf.interceptor.in.PreInvokeInterceptor
com.rk.schema.calculator.CalculateDataRequest
Object found is :com.rk.schema.calculator.CalculateDataRequest@68ec42c2
String from SOAP Header:
printing String from Document..START
<?xml version="1.0" encoding="UTF-8" standalone="no"?><soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:cal="http://www.rk.com/schema/Calculator">
   <soapenv:Header>
   	<a>
   	<requestId>aslkfaklhglkahgklhagf</requestId>
   	</a>
   
</soapenv:Header>
   <soapenv:Body/></soapenv:Envelope>
printing String from Document..END
Important Header Info : requestId:aslkfaklhglkahgklhagf
contentFormat:java.util.List
contentFormat:javax.xml.stream.XMLStreamReader
contentFormat:org.apache.cxf.io.DelegatingInputStream
contentFormat:java.io.InputStream
contentFormat:org.w3c.dom.Node


class org.apache.cxf.interceptor.StaxInEndingInterceptor
contentFormat:java.util.List
contentFormat:org.apache.cxf.io.DelegatingInputStream
contentFormat:java.io.InputStream
contentFormat:org.w3c.dom.Node


class org.apache.cxf.jaxws.interceptors.SwAInInterceptor
contentFormat:java.util.List
contentFormat:org.apache.cxf.io.DelegatingInputStream
contentFormat:java.io.InputStream
contentFormat:org.w3c.dom.Node


class org.apache.cxf.jaxws.interceptors.HolderInInterceptor
contentFormat:java.util.List
contentFormat:org.apache.cxf.io.DelegatingInputStream
contentFormat:java.io.InputStream
contentFormat:org.w3c.dom.Node


class org.apache.cxf.interceptor.ServiceInvokerInterceptor
Running:com.rk.service.impl.CalculatorWS
contentFormat:java.util.List
contentFormat:org.apache.cxf.io.DelegatingInputStream
contentFormat:java.io.InputStream
contentFormat:org.w3c.dom.Node


class org.apache.cxf.interceptor.OutgoingChainInterceptor
App Interceptor:com.rk.jaxws.cxf.interceptor.out.SetupInterceptor
Alter the header for outgoing message
print header - 1st time.
a
print header - 2nd time.
a
{http://www.rk.com/service/Calculator/}apikey
App Interceptor:com.rk.jaxws.cxf.interceptor.out.PrepareSendInterceptor
App Interceptor:com.rk.jaxws.cxf.interceptor.out.SendInterceptor
App Interceptor:com.rk.jaxws.cxf.interceptor.out.WriteInterceptor
contentFormat:java.util.List
contentFormat:org.apache.cxf.io.DelegatingInputStream
contentFormat:org.w3c.dom.Node


*/

// this code does not print anything.. just left here for reference.
/*
 * private void printServerInfo() { //Look up for all available endpoints
 * registered on the bus Bus bus = CXFBusFactory.getDefaultBus(); ServerRegistry
 * serverRegistry = bus.getExtension(ServerRegistry.class); List<Server> servers
 * = serverRegistry.getServers(); for ( Server server : servers ) {
 * System.out.println(server.getEndpoint()); }
 * 
 * }
 */