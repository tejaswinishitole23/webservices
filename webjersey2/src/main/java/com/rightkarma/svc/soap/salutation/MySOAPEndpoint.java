package com.rightkarma.svc.soap.salutation;

import java.time.LocalDateTime;

import javax.jws.WebService;

import com.rightkarma.svc.rest.model.SalutationRequest;
import com.rightkarma.svc.rest.model.SalutationResponse;

/*
 * URL - http://localhost:8080/jersey/soap . the last word - soap comes from what you put in endpoint declaration in sun-jaxws.xml
 * 
 */
@WebService
public class MySOAPEndpoint {
	
	public String getIt() {
		return "Got it !! "+LocalDateTime.now();
	}
	
	public SalutationResponse salute(SalutationRequest request, String guest) {
		String salutation= (null==request)?"Mr ":request.getSalutation();
		guest = (null == guest)? "Missing Name": guest;
		System.out.println("salutation:"+salutation);

		SalutationResponse response=new SalutationResponse();
		response.setSalutationResponse("Hello, "+salutation+" "+guest);
		return response;
	}
}
