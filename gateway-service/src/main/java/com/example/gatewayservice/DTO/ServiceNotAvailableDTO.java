package com.example.gatewayservice.DTO;


public class ServiceNotAvailableDTO {
	private String message;
	private Integer statusCode;
	private String status;
	
	
	public ServiceNotAvailableDTO(String message, Integer statusCode, String status) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.status = status;
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
	
	
}
