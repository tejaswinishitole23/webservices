package in.rk.jaxws.cxfanno.interceptor.out;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;

@Component
public class SetupInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	public SetupInterceptor() {
		super(Phase.SETUP);
		System.out.println(this.getClass().getName() + " created");
	}

	// LEARN
	// Apache CXF till this point has headers from incoming message. but from here,
	// it will drop all of them
	// but keep any new headers you add.
	// IN this interceptor, two new headers are added
	// 1 - new header called apidescription
	// 2 - new header where data is copied from incoming header

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		System.out.println("App Interceptor:" + this.getClass().getName());
		System.out.println("In this interceptor, alter the header for outgoing message\n");

		printHeader("pre alteration", message);
		addNewGenericHeader(message);
		findExistingInputHeadersToCopy(message);
		printHeader("post alertation", message);

	}

	private void findExistingInputHeadersToCopy(SoapMessage message) {
		List<Header> newHeaders = new ArrayList<>();
		// iterate existing headers to find what is required.
		List<Header> headers = message.getHeaders();
		if (null != headers && headers.size() > 0) {

			for (Header header : headers) {
				if (header.getName().toString().equalsIgnoreCase("random")) {
					Element element = (Element) header.getObject();
					String nodeValue = element.getTextContent();
					Header newHeader;
					try {
						newHeader = new SoapHeader(new QName("randomout"), nodeValue,
								new JAXBDataBinding(nodeValue.getClass()));
						newHeaders.add(newHeader);
					} catch (JAXBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
		// add new headers to message
		newHeaders.forEach(x -> message.getHeaders().add(x));
	}

	private void addNewGenericHeader(SoapMessage message) {
		QName qname = new QName("http://www.rk.com/service/Calculator/", "apidescription");
		String apidescription = "This API is unsupported";
		try {
			SoapHeader soapHeader = new SoapHeader(qname, apidescription,
					new JAXBDataBinding(apidescription.getClass()));
			message.getHeaders().add(soapHeader);

		} catch (JAXBException e) {
			System.out.println("test message interceptor error");
			e.printStackTrace();
		}
	}

	private void printHeader(String msg, SoapMessage message) {
		System.out.println(msg);
		message.getHeaders().forEach(x -> System.out.println(x.getName().toString()));
	}

}
