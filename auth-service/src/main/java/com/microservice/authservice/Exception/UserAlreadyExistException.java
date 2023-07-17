package com.microservice.authservice.Exception;

import org.springframework.http.ResponseEntity;

import com.microservice.authservice.DTO.ExceptionDTO;

public class UserAlreadyExistException extends Exception implements ExceptionResponseEntity {
	
	private ExceptionDTO dto;
	
	public UserAlreadyExistException(ExceptionDTO dto) {
		super();
		this.dto=dto;
	}

	@Override
	public ResponseEntity<ExceptionDTO> getResponseEntity() {
		return ResponseEntity.
				status(dto.getStatusCode())
				.body(dto);
	}
	
	
}
