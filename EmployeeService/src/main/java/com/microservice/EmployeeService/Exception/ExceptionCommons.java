package com.microservice.EmployeeService.Exception;

import org.springframework.http.ResponseEntity;

import com.microservice.EmployeeService.DTO.ExceptionDTO;

public interface ExceptionCommons {
	
	public ResponseEntity<ExceptionDTO> getResponseEntity(String path);

}
