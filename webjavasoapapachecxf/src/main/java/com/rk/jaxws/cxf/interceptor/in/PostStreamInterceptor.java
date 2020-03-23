package com.rk.jaxws.cxf.interceptor.in;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.SoapVersion;
import org.apache.cxf.binding.soap.SoapVersionFactory;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.StaxInInterceptor;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.staxutils.DepthXMLStreamReader;
import org.apache.cxf.staxutils.StaxUtils;

public class PostStreamInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	public PostStreamInterceptor() {
		super(Phase.POST_STREAM);
		addBefore(StaxInInterceptor.class.getName());
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		System.out.println("Interceptor:" + this.getClass().getName());
		readXmlStream(message);
	}

	private void readXmlStream(SoapMessage message) {
		try {
			XMLStreamReader reader = message.getContent(XMLStreamReader.class);
			DepthXMLStreamReader xmlReader = new DepthXMLStreamReader(reader);
			if (xmlReader.nextTag() == XMLStreamConstants.START_ELEMENT) {
				String ns = xmlReader.getNamespaceURI();
				SoapVersion soapVersion = SoapVersionFactory.getInstance().getSoapVersion(ns);
				// advance just past header
				StaxUtils.toNextTag(xmlReader, soapVersion.getBody());
				// past body.
				xmlReader.nextTag();
			}
			String schemaNamespace = xmlReader.getName().getNamespaceURI();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
