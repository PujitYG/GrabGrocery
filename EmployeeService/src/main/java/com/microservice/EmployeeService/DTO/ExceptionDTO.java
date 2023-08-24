package com.microservice.EmployeeService.DTO;

import java.time.LocalDateTime;

public class ExceptionDTO {
	
	private String message;
	private Integer statusCode;
	private String status;
	private LocalDateTime timestamp;
	private String path;
	
	
	public ExceptionDTO() {
		super();
	}


	public ExceptionDTO(String message, Integer statusCode, String status, LocalDateTime timestamp, String path) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.status = status;
		this.timestamp = timestamp;
		this.path = path;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Integer getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public LocalDateTime getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	
}
