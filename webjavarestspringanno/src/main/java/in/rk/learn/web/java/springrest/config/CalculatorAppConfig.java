package in.rk.learn.web.java.springrest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc // without this url mapping will not get loaded.
@ComponentScan("in.rk.learn.web.java.springrest")
public class CalculatorAppConfig {

}
