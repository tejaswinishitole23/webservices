package com.rk.jaxws.cxf.interceptor.in;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.staxutils.DepthXMLStreamReader;
import org.apache.cxf.staxutils.StaxUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.rk.jaxws.cxf.interceptor.common.InterceptorUtil;

public class ReadInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	public ReadInterceptor() {
		super(Phase.READ);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		System.out.println("Interceptor:"+this.getClass().getName());
		InterceptorUtil.printAvailableFormats(message);
//		readXMLMessage(message); // LEARN - works only in Read.
	}

	public static void readXMLMessage(SoapMessage message) {
		
		 
	}
	
}
