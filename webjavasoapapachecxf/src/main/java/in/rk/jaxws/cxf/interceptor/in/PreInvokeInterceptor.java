package in.rk.jaxws.cxf.interceptor.in;

import java.io.StringWriter;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import in.rk.jaxws.model.RequestData;

public class PreInvokeInterceptor extends AbstractSoapInterceptor {

	public PreInvokeInterceptor() {
		super(Phase.PRE_INVOKE);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		System.out.println("App Interceptor:" + this.getClass().getName());
		if (null==message.getContent(RequestData.class)) {
			message.setContent(RequestData.class, new RequestData());
		}
		RequestData requestData = message.getContent(RequestData.class);
		
		readMessageHeader(message, requestData);
		readMessageBody(message);
	}

	// LEARN - we can extract all the required information from header here.
	// but we can also keep raw Document (for random extraction later if needed) and String ( for simple logging )
	private void readMessageHeader(SoapMessage message, RequestData requestData) {

		try {
			List<Header> headers = message.getHeaders();
			Header headerSoap = headers.get(0);
			Element soapHeaderElement = (Element) headerSoap.getObject();
			Document soapHeaderDocument = soapHeaderElement.getOwnerDocument();
			DOMSource domSource = new DOMSource(soapHeaderDocument);
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			StringWriter sw = new StringWriter();
			StreamResult sr = new StreamResult(sw);
			transformer.transform(domSource, sr);

			requestData.getExtractedHeaderVO().setHeaderXMLString(sw.toString());
			requestData.getExtractedHeaderVO().setHeaderDocument(soapHeaderDocument);

			// extract requestId
			NodeList requestId = soapHeaderDocument.getElementsByTagName("requestId");
			Node item = requestId.item(0);
			requestData.getExtractedHeaderVO().setRequestId(item.getTextContent());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Learn - This is just to show where the message object is finally kept before
	// passing on to the service.
	@SuppressWarnings("rawtypes")
	private void readMessageBody(SoapMessage message) {
		List contents = message.getContent(List.class);
		for (Object object : contents) {
			System.out.println(object.getClass().getCanonicalName());
			System.out.println("Object found is :" + object);
		}

	}

}
