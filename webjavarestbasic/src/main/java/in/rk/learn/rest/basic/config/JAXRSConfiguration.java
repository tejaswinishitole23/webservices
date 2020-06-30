package in.rk.learn.rest.basic.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import in.rk.learn.rest.basic.endpoint.GreeterResource;

public class JAXRSConfiguration extends Application {

	private Set<Object> singletons = new HashSet<Object>();

	public JAXRSConfiguration() {
		singletons.add(new GreeterResource());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

}
