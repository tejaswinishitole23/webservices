package in.rk.learn.rest.basic.endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/*
URL for testing - http://localhost:8080/webjavarestbasic/greetuser/hi
*/

@Path("/greetuser")
public class GreeterResource {

	@GET
	@Path("/hi")
	public String greetUser() {
		return "Hi";
	}
}
