package in.rk.learn.web.java.rest.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.apache.cxf.message.Message;
import org.springframework.web.bind.annotation.RequestBody;

import in.rk.learn.web.java.rest.codegen.CalcHeaderInfo;
import in.rk.learn.web.java.rest.codegen.CalculateDataRequest;
import in.rk.learn.web.java.rest.codegen.CalculateDataResponse;
import in.rk.learn.web.java.rest.codegen.ObjectFactory;


//@Path("calculator")
@Produces({"application/xml", "application/json"})
@Consumes({"application/xml", "application/json"})	
public class CalculatorWS {

	@Context 
	MessageContext jaxrsContext;
	
	public CalculatorWS(){
		System.out.println("CalculatorWS created");
	}

	@POST
//	@ResponseBody - what is this for 
	public CalculateDataResponse addOrMultiply(@RequestBody CalculateDataRequest request, @HeaderParam("RequestId") String requestId ) {
		System.out.println("\n\nRunning:"+this.getClass().getName()+" RequestId : "+requestId);
		String requestId2 = jaxrsContext.getContent(CalcHeaderInfo.class).getRequestId();
		System.out.println("request id using context:"+requestId2);
		ObjectFactory factory = new ObjectFactory();
		CalculateDataResponse response = factory.createCalculateDataResponse();
		switch ( request.getOperation()) {
			case "+":response.setReturnValue(add(request.getNumbers()));break;
			case "*":response.setReturnValue(multiply(request.getNumbers()));break;
			default:response.setReturnValue(0);
		}
		
		addHeaderInEndPoint();
		return response;
	}
	
	private void addHeaderInEndPoint() {
		
		System.out.println("list headers using jaxrsContext.get(Message.PROTOCOL_HEADERS)");
		Object object = jaxrsContext.get(Message.PROTOCOL_HEADERS);
		if ( object != null) {
			System.out.println(object.toString());
			TreeMap<String, List<String>> map = (TreeMap)object;
			Set keySet = map.keySet();
			keySet.forEach(x->System.out.println(x+":"+map.get(x)));
			List<String> list=new ArrayList<>();
			list.add("TestHeaderValue");
			map.put("testHeader",list);
		}
		
		System.out.println("list headers using jaxrsContext.getHttpHeaders()");
		MultivaluedMap<String,String> requestHeaders = jaxrsContext.getHttpHeaders().getRequestHeaders();
		requestHeaders.keySet().forEach(x->System.out.println(x+" : "+requestHeaders.get(x)));
		
		// you cant use PROTOCOL_HEADERS for manipulating response Headers.
		// for that you need to use Response Object.
		jaxrsContext.getHttpServletResponse().addHeader("headerFromEndPoint", new Date().toString());
		System.out.println("list header using servlet response:");
		Collection<String> headerNames = jaxrsContext.getHttpServletResponse().getHeaderNames();
		for ( String hdr : headerNames ) {
			System.out.println("hdr:"+hdr+" value:"+jaxrsContext.getHttpServletResponse().getHeader(hdr));
		}

		// add something to context for later use
		System.out.println("jaxrsContext hash:"+jaxrsContext.hashCode());
		jaxrsContext.put("RandomKeyForLaterUse", "KEYVALUE");
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
	
}
