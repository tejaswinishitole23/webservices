package com.rk.jaxws.cxf.interceptor.out;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

public class SetupInterceptor extends AbstractPhaseInterceptor<Message> {

	public SetupInterceptor() {
		super(Phase.SETUP);
	}

	@Override
	public void handleMessage(Message message) throws Fault {
		SoapMessage soapMessage=(SoapMessage)message;
		System.out.println("Interceptor:"+this.getClass().getName());
		System.out.println("print header - 1st time.");
		soapMessage.getHeaders().forEach(x->System.out.println(x.getName().toString()));
		QName qname = new QName("http://www.rk.com/service/Calculator/","apikey");
		String apikey="rightkarma";
		try {
			SoapHeader soapHeader = new SoapHeader(qname, apikey, new JAXBDataBinding(apikey.getClass()));
			soapMessage.getHeaders().add(soapHeader);
			System.out.println("print header - 2nd time.");
			soapMessage.getHeaders().forEach(x->System.out.println(x.getName().toString()));
		} catch (JAXBException e) {
			System.out.println("test message interceptor error");
			e.printStackTrace();
		}
		
	}

}
