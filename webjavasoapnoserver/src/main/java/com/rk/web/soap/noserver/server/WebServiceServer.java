package com.rk.web.soap.noserver.server;

import javax.xml.ws.Endpoint;

import com.rk.web.soap.noserver.Calculator2WS;

/*
 * LEARN
 * To create a simple, lightweight server for deploying the web service, we use the method publish() of the javax.xml.ws.Endpoint class:
 * 
 * The JAX-WS implementation will create necessary infrastructure to start the server using some default configuration. And once started, the server is ready to receiving client’s requests.
 * 
 * Below class should be started like any other java class, and NOT as java web app
 * 
 * Jax-ws will create wsdl - http://localhost:8081/calculator2WS?wsdl
 * 
 * */

public class WebServiceServer {
	 
    /**
     * Starts a simple server to deploy the web service.
     */
    public static void main(String[] args) {
        String bindingURI = "http://localhost:8081/calculator2WS";
        Calculator2WS calculatorWS = new Calculator2WS();
        Endpoint.publish(bindingURI, calculatorWS);
        System.out.println("Server started at: " + bindingURI);
    }
}