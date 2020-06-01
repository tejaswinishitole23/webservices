package in.rk.jaxws.config;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import in.rk.jaxws.api.CalculatorWS;
import in.rk.jaxws.interceptor.in.PreInvokeInterceptor;
import in.rk.jaxws.interceptor.in.PreStreamInterceptor;
import in.rk.jaxws.interceptor.in.PrintInterceptorsAndMessageFormats;
import in.rk.jaxws.interceptor.in.Readnterceptor;
import in.rk.jaxws.interceptor.out.SetupInterceptor;

/*CAUTION - don't forget context scanning. otherwise no service beans will be available. You need too add xlmns:context, and xsi:schemaLocation */
@Configuration
public class CalculatorAppConfig {

	@Autowired
	PreInvokeInterceptor preInvokeInterceptor;

	@Autowired
	PreStreamInterceptor preStreamInterceptor;

	@Autowired
	PrintInterceptorsAndMessageFormats printInterceptorsAndMessageFormats;

	@Autowired
	SetupInterceptor setupInterceptor;

	@Bean
	public ServletRegistrationBean<CXFServlet> dispatcherServlet() {
		return new ServletRegistrationBean<CXFServlet>(new CXFServlet(), "/api/*");
	}

	@Bean
	@Primary
	public DispatcherServletPath dispatcherServletPathProvider() {
		return () -> "";
	}

	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		SpringBus cxfBus = new SpringBus();
		return cxfBus;
	}

	@Bean
	public void addInterceptorsToBus() {
		Bus defaultBus = springBus();
		System.out.println("bus in addInterceptorsToBus:" + defaultBus.toString());
		defaultBus.getInInterceptors().add(new Readnterceptor());
		defaultBus.getInInterceptors().add(preInvokeInterceptor);
		defaultBus.getInInterceptors().add(preStreamInterceptor);
		defaultBus.getInInterceptors().add(printInterceptorsAndMessageFormats);
		defaultBus.getOutInterceptors().add(setupInterceptor);
	}

	@Bean
	public String testBean() {
		System.out.println("created testBean");
		return "TestBean";
	}

	@Autowired()
	CalculatorWS calculatorWS;

	@Bean
	public EndpointImpl calculator() {
		Bus defaultBus = springBus();
		System.out.println(defaultBus);
		System.out.println(defaultBus.getId());
		System.out.println("bus in calculator:" + defaultBus.toString());
		EndpointImpl endPoint = new EndpointImpl(defaultBus, calculatorWS);
		endPoint.setAddress("/services/calculator");
		endPoint.publish();

		return endPoint;
	}

}
