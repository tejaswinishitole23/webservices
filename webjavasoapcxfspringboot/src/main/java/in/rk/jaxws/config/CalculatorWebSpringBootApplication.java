package in.rk.jaxws.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("in.rk.jaxws")
public class CalculatorWebSpringBootApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(CalculatorWebSpringBootApplication.class, args);
    }
}
