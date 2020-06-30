package in.rk.learn.web.java.springrest.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 http://localhost:8080/webjavarestspringxml/calc/do
 
 Json Request body 
 
 {		"op": "+",
		"numbers": [
			"444",
			"25"
		]
}

curl --header "Content-Type: application/json"  --request POST --data ' {		"op": "+", "numbers": [ "444", "25"	] } ' http://localhost:8080/webjavarestspringxml/calc/do
curl --header "Content-Type: application/json"  --request POST --data ' {		"op": "+", "numbers": [ "444", "25"	] } ' http://localhost:9990/webjavarestspringxml/calc/do
 
 * */

@RestController
@RequestMapping("/calc")
public class Calculator {

	@GetMapping("/prime")
	public long calculate() {
		return 17;
	}

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
