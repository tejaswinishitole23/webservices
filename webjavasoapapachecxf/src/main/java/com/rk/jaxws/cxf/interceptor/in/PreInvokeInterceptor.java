package com.rk.jaxws.cxf.interceptor.in;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;

public class PreInvokeInterceptor extends AbstractSoapInterceptor {

	public PreInvokeInterceptor() {
		super(Phase.PRE_INVOKE);
	}

	@Override
	public void handleMessage(SoapMessage soapMessage) throws Fault {
		System.out.println("Interceptor:"+this.getClass().getName());
	}

}
