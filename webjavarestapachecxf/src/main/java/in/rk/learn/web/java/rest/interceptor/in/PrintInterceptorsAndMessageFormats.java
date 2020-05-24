package in.rk.learn.web.java.rest.interceptor.in;

import java.util.Iterator;
import java.util.Set;

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
		System.out.println("handleMessage in Interceptor:" + this.getClass().getName()+" Object:"+this);
		
		/* Uncomment this line to see all interceptors and formats */
//		 printInterceptorsAndFormats(message);
		
	}

	@SuppressWarnings("unused")
	private void printInterceptorsAndFormats(Message message) {
		printAvailableFormats(message);
		Iterator<Interceptor<? extends Message>> iterator = message.getInterceptorChain().iterator();
		while ( iterator.hasNext() ) {
			@SuppressWarnings("unchecked")
			Interceptor<Message> interceptor = (Interceptor<Message>)iterator.next();
			if ( interceptor.getClass().equals(this.getClass())) {
				continue;
			}
			System.out.println(interceptor.getClass());
			interceptor.handleMessage(message);
			printAvailableFormats(message);
		}
		
		/* since you iterated through interceptors, all processing is done. at this point, if you let program continue
		 * next interceptor will be called by PhaseInterceptorChain. but message is already processed so it will fail.
		 * if we call abort, interceptor will stop processing. 
		 * Do note that response is already gone to client at this point.
		 */
		message.getInterceptorChain().abort();
	}
	
	private void printAvailableFormats(Message message) {
		Set<Class<?>> contentFormats = message.getContentFormats();
		Iterator<Class<?>> iterator = contentFormats.iterator();
		while (iterator.hasNext()) {
			Class<?> next = iterator.next();
			System.out.println("contentFormat:" + next.getCanonicalName());
		}
		System.out.println('\n');
	}

}

/* SAMPLE OUTPUT - extra irrelevant lines removed */

/*
 
Interceptor:in.rk.learn.web.java.rest.interceptor.in.PrintInterceptorsAndMessageFormats Object:in.rk.learn.web.java.rest.interceptor.in.PrintInterceptorsAndMessageFormats@72fa9288
contentFormat:java.io.InputStream
contentFormat:org.apache.cxf.io.DelegatingInputStream


class in.rk.learn.web.java.rest.interceptor.in.PreStreamInterceptor
contentFormat:java.io.InputStream
contentFormat:org.apache.cxf.io.DelegatingInputStream


class org.apache.cxf.transport.https.CertConstraintsInterceptor
contentFormat:java.io.InputStream
contentFormat:org.apache.cxf.io.DelegatingInputStream

class org.apache.cxf.jaxrs.interceptor.JAXRSInInterceptor
contentFormat:org.apache.cxf.io.DelegatingInputStream


class org.apache.cxf.interceptor.OneWayProcessorInterceptor
contentFormat:org.apache.cxf.io.DelegatingInputStream


class in.rk.learn.web.java.rest.interceptor.in.PreInvokeInterceptor
contentFormat:org.apache.cxf.io.DelegatingInputStream


class org.apache.cxf.interceptor.ServiceInvokerInterceptor
contentFormat:org.apache.cxf.io.DelegatingInputStream


class org.apache.cxf.interceptor.OutgoingChainInterceptor
contentFormat:org.apache.cxf.io.DelegatingInputStream

*/