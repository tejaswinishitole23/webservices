package com.rightkarma.svc.rest.sse;

import java.time.LocalDateTime;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

import com.rightkarma.svc.rest.model.SalutationRequest;
import com.rightkarma.svc.rest.model.SalutationResponse;

/*
 * To Test use command line curl 
 * curl -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8080/jersey/webapi/sse/guest/TT/salute
 * 
 * */

@Path("sse")
public class MySSEResource {

	@Path("guest/{guest}/salute")
	@GET
	public void saluteSSE(@Context SseEventSink sink, @Context Sse sse, @PathParam("guest") String guest) {
		System.out.println("saluteSSE called. guest:" + guest);
		try {
			for (int i = 0; i < 10; i++) {
				OutboundSseEvent event = sse.newEvent("Hello, " + guest);
				System.out.println("Event Created." + i);
				sink.send(event);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(LocalDateTime.now() + " Closing SseEventSink");
			sink.close();
		}
	}

	@Path("guest/{guest}/salute")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void saluteSSE2(SalutationRequest request, @Context SseEventSink sink, @Context Sse sse,
			@PathParam("guest") String guest) {
		System.out.println("saluteSSE called. guest:" + guest);
		try {

			SalutationResponse response = new SalutationResponse();
			response.setSalutationResponse("Hello, " + request.getSalutation() + " " + guest);
			OutboundSseEvent event = sse.newEvent(response.toString());
			sink.send(event);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(LocalDateTime.now() + " Closing SseEventSink");
			sink.close();
		}
	}

}
