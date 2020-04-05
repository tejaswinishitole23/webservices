package com.rk.jaxws.cxf.interceptor.in;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.util.ListIterator;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.InterceptorChain;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.staxutils.StaxUtils;
import org.w3c.dom.Document;
import org.xml.sax.XMLReader;

import com.rk.jaxws.cxf.interceptor.common.InterceptorUtil;

public class TestMessageInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	public TestMessageInterceptor() {
		super(Phase.UNMARSHAL);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		System.out.println("Interceptor:" + this.getClass().getName());
		InterceptorUtil.printAvailableFormats(message);
//		readMessageBody(message);
	}

	private void readMessageBody(SoapMessage message) {
		try {
			XMLStreamReader xmlReader = message.getContent(XMLStreamReader.class);
			
			if ( xmlReader!=null) {
				
				XMLInputFactory infactory = XMLInputFactory.newInstance();
				XMLOutputFactory factory      = XMLOutputFactory.newInstance();
				StringWriter sw = new StringWriter();
				XMLStreamWriter writer = factory.createXMLStreamWriter(sw);
				StaxUtils.copy(xmlReader, writer);
				
				writer.flush();
				sw.flush();
				StringBuffer sb = sw.getBuffer();
		        System.out.println("sb:" + sb.toString());

				message.setContent(XMLStreamReader.class, xmlReader);
				
			} else {
				System.out.println("XMLReader is null");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}

	
	

}
