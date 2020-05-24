package in.rk.learn.web.java.springbootrest.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
http://localhost:8080/calc/do 
note -  webjavarestspringboot is missing in url

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
	public long calculate(@RequestBody CalculateObject request) {
		switch (request.getOp()) {
		case "+":
			return add(request.getNumbers());
		case "*":
			return multiply(request.getNumbers());
		default:
			return 0;
		}
	}

	private long multiply(int[] numbers) {
		long result = 0;
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
