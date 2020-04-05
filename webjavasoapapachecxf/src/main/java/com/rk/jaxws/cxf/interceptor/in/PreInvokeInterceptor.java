package com.rk.jaxws.cxf.interceptor.in;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.rk.jaxws.cxf.interceptor.common.InterceptorUtil;

public class PreInvokeInterceptor extends AbstractSoapInterceptor {

	public PreInvokeInterceptor() {
		super(Phase.PRE_INVOKE);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		System.out.println("Interceptor:" + this.getClass().getName());
		InterceptorUtil.printAvailableFormats(message);
		readMessageBody(message);
		readMessageHeader(message);
	}

	

	@SuppressWarnings("rawtypes")
	private void readMessageBody(SoapMessage message) {
		List contents = message.getContent(List.class);
		for (Object object : contents) {
			System.out.println(object.getClass().getCanonicalName());
			System.out.println("Object found is :" + object);
		}

	}
	
	private void readMessageHeader(SoapMessage message) {

		try {
			List<Header> headers = message.getHeaders();
			Header headerSoap = headers.get(0);
			Element soapHeaderElement = (Element) headerSoap.getObject();
			Document soapHeaderDocument = soapHeaderElement.getOwnerDocument();

			System.out.println("String from SOAP Header:");

			InterceptorUtil.printStringFromDocument(soapHeaderDocument);
			
			// extract requestId
			NodeList requestId = soapHeaderDocument.getElementsByTagName("requestId");
			Node item = requestId.item(0);
			System.out.println("Important Header Info : "+item.getNodeName()+":"+item.getTextContent());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}



}
