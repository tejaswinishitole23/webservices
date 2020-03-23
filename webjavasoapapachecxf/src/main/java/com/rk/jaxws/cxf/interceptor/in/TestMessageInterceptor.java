package com.rk.jaxws.cxf.interceptor.in;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.SoapVersion;
import org.apache.cxf.binding.soap.SoapVersionFactory;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.staxutils.DepthXMLStreamReader;
import org.apache.cxf.staxutils.StaxUtils;

public class TestMessageInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	public TestMessageInterceptor() {
		super(Phase.PRE_INVOKE);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		System.out.println("Interceptor:" + this.getClass().getName());
		printAvailableFormats(message);
		readMessageBody(message);
	}

	
	@SuppressWarnings("rawtypes")
	private void readMessageBody(SoapMessage message) {
		List contents = message.getContent(List.class);
		for (Object object : contents) {
			System.out.println(object.getClass().getCanonicalName());
			System.out.println("Object found is :" + object);
		}
	}

	private void printAvailableFormats(SoapMessage message) {
		String contentType = (String) message.get(Message.CONTENT_TYPE);
		System.out.println("contentType:" + contentType);

		/*
		 * Following formats found contentFormat:java.util.List
		 * contentFormat:java.io.InputStream
		 * contentFormat:org.apache.cxf.io.DelegatingInputStream
		 * contentFormat:javax.xml.stream.XMLStreamReader
		 */

		Set<Class<?>> contentFormats = message.getContentFormats();
		Iterator<Class<?>> iterator2 = contentFormats.iterator();
		while (iterator2.hasNext()) {
			Class<?> next = iterator2.next();
			System.out.println("contentFormat:" + next.getCanonicalName());
		}
	}

}
