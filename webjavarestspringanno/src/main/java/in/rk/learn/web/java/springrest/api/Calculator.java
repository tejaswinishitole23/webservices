package in.rk.learn.web.java.springrest.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 http://localhost:8080/webjavarestspring/calc/do
 
 Json Request body 
 
 {		"op": "+",
		"numbers": [
			"444",
			"25"
		]
}

Command line options

java -jar `jarname` ( check build libs )

after app starts test it:
Curl with body:

curl --header "Content-Type: application/json"  --request POST --data ' {		"op": "+", "numbers": [ "444", "25"	] } ' http://localhost:8080/calc/do


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
