package com.rightkarma.svc.rest.client;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.rightkarma.svc.rest.model.SalutationRequest;
import com.rightkarma.svc.rest.model.SalutationResponse;

public class AsyncRESTClient {
	public static void main(String[] args) {
		asyncClientCall();
	}
	
	private static void asyncClientCall() {
		Client client = ClientBuilder.newClient();
		SalutationRequest request=new SalutationRequest();
		request.setSalutation("Missrest");
		Entity<SalutationRequest> entity=Entity.entity(request, MediaType.APPLICATION_JSON);
		String url="http://localhost:8080/jersey/webapi/myresource/guest/{guest}/salute";
		Future<Response> future=client.target(url)
				.resolveTemplate("guest", "Rester")
				.queryParam("sleepSeconds", 5)
				.request(MediaType.APPLICATION_JSON)
				.async()
				.post(entity);
		
		System.out.println(LocalDateTime.now()+"~"+"call made. waiting for response.");
		Response response = null;
		try {
			response = future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		System.out.println(LocalDateTime.now()+"~"+"response code:"+response.getStatus());
		System.out.println(LocalDateTime.now()+"~"+"response from server with proper class:"+response.readEntity(SalutationResponse.class));
		//System.out.println(LocalDateTime.now()+"~"+"response from server:"+response.readEntity(String.class)); // response.readEntity works only once. Gives error - Entity input stream has already been closed.
		client.close();
	}

}
