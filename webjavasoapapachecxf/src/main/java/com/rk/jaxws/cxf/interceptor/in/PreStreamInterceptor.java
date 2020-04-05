package com.rk.jaxws.cxf.interceptor.in;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import com.rk.jaxws.cxf.interceptor.common.InterceptorUtil;

public class PreStreamInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	public PreStreamInterceptor() {
		super(Phase.PRE_STREAM);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		System.out.println("Interceptor:"+this.getClass().getName());
		InterceptorUtil.printAvailableFormats(message);
		
		printUsingInputStream(message);
		printProtocolHeaders(message);
		
		
	}
	
	private void printProtocolHeaders(SoapMessage message) {
		System.out.println("Print Headers:");
		Map<String, List<String>> headers2 =
		        (Map<String, List<String>>)message.get(Message.PROTOCOL_HEADERS);
		for ( String hdr: headers2.keySet()) {
			System.out.println(hdr);
		}
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
	

}
