package com.example.shorty.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exception) {
		ApiError apiError = new ApiError("Please check arguments values", exception);
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY.value()).body(apiError);
	}

}
