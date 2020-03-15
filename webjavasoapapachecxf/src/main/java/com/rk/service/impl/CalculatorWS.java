package com.rk.service.impl;

import java.util.List;

import javax.jws.WebService;

import com.rk.schema.calculator.CalculateDataRequest;
import com.rk.schema.calculator.CalculateDataResponse;
import com.rk.schema.calculator.ObjectFactory;
import com.rk.service.calculator.Calculator;

/* LEARN - portName and serviceName are kept under wsdl:service in wsdl.
 * endpointInterface - fully qualified interface name
 * targetNamespace - 
 *  */
@WebService(portName="CalculatorSOAP", serviceName="Calculator",
endpointInterface="com.rk.service.calculator.Calculator", 
targetNamespace="http://www.rk.com/service/Calculator/")
public class CalculatorWS implements Calculator {

	static {
		System.out.println("CalculatorWS. static");
	}
	{
		System.out.println("CalculatorWS. non-static");
	}
	@Override
	public CalculateDataResponse addOrMultiply(CalculateDataRequest request) {
		ObjectFactory factory = new ObjectFactory();
		CalculateDataResponse response = factory.createCalculateDataResponse();
		switch ( request.getOperation()) {
			case "+":response.setReturnValue(add(request.getNumbers()));break;
			case "*":response.setReturnValue(multiply(request.getNumbers()));break;
			default:response.setReturnValue(0);
		}
		return response;
	}

	private int multiply(List<Integer> numbers) {
		Integer result =1;
		for ( Integer i :numbers) {
			result=result*i;
		}
		return result;
	}

	private int add(List<Integer> numbers) {
		Integer result =0;
		for ( Integer i :numbers) {
			result=result+i;
		}
		return result;
	}

}
