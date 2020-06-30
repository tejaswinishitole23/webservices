package in.rk.learn.web.java.rest.config;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class CalculatorWebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		
		/* Create application context for the servlet */
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(CalculatorAppConfig.class); // if you want just one file for config
//		context.setConfigLocation(projectBasePackage); // scans whole package for config files
		
		/* add to SevletContext so that context is loaded */
		container.addListener(new ContextLoaderListener(context));
		
		/* now create servlet for your web services */
		Dynamic servlet = container.addServlet("CXFServlet", CXFServlet.class);
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/*");
	}
	
	
}