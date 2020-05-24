* Configuration using annotation. 
* CalculatorWebAppInitializer extneds AbstractAnnotationConfigDispatcherServletInitializer is equivalent to web.xml
* CalculatorAppConfig is like beans.xml for any beans to create at start up.
* Calculator is annotation based service class.
	* Uses spring annotations like @RestController, @RequestMapping, @PostMapping, @RequestBody
	
```java
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
```