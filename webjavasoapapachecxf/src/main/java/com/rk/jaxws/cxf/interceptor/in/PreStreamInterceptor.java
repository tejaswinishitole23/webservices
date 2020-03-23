package com.rk.jaxws.cxf.interceptor.in;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

public class PreStreamInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	public PreStreamInterceptor() {
		super(Phase.PRE_STREAM);
	}

	@Override
	public void handleMessage(SoapMessage soapMessage) throws Fault {
		System.out.println("Interceptor:"+this.getClass().getName());
		printAvailableFormats(soapMessage);
		printUsingInputStream(soapMessage);
	}
	
	// LEARN - Using InputStream to get data works only in PRE_STREAM
	private void printUsingInputStream(SoapMessage message) {
		try {
			
			//Get the message body into payload[] and set a new non-consumed  inputStream into Message
	        InputStream in = message.getContent(InputStream.class);
	        byte payload[] = IOUtils.readBytesFromStream(in);
	        ByteArrayInputStream bin = new ByteArrayInputStream(payload);
	        message.setContent(InputStream.class, bin);
	        
	        String s = new String(payload, StandardCharsets.UTF_8);
	        System.out.println(s);
		}
		catch ( Exception e) {
			e.printStackTrace();
		}
	}
	
	private void printAvailableFormats(SoapMessage message) {
		String contentType = (String) message.get(Message.CONTENT_TYPE);
		System.out.println("contentType:" + contentType);

		/*
		 * Following formats found next:java.util.List next:java.io.InputStream
		 * next:org.w3c.dom.Node next:javax.xml.stream.XMLStreamReader
		 * next:org.apache.cxf.io.DelegatingInputStream
		 */
		Message inMessage = message.getExchange().getInMessage();
		if (null != inMessage) {
			Set<Class<?>> contentFormats = inMessage.getContentFormats();
			Iterator<Class<?>> iterator2 = contentFormats.iterator();
			while (iterator2.hasNext()) {
				Class<?> next = iterator2.next();
				System.out.println("contentFormat:" + next.getCanonicalName());
			}
		}
	}

}
