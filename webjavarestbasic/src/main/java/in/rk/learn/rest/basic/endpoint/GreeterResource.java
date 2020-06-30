package in.rk.learn.rest.basic.endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/greetuser")
public class GreeterResource {

	@GET
	@Path("/hi")
	public String greetUser() {
		return "Hi";
	}
}
