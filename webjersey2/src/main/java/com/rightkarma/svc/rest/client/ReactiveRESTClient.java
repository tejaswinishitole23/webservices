package com.rightkarma.svc.rest.client;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.rightkarma.svc.rest.model.SalutationRequest;
import com.rightkarma.svc.rest.model.SalutationResponse;

public class ReactiveRESTClient {
	private static String[] names = { "Ann", "Jean", "Lin" };

	public static void main(String[] args) {
		reactiveClientCall();
	}

	private static void reactiveClientCall() {
		Client client = ClientBuilder.newClient();
		SalutationRequest request = new SalutationRequest();
		request.setSalutation("Respondent ");
		Entity<SalutationRequest> entity = Entity.entity(request, MediaType.APPLICATION_JSON);
		String url = "http://localhost:8080/jersey/webapi/sse/guest/{guest}/salute";

		CompletableFuture<Response> whenCompleteAsync = null;
		for (String name : names) {
			System.out.println("name:" + name);
			whenCompleteAsync = client.target(url)
				.resolveTemplate("guest", name)
				.request(MediaType.APPLICATION_JSON)
				.rx()
				.post(entity)
				.toCompletableFuture().exceptionally((e) -> {
						e.printStackTrace();
						return null;
					})
				.whenCompleteAsync((response, throwable) -> {
						if (null != throwable) {
							System.out.println("Exception:" + throwable.getMessage());
						}
						if (response != null) {
							System.out.println("not null response. status:" + response.getStatus());
							System.out.println("Response:" + response.readEntity(String.class));
						} else {
							System.out.println("null response");
						}

					});
		}
		try {
			Response response = whenCompleteAsync.get();
			System.out.println("Final RC:"+response.getStatus());
			client.close();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		

	}

}
