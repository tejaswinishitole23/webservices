package com.rk.jaxws.config;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.rk.jaxws.cxfanno.interceptor.in.PreInvokeInterceptor;
import com.rk.jaxws.cxfanno.interceptor.in.PreStreamInterceptor;
import com.rk.jaxws.cxfanno.interceptor.in.PrintInterceptorsAndMessageFormats;
import com.rk.jaxws.cxfanno.interceptor.in.Readnterceptor;
import com.rk.jaxws.cxfanno.interceptor.out.SetupInterceptor;
import com.rk.jaxws.cxfanno.serviceimpl.CalculatorWS;

/*CAUTION - don't forget context scanning. otherwise no service beans will be available. You need too add xlmns:context, and xsi:schemaLocation */
@Configuration
@ComponentScan("com.rk.jaxws.cxfanno")
@ImportResource({ "classpath:META-INF/cxf/cxf.xml", "classpath:META-INF/cxf/cxf-servlet.xml",
		"classpath:META-INF/cxf/cxf-extension-jaxws.xml" })
public class CalculatorAppConfig {

	@Autowired
	PreInvokeInterceptor preInvokeInterceptor;

	@Autowired
	PreStreamInterceptor preStreamInterceptor;

	@Autowired
	PrintInterceptorsAndMessageFormats printInterceptorsAndMessageFormats;

	@Autowired
	SetupInterceptor setupInterceptor;

	/*
	 * Interceptors would be called as per apache cxf pipeline. below sequence does
	 * not matter
	 */
	@Bean(name = Bus.DEFAULT_BUS_ID) // Default bus id is critical as you need to get hold of "cxf" bean
	public Bus getDefaultBus() {
		Bus defaultBus = BusFactory.getDefaultBus();
		System.out.println("bus in getDefaultBus:" + defaultBus.toString());
		return defaultBus;
	}
	
	@Bean
	public void addInterceptorsToBus() {
		Bus defaultBus = getDefaultBus();
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
	
	@Autowired
	CalculatorWS calculatorWS;

	/* This code does not work. This end-point is added using jaxws:endpoint configuration in beans.xml */
	@Bean
	public EndpointImpl calculator() {
		Bus defaultBus = getDefaultBus();

		System.out.println("bus in calculator:" + defaultBus.toString());
		EndpointImpl endPoint = new EndpointImpl(defaultBus, calculatorWS);

		endPoint.setAddress("/services/calculator");
		endPoint.setBus(defaultBus);
		endPoint.publish();

		return endPoint;
	}

}
