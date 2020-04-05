package com.rk.jaxws.cxf.interceptor.out;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import com.rk.jaxws.cxf.interceptor.common.InterceptorUtil;

public class SendInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	public SendInterceptor() {
		super(Phase.WRITE);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		
		System.out.println("Interceptor:"+this.getClass().getName());
		InterceptorUtil.printAvailableFormats(message);
	}

}
