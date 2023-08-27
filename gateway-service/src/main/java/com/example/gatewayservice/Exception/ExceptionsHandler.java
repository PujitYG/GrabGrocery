package com.example.gatewayservice.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {
	
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> handleRuntime(Exception e){
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body("Nope Cool");
	}

}
