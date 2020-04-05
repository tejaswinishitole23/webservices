package com.rk.jaxws.cxf.interceptor.in;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.StaxInInterceptor;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import com.rk.jaxws.cxf.interceptor.common.InterceptorUtil;

public class PostStreamInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	public PostStreamInterceptor() {
		super(Phase.POST_STREAM);
		addBefore(StaxInInterceptor.class.getName());
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		System.out.println("Interceptor:" + this.getClass().getName());
		InterceptorUtil.printAvailableFormats(message);
	}
	
}
