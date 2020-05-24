package in.rk.learn.web.java.rest.interceptor.in;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.springframework.stereotype.Component;

import in.rk.learn.web.java.rest.codegen.CalcHeaderInfo;

@Component
public class PreStreamInterceptor extends AbstractPhaseInterceptor<Message> {

	public PreStreamInterceptor() {
		super(Phase.PRE_STREAM);
		System.out.println(this.getClass().getName() +" created");
	}
	
	@Override
	public void handleMessage(Message message) throws Fault {
		System.out.println("handleMessage in App Interceptor:"+this.getClass().getName());
		
		// protocol headers
		@SuppressWarnings("unchecked")
		Map<String, List<String>> protocolHeaders = (Map<String, List<String>>)message.get(Message.PROTOCOL_HEADERS);
		for ( String header : protocolHeaders.keySet()) {
			System.out.println("header:"+header+":"+Arrays.toString(protocolHeaders.get(header).toArray()));
		}
		
		CalcHeaderInfo calcHeaderInfo = new CalcHeaderInfo();
		calcHeaderInfo.setRequestId(protocolHeaders.get("RequestId").get(0));
		
		message.setContent(CalcHeaderInfo.class, calcHeaderInfo);
		
		// Add SoapRequestString to RequestData
		System.out.println("msg:"+extractPayLoadStringFromMessage(message));
	}
	
	
	// LEARN - Using InputStream to get data works only in PRE_STREAM
	private String extractPayLoadStringFromMessage(Message message) {
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
