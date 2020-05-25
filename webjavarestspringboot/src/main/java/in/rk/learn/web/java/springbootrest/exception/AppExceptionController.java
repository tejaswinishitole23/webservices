package in.rk.learn.web.java.springbootrest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionController {

	@ExceptionHandler(value = CustomAppException.class)
	public ResponseEntity<Object> exception(CustomAppException exception){
		System.out.println("Exception happened.");
		String errmsg="Get used instead of post";
		return new ResponseEntity<>(errmsg, HttpStatus.BAD_REQUEST); // errmsg will go to client along err code 400 Bad Request. 
	}

}
