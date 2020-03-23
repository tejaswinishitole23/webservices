package com.rk.jaxws.cxf.interceptor.out;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

public class PrepareSendInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	public PrepareSendInterceptor() {
		super(Phase.PREPARE_SEND);
	}

	@Override
	public void handleMessage(SoapMessage soapMessage) throws Fault {
		System.out.println("Interceptor:"+this.getClass().getName());
	}

}
