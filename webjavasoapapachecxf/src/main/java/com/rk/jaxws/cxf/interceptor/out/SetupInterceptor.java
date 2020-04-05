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

import com.rk.jaxws.cxf.interceptor.common.InterceptorUtil;

public class SetupInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	public SetupInterceptor() {
		super(Phase.SETUP);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		System.out.println("Interceptor:"+this.getClass().getName());
		InterceptorUtil.printAvailableFormats(message);
		
		System.out.println("print header - 1st time.");
		message.getHeaders().forEach(x->System.out.println(x.getName().toString()));
		QName qname = new QName("http://www.rk.com/service/Calculator/","apikey");
		String apikey="rightkarma";
		try {
			SoapHeader soapHeader = new SoapHeader(qname, apikey, new JAXBDataBinding(apikey.getClass()));
			message.getHeaders().add(soapHeader);
			System.out.println("print header - 2nd time.");
			message.getHeaders().forEach(x->System.out.println(x.getName().toString()));
		} catch (JAXBException e) {
			System.out.println("test message interceptor error");
			e.printStackTrace();
		}
		
	}

}
