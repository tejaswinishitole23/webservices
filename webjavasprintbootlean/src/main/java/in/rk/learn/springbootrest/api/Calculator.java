package in.rk.learn.springbootrest.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
http://localhost:8080/calc/add?var1=12&&var2=13 
note -  webjavarestspringboot should not be given in url
*/

@RestController
@RequestMapping("/calc")
public class Calculator {

	@GetMapping("/add")
	public long calculate(@RequestParam("var1") Integer i1, @RequestParam("var2") Integer i2) {
		long sum=i1+i2;
		return sum;
	}
	
}
