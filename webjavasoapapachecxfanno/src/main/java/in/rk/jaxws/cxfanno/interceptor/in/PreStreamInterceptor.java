package in.rk.jaxws.cxfanno.interceptor.in;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.springframework.stereotype.Component;

import in.rk.jaxws.cxfanno.model.RequestData;
@Component
public class PreStreamInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	public PreStreamInterceptor() {
		super(Phase.PRE_STREAM);
		System.out.println(this.getClass().getName() +" created");
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		System.out.println("App Interceptor:"+this.getClass().getName());

		if (null==message.getContent(RequestData.class)) {
			message.setContent(RequestData.class, new RequestData());
		}
		RequestData requestData = message.getContent(RequestData.class);
		
		
		// Add protocol headers
		@SuppressWarnings("unchecked")
		Map<String, List<String>> protocolHeaders = (Map<String, List<String>>)message.get(Message.PROTOCOL_HEADERS);
		requestData.getProtocolHeaders().putAll(protocolHeaders);
		
		// Add SoapRequestString to RequestData
		requestData.setSoapString(extractPayLoadStringFromMessage(message));
		
	}
	
	
	// LEARN - Using InputStream to get data works only in PRE_STREAM
	private String extractPayLoadStringFromMessage(SoapMessage message) {
		try {
			//Get the message body into payload[] and set a new non-consumed  inputStream into Message
	        InputStream in = message.getContent(InputStream.class);
	        byte payload[] = IOUtils.readBytesFromStream(in);
	        ByteArrayInputStream bin = new ByteArrayInputStream(payload);
	        message.setContent(InputStream.class, bin);
	        
	        return new String(payload, StandardCharsets.UTF_8);
	        
		} catch ( Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
/* sample protocol headers :
accept-encoding
gzip,deflate
connection
Keep-Alive
Content-Length
700
content-type
text/xml;charset=UTF-8
host
localhost:8080
SOAPAction
"http://www.rk.com/service/Calculator/AddOrMultiply"
user-agent
Apache-HttpClient/4.1.1 (java 1.5)

*/
