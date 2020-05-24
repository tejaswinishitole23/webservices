package in.rk.learn.web.java.springrest.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class CalculatorWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {CalculatorAppConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
}