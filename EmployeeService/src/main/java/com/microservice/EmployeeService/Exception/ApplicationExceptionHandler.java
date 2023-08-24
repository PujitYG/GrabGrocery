package com.microservice.EmployeeService.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.microservice.EmployeeService.DTO.ExceptionDTO;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	
	
	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<ExceptionDTO> badRequestExceptionHandler(BadRequestException e, ServerHttpRequest request){
		return e.getResponseEntity(request.getURI().getPath());
	}
	
}
