package in.rk.jaxws.cxfanno.interceptor.in;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;

public class Readnterceptor extends AbstractSoapInterceptor {

	public Readnterceptor() {
		super(Phase.READ);
		System.out.println(this.getClass().getName() +" created");
	}
	

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		System.out.println("App Interceptor:" + this.getClass().getName());
	}

}
