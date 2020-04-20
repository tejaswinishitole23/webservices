package com.rk.jaxws.cxf.interceptor.out;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;

public class SetupInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	public SetupInterceptor() {
		super(Phase.SETUP);
	}

	
	// LEARN
	// Apache CXF till this point has headers from incoming message. but from here, it will drop all of them
	// but keep any new headers you add.
	// IN this interceptor, two new headers are added
	// 1 - new header called apikey
	// 2 - new header where data is copied from incoming header
	
	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		System.out.println("App Interceptor:"+this.getClass().getName());
		System.out.println("In this interceptor, alter the header for outgoing message\n");
		
		printHeader("pre alteration",message);
		addHeader(message);
		findHeadersForOutput(message);
		printHeader("post alertation",message);
		
	}

	
	private void findHeadersForOutput(SoapMessage message) {
		List<Header> newHeaders = new ArrayList<>();
		// iterate existing headers to find what is required.
		List<Header> headers = message.getHeaders();
		for ( Header header : headers) {
			if ( header.getName().toString().equalsIgnoreCase("a")) {
				Element element = (Element) header.getObject();
				Header newHeader = new Header(new QName("aout"), element);
				newHeaders.add(newHeader);
			}
		}
		
		// add new headers to message
		newHeaders.forEach(x->message.getHeaders().add(x));
	}

	private void addHeader(SoapMessage message) {
		QName qname = new QName("http://www.rk.com/service/Calculator/","apikey");
		String apikey="rightkarma";
		try {
			SoapHeader soapHeader = new SoapHeader(qname, apikey, new JAXBDataBinding(apikey.getClass()));
			message.getHeaders().add(soapHeader);
			
		} catch (JAXBException e) {
			System.out.println("test message interceptor error");
			e.printStackTrace();
		}
	}

	private void printHeader(String msg, SoapMessage message) {
		System.out.println(msg);
		message.getHeaders().forEach(x->System.out.println(x.getName().toString()));
	}

}
