package com.rightkarma.svc.rest.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider // for jaxrs run-time to discover custom components
public class ExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException exception) {
		Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
		StringBuilder sb = new StringBuilder();
		constraintViolations.forEach(x->sb.append("Value:"+x.getInvalidValue()+" failed validation because "+x.getMessage()));
		Response responseWith = Response.status(400, sb.toString()).type(MediaType.APPLICATION_JSON).build();
		return responseWith;
	}

}
