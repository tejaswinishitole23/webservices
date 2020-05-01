package com.rightkarma.svc;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

@ApplicationPath("webapi")
public class ApplicationConfig extends ResourceConfig {
	
	public ApplicationConfig() {
		packages("com.rightkarma.svc.rest");
		property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true); // For Bean Validation to return proper bean validation error to client
	}
}
