package in.rk.learn.web.java.springbootrest.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.rk.learn.web.java.springbootrest.exception.CustomAppException;

/*
http://localhost:8080/calc/do?key=abcd 
note -  webjavarestspringboot is missing in url

when started as war - url - http://localhost:8080/webjavarestspringboot/calc/do?key=abcd 

Json Request body 

{		"op": "+",
		"numbers": [
			"444",
			"25"
		]
}

* */

@RestController
@RequestMapping("/calc")
public class Calculator {

	@PostMapping("/do")
	public long calculate(@RequestBody CalculateObject request, @RequestParam("key") String key) {
		System.out.println("key:"+key);
		switch (request.getOp()) {
		case "+":
			return add(request.getNumbers());
		case "*":
			return multiply(request.getNumbers());
		default:
			return 0;
		}
	}
	
	@GetMapping("/do")
	public void calculate() {
		System.out.println("calculation do called using Get");
		throw new CustomAppException(); // learn - when you though CustomAppException, AppExceptionController ExceptionHandler will catch it. 
	}

	private long multiply(int[] numbers) {
		long result = 1;
		for (int i : numbers) {
			result = i * result;
		}
		return result;
	}

	private long add(int[] numbers) {
		long result = 0;
		for (int i : numbers) {
			result = i + result;
		}
		return result;
	}

}
