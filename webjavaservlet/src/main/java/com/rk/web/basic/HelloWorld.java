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

@WebServlet("/hello")
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HelloWorld() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String a = request.getParameter("a");
		String b = request.getParameter("b");
    	response.getWriter().append("Servlet:").append(this.toString()).append(". Parameters: a:").append(a).append(" b:").append(b);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletInputStream inputStream = request.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		StringBuffer sb = new StringBuffer();
		int readchar = bufferedReader.read();
		while ( readchar != -1) {
			char c = (char)readchar;
			sb.append(c);
			readchar=bufferedReader.read();
		}
		response.getWriter().append("Servlet:").append(this.toString()).append(". Content From Request:").append(sb.toString());
		
	}
    
    @Override
    public void init() throws ServletException {
    	System.out.println("servlet init called only once at beginning");
    	super.init();
    }
    
    @Override
    public void destroy() {
    	System.out.println("servlet destroy called only once at end");
    	super.destroy();
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	System.out.println("servlet service called to check http verb and call relevant method");
    	super.service(req, resp);
    }

}
