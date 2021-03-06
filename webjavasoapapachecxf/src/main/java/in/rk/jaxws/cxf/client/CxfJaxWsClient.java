package in.rk.jaxws.cxf.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import in.rk.schema.calculator.CalculateDataRequest;
import in.rk.schema.calculator.CalculateDataResponse;
import in.rk.schema.calculator.UserType;
import in.rk.service.calculator.Calculator;

public class CxfJaxWsClient {
	
	public static void main(String[] args) {
		try (ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("com/rk/jaxws/cxf/client/client-beans.xml")) {
			Calculator calculator=(Calculator)ctx.getBean("client");
			
			CalculateDataRequest request = new CalculateDataRequest();
			request.setOperation("+");
			request.getNumbers().add(23);
			request.getNumbers().add(24);
			UserType userType = new UserType();
			userType.setUserId(7);
			userType.setUserName("SomeUser");
			request.getUser().add(userType );
			
			CalculateDataResponse response = calculator.addOrMultiply(request);
			System.out.println("Result: "+response.getReturnValue());
		}
	}

}
