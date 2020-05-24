This project was downloaded from spring initializer website. https://start.spring.io/
Only code change was to add a file in static folder so when we hit the server after starting main method, there is somethinng to see.

In initializer, only web dependency was mentioned so that tomocat start up ability is present.


OUTPUT:

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.2.7.RELEASE)

2020-05-14 00:21:01.939  INFO 18096 --- [           main] i.r.l.s.cxfsoap.CxfsoapApplication       : Starting CxfsoapApplication on Himanshu-pc with PID 18096 (C:\git\webservices\cxfsoap\bin\main started by himan in C:\git\webservices\cxfsoap)
2020-05-14 00:21:01.947  INFO 18096 --- [           main] i.r.l.s.cxfsoap.CxfsoapApplication       : No active profile set, falling back to default profiles: default
2020-05-14 00:21:04.068  INFO 18096 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2020-05-14 00:21:04.086  INFO 18096 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2020-05-14 00:21:04.086  INFO 18096 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.34]
2020-05-14 00:21:04.248  INFO 18096 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2020-05-14 00:21:04.248  INFO 18096 --- [           main] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 2201 ms
2020-05-14 00:21:04.579  INFO 18096 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2020-05-14 00:21:04.697  INFO 18096 --- [           main] o.s.b.a.w.s.WelcomePageHandlerMapping    : Adding welcome page: class path resource [static/index.html]
2020-05-14 00:21:04.869  INFO 18096 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2020-05-14 00:21:04.873  INFO 18096 --- [           main] i.r.l.s.cxfsoap.CxfsoapApplication       : Started CxfsoapApplication in 3.673 seconds (JVM running for 4.487)
2020-05-14 00:21:23.404  INFO 18096 --- [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2020-05-14 00:21:23.404  INFO 18096 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2020-05-14 00:21:23.413  INFO 18096 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 8 ms
```