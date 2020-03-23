package com.rk.jaxws.cxf.interceptor.in;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;

public class ReadSoapInterceptor extends AbstractSoapInterceptor {

	public ReadSoapInterceptor() {
		super(Phase.READ);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		System.out.println("Interceptor:"+this.getClass().getName());
	}


}
