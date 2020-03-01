# webservices

**What is a Java web container aka Java EE container ?**

Java web applications are typically not running directly on the server. Java web applications are running inside a web container on the server.

The container provides a runtime environment for Java web applications. The container is for Java web applications what the JVM (Java Virtual Machine) is for local running Java applications. The container itself runs in the JVM.

In general, Java distinguishes two containers: the web container and the Java EE container. Typical web containers in the Java world are Tomcat or Jetty. A web container supports the execution of Java servlets and JavaServer Pages. A Java EE container supports additional functionality, for example, distribution of server load.

Most of the modern Java web frameworks are based on servlets. Popular Java web frameworks are GWT, JavaServer Faces, Struts and the Spring framework. These web frameworks usually require as a minimum container a web container.

# What is a Java Web application?

A Java web application is a collection of dynamic resources (such as Servlets, JavaServer Pages, Java classes and jars) and static resources (HTML pages and pictures). A Java web application can be deployed as a WAR (Web ARchive) file.

A WAR file is a zip file which contains the complete content of the corresponding web application.

# What are Java Web Standards ?

Standard Java technologies are defined via a standard process called the Java Community Process (JCP). The following technologies are  defined via the JCP.

** Servlet**

A servlet is a Java class which extends "HttpServlet" and answers a HTTP request within a web container. The latest official version is Servlets 3.0 which is also part of Java EE 6. For details see the Java Servlets 3.0 Spec.

** JavaServer Page**

JavaServer Pages (JSP) are files which contain HTML and Java code. The web cotainer compiles the JSP into a servlet at the first time the JSP is accessed. The current latest version is 2.1.

See Specification for JavaServer Pages 2.1

** JavaServer Pages Standard Tag Library**

The JavaServer Pages Standard Tag Library (JSTL) encapsulates the core functionality common to many Web applications as simple tags. The current version is 1.2 is part of the JavaServer Pages Specification version 2.1.


# Servlets - Core aspect of web development in Java – Servlets

Servlet is a class that handles requests, processes them and reply back with a response.

Lifecycle of a Servlet.
init()
service()
destroy()


# Tools for calling services
* Postman
* SOAPUI
* GraphQL

