package com.rk.jaxws.config;

import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CheckAppConfigBeans {
	
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(CalculatorAppConfig.class);
		
		listBeans(ctx, String.class);
		listBeans(ctx, AbstractPhaseInterceptor.class);
		Object bean = ctx.getBean("cxf");
		
		if (null!=bean) {
				System.out.println("bean:"+bean.toString());
		}
		
		//Object bean = ctx.getBeanNamesForType(String.class);
		Object testBean = ctx.getBean("testBean");
		Object beanInterceptor = ctx.getBean("preInvokeInterceptor");
		System.out.println("testBean:"+testBean.toString());
		System.out.println("beanInterceptor:"+beanInterceptor.toString());
	}

	private static void listBeans(ApplicationContext ctx, Class cls) {
		
		String[] beanNamesForType = ctx.getBeanNamesForType(cls);
		for ( int i = 0;i<beanNamesForType.length;i++ ) {
			System.out.println("i:"+beanNamesForType[i]);
		}
		
	}
}
