package com.rk.web.soap.basic;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/* 
 * LEARN - 
 * URL - http://localhost:8080/webjavasoapbasic/calc?wsdl
*/

@WebService
public class CalculatorWS {
	
	@WebMethod(action="add")
    public int add(@WebParam(name = "i") int i,  @WebParam(name = "j") int j) {
        int k = i + j;
        return k;
    }
}
