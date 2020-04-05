package com.rk.web.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWorld
 *
*  You can hit this servlet by calling this url - http://localhost:8080/webjavaservlet/hello
*  CURL commandss for testing
*  curl "http://localhost:8080/webjavaservlet/hello?a=Str1&b=Str2"
*  curl --request POST --data "This is body" "http://localhost:8080/webjavaservlet/hello"
*  
*/

@WebServlet("/")
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BaseServlet() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	response.getWriter().append("<!DOCTYPE html><html><head><title>HTML output</title></head><body style=\"background-color:#AABBCC;\">")
		.append("Welcome !!")
		.append("</body></html>");
	}
	
}
