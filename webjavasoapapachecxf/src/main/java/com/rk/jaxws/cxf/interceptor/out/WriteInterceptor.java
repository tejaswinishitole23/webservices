package com.rk.jaxws.cxf.interceptor.out;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

public class WriteInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	public WriteInterceptor() {
		super(Phase.WRITE);
	}

	@Override
	public void handleMessage(SoapMessage soapMessage) throws Fault {
		System.out.println("Interceptor:"+this.getClass().getName());
	}

}
