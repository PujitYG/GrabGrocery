package com.microservice.authservice.Exception;

import org.springframework.http.ResponseEntity;

import com.microservice.authservice.DTO.ExceptionDTO;

public interface ExceptionResponseEntity {
	
	public ResponseEntity<ExceptionDTO> getResponseEntity();

}
