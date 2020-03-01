package com.rk.web.soap.noserver;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class Calculator2WS {
	
	@WebMethod(action="add")
	public int add(int i, int j) {
        int k = i + j;
        return k;
    }

}
