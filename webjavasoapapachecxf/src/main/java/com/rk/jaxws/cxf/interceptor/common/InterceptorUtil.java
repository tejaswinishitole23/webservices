package com.rk.jaxws.cxf.interceptor.common;

import java.io.StringWriter;
import java.util.Iterator;
import java.util.Set;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.cxf.binding.soap.SoapMessage;
import org.w3c.dom.Document;

public class InterceptorUtil {

	public static void printAvailableFormats(SoapMessage message) {
		Set<Class<?>> contentFormats = message.getContentFormats();
		Iterator<Class<?>> iterator = contentFormats.iterator();
		while (iterator.hasNext()) {
			Class<?> next = iterator.next();
			System.out.println("contentFormat:" + next.getCanonicalName());
		}
		System.out.println('\n');
		System.out.println('\n');
	}

	public static void printStringFromDocument(Document soapHeaderDocument)
			throws TransformerConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		System.out.println("printing String from Document..START");
		DOMSource domSource = new DOMSource(soapHeaderDocument);
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		StringWriter sw = new StringWriter();
		StreamResult sr = new StreamResult(sw);
		transformer.transform(domSource, sr);
		System.out.println(sw.toString());
		System.out.println("printing String from Document..END");
	}
	
	
}
