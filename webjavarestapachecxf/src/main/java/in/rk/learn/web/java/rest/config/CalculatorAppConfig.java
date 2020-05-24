package in.rk.learn.web.java.rest.config;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import in.rk.learn.web.java.rest.interceptor.in.PreInvokeInterceptor;
import in.rk.learn.web.java.rest.interceptor.in.PreStreamInterceptor;
import in.rk.learn.web.java.rest.interceptor.in.PrintInterceptorsAndMessageFormats;
import in.rk.learn.web.java.rest.interceptor.out.SetupInterceptor;
import in.rk.learn.web.java.rest.service.CalculatorWS;

/*CAUTION - don't forget context scanning. otherwise no service beans will be available. You need too add xlmns:context, and xsi:schemaLocation */
@Configuration
@ComponentScan("in.rk.learn.web.java.rest")
@ImportResource({ "classpath:META-INF/cxf/cxf.xml", "classpath:META-INF/cxf/cxf-servlet.xml", "classpath:beans.xml" })
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

	
	@Bean
	public JAXRSServerFactoryBean jaxRSServerFactoryBean() {
		System.out.println("in jaxRSServerFactoryBean");
		JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
		factoryBean.setResourceClasses(CalculatorWS.class);
		factoryBean.setResourceProvider(new SingletonResourceProvider(new CalculatorWS()));
		
		return factoryBean;
	}
	
}
