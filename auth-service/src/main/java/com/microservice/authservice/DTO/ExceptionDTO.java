package com.microservice.authservice.DTO;

import java.time.LocalDateTime;

public class ExceptionDTO {
	
	private String error;
	private Integer statusCode;
	private LocalDateTime timeStamp;
	private String message;
	private String path;
	
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "ExceptionDTO [error=" + error + ", statusCode=" + statusCode + ", timeStamp=" + timeStamp + ", message="
				+ message + ", path=" + path + "]";
	}
	
	
	
	

}
