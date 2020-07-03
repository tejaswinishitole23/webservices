package in.rk.learn.springbootrest.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*default controller when nothing is passed in url after context */

@RestController
@RequestMapping("/")
public class Welcome {

	@GetMapping("/")
	public String hi() {
		return "Welcome to Lean API !!";
	}

}
