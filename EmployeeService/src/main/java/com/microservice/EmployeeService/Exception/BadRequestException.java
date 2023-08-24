package com.microservice.EmployeeService.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.microservice.EmployeeService.DTO.ExceptionDTO;

public class BadRequestException extends Exception implements ExceptionCommons {
	

	private static final long serialVersionUID = 1L;

	public BadRequestException(String message) {
		super(message);
	}

	@Override
	public ResponseEntity<ExceptionDTO> getResponseEntity(String path) {
		ExceptionDTO exception = 
				new ExceptionDTO(this.getMessage(), 
						HttpStatus.BAD_REQUEST.value(), 
						HttpStatus.BAD_REQUEST.getReasonPhrase(),
						LocalDateTime.now(),path);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(exception);
	}

}
