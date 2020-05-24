package in.rk.jaxws.cxfanno.serviceimpl;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.cxf.jaxws.context.WrappedMessageContext;
import org.springframework.stereotype.Service;

import in.rk.jaxws.cxfanno.interceptor.out.WriteInterceptor;
import in.rk.jaxws.cxfanno.model.ExtractedHeaderVO;
import in.rk.jaxws.cxfanno.model.RequestData;
import in.rk.schema.calculator.CalculateDataRequest;
import in.rk.schema.calculator.CalculateDataResponse;
import in.rk.schema.calculator.ObjectFactory;
import in.rk.schema.mwdsheader.MwdsHeaderInfo;
import in.rk.schema.mwdsheader.MwdsHeaderInfoResponse;
import in.rk.service.calculator.Calculator;

/* LEARN - 
 * portName and serviceName are kept under wsdl:service in wsdl.
 * endpointInterface - fully qualified interface name
 * targetNamespace - defined in wsdl under targetNamespace at top
 * 
 * http://localhost:8080/webjavasoapapachecxfanno/services/calculator?wsdl
 * 
 * All services - http://localhost:8080/webjavasoapapachecxf/
 * 
 *  */
@Service
@WebService(portName="CalculatorSOAP", serviceName="Calculator",
endpointInterface="in.rk.service.calculator.Calculator", 
targetNamespace="http://www.rk.com/service/Calculator/")
public class CalculatorWS implements Calculator {

	@Resource
	private WebServiceContext context;
	
	public CalculatorWS(){
		System.out.println("CalculatorWS created");
	}
	
	@Override
	public CalculateDataResponse addOrMultiply(CalculateDataRequest request, MwdsHeaderInfo hdr,
			Holder<MwdsHeaderInfoResponse> hdrResponseHolder) {
		System.out.println("\n\nRunning:"+this.getClass().getName());
		System.out.println("header:"+hdr.getRequestId()+" "+hdr.getImpersonateUser());
		
		System.out.println(hdrResponseHolder.value);
		MwdsHeaderInfoResponse hdrRespValue=new MwdsHeaderInfoResponse();
		hdrRespValue.setApidescription("test api for learning");
		hdrRespValue.setRequestId(hdr.getRequestId());
		hdrResponseHolder.value=hdrRespValue;
		
		accessSoapMessageAndPreviousInterceptorData();
		ObjectFactory factory = new ObjectFactory();
		CalculateDataResponse response = factory.createCalculateDataResponse();
		switch ( request.getOperation()) {
			case "+":response.setReturnValue(add(request.getNumbers()));break;
			case "*":response.setReturnValue(multiply(request.getNumbers()));break;
			default:response.setReturnValue(0);
		}
		return response;
	}

	// LEARN - this method shows that you can access the original message
	// extract headers, user information etc from the message.
	// this can be used when you need something beyond the Java Bean with request details.
	private void accessSoapMessageAndPreviousInterceptorData() {
		System.out.println("*******************print principal user*******************\n");
		Principal userPrincipal = context.getUserPrincipal();
		System.out.println("user:"+userPrincipal);
		
		System.out.println("*******************print messageContext keyset*******************\n");
		MessageContext messageContext = context.getMessageContext();
		Set<String> keySet = messageContext.keySet();
		System.out.println("keyset:");
		keySet.forEach(x->System.out.println(x.toString()+":"+messageContext.get(x)));
		
		
		WrappedMessageContext wmc = (WrappedMessageContext)context.getMessageContext();
		SoapMessage soapMessage = (SoapMessage)wmc.getWrappedMessage();
		RequestData requestData = soapMessage.getContent(RequestData.class); 
		if ( null != requestData) {
			Map<String, List<String>> protocolHeaders = requestData.getProtocolHeaders();
			if ( null != protocolHeaders) {
				System.out.println("*******************print protocol headers*******************\n");
				protocolHeaders.keySet().forEach(x->System.out.println(x +"-"+protocolHeaders.get(x)));
			}
			if ( null != requestData.getSoapString() ) {
				System.out.println("*******************print soap message*******************\n");
				System.out.println("soap message:"+requestData.getSoapString());
			}
			if ( null != requestData.getExtractedHeaderVO()) {
				ExtractedHeaderVO extractedHeaderVO = requestData.getExtractedHeaderVO();
				if ( null != extractedHeaderVO.getHeaderDocument() ) {
					System.out.println("*******************print header first element*******************\n");
					String nodeName = extractedHeaderVO.getHeaderDocument().getFirstChild().getNodeName();
					System.out.println("first node:"+nodeName);
				}
				if ( null != extractedHeaderVO.getHeaderXMLString()) {
					System.out.println("*******************print header xml*******************\n");
					System.out.println("header xml:"+extractedHeaderVO.getHeaderXMLString());
				}
				System.out.println("*******************print requestId*******************\n");
				System.out.println("requestId:"+extractedHeaderVO.getRequestId());
			}
		}
		
		System.out.println("*******************End of Printing Information*******************\n");
	}

	private int multiply(List<Integer> numbers) {
		Integer result =1;
		for ( Integer i :numbers) {
			result=result*i;
		}
		return result;
	}

	private int add(List<Integer> numbers) {
		Integer result =0;
		for ( Integer i :numbers) {
			result=result+i;
		}
		return result;
	}
	
	private void attachInterceptorToEndPoint() {
		ServerFactoryBean sfb = new ServerFactoryBean();
		Server server = sfb.create();
		Endpoint endpt = server.getEndpoint();
		WriteInterceptor interceptor = new WriteInterceptor();
		endpt.getOutInterceptors().add(interceptor);
	}

	

}
