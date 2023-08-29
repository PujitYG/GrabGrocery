package com.microservice.EmployeeService.DTO;

import com.microservice.EmployeeService.Service.Enums.EmployeeStatus;

public class EmployeeRegistrationStatusEventDTO {
	
	private Integer employeeId;
	
	private String employeeEmail;
	
	private String password;
	
	private EmployeeStatus status;

	
	
	public EmployeeRegistrationStatusEventDTO() {
		super();
	}


	public EmployeeRegistrationStatusEventDTO(Integer employeeId, String employeeEmail, String password, EmployeeStatus status) {
		super();
		this.employeeId = employeeId;
		this.employeeEmail = employeeEmail;
		this.password = password;
		this.status = status;
	}


	public Integer getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}


	public String getEmployeeEmail() {
		return employeeEmail;
	}


	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}



	public EmployeeStatus getStatus() {
		return status;
	}


	public void setStatus(EmployeeStatus status) {
		this.status = status;
	}
	

}
