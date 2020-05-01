package com.rightkarma.svc.rest.salutation;

import javax.inject.Singleton;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.rightkarma.svc.rest.model.SalutationRequest;
import com.rightkarma.svc.rest.model.SalutationResponse;

/*
 * URL 
 * 
 * For GET - http://localhost:8080/jersey/webapi/myresource 
 * 
 * For POST
 * http://localhost:8080/jersey/webapi/myresource/guest/TT/salute
 * Raw Body :
 	{
	"salutation" : "Miss"
	}
	Headers - Accept and Content-Type to be application/json
 *
 */
//@Singleton // for each call from client, a new MyResource is created, unless marked Singleton

/*
 * Important Annotations
 * @HeaderParam
 * @CookieParam
 * @BeanParam
 * @MartixParam
 * 
 * 
 * */

/*
 * @Context
 * 
 * The JAX-RS API from the Java EE ecosystem of technologies provides the annotation @Context, to inject 12 object instances related to the context of HTTP requests. It behaves just like the @Inject and @Autowired annotations in Java EE and Spring respectively.
FROM: https://dzone.com/articles/jax-rs-what-is-context
The object instances that it can inject are the following:

    SecurityContext – Security context instance for the current HTTP request
    Request – Used for setting precondition request processing
    Application, Configuration, and Providers -> Provide access to the JAX-RS application, configuration, and providers instances
    ResourceContext – Resource context class instances
    ServletConfig – The ServletConfig instance instance
    ServletContext – The ServletContext instance
    HttpServletRequest – The HttpServletRequest instance for the current request
    HttpServletResponse – The HttpServletResponse instance for the current request
    HttpHeaders – Maintains the HTTP header keys and values
    UriInfo – Query parameters and path variables from the URI called
    SseEventSink - JavaEE8 - server sent events
    Sse - JavaEE8 - server sent events

It is a little confusing to have both an @Inject and @Context when both do the same job of injecting objects, but it is envisioned that future version of Java EE will bring more alignment of annotation use.

 * 
 * */

@Path("myresource")
public class MyResource {
	
	@DefaultValue("NotPassed")
	@QueryParam("qp1")
	String queryParam1; // http://localhost:8080/jersey/webapi/myresource?qp1=Wow%20Great!!
	
	@Context
	UriInfo uriInfo ;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt(@Context HttpHeaders httpHeaders) {
		System.out.println("MyResource..getIt called.");
		MultivaluedMap<String,String> requestHeaders = httpHeaders.getRequestHeaders();
		StringBuffer sb = new StringBuffer();
		for ( String key: requestHeaders.keySet()) {
			sb.append(" ");
			sb.append(key);
			sb.append(":");
			sb.append(requestHeaders.get(key));
		}
		
		return "Got it !! "+queryParam1+" Host Info:"+httpHeaders.getRequestHeader("Host")+" Header Info:"+sb.toString()+" URI INFO:"+uriInfo.getAbsolutePath();
	}
	
	@Path("/guest/{guest}/salute")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response salute(SalutationRequest request, @PathParam("guest") String guest, @QueryParam("sleepSeconds") int sleepsecs) {
		try {
			System.out.println("sleepSeconds:"+sleepsecs);
			Thread.sleep(sleepsecs*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SalutationResponse response=new SalutationResponse();
		String salutation= (null==request)?"Mr ":request.getSalutation();
		guest = (null == guest)? "Missing Name": guest;
		System.out.println("salutation:"+salutation);
		response.setSalutationResponse("Hello, "+salutation+" "+guest);
		Response responseWrapper = Response.ok(response).build();
		return responseWrapper;
	}
	
	@Path("/guest/salute")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response salute(@NotBlank @FormParam("salutation") String salutation, @FormParam("guest") String guest) {
		SalutationResponse response=new SalutationResponse();
		salutation= (null==salutation)?"Mr ":salutation;
		guest = (null == guest)? "Missing Name": guest;
		System.out.println("salutation:"+salutation);
		response.setSalutationResponse("Hello, "+salutation+" "+guest);
		Response responseWrapper = Response.ok(response).build();
		return responseWrapper;
	}
}
