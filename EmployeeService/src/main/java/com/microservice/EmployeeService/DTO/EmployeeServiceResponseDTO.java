package com.microservice.EmployeeService.DTO;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeServiceResponseDTO {
	
	
	private Integer statusCode;
	private String status;
	private LocalDateTime timestamp;
	private String path;
	private Object data;
	
	
	public EmployeeServiceResponseDTO() {
	}


	public EmployeeServiceResponseDTO(Integer statusCode, String status, LocalDateTime timestamp, String path, Object data) {
		super();
		this.statusCode = statusCode;
		this.status = status;
		this.timestamp = timestamp;
		this.path = path;
		this.data = data;
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


	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}
	
	

}
