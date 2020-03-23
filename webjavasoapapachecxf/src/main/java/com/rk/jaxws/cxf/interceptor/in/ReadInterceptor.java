package com.rk.jaxws.cxf.interceptor.in;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

public class ReadInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	public ReadInterceptor() {
		super(Phase.READ);
	}

	@Override
	public void handleMessage(SoapMessage soapMessage) throws Fault {
		System.out.println("Interceptor:"+this.getClass().getName());
		
	}

	
}
