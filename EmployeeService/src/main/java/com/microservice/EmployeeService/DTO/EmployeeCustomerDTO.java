package com.microservice.EmployeeService.DTO;

import java.util.List;

import com.microservice.EmployeeService.Entity.CustomerAided;

public class EmployeeCustomerDTO {
	

	private String id;
	private String employeeName;
	private List<CustomerAided> customerAided;
	
	
	
	public EmployeeCustomerDTO() {
		super();
	}

	public EmployeeCustomerDTO(String id, String employeeName, List<CustomerAided> customerAided) {
		super();
		this.id = id;
		this.employeeName = employeeName;
		this.customerAided = customerAided;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public List<CustomerAided> getCustomerAided() {
		return customerAided;
	}

	public void setCustomerAided(List<CustomerAided> customerAided) {
		this.customerAided = customerAided;
	}
	
	
	
	
	
}
