package com.microservice.EmployeeService.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CustomerAided {
	
	@Id
	@Column(name = "CUSTOMER_ID")
	private String customerId;
	
	@JoinColumn(name = "Employee_ID")
	@ManyToOne(fetch = FetchType.EAGER)
	private Employee employeeId;

	public CustomerAided() {
		super();
	}

	public CustomerAided(String customerId, Employee employeeId) {
		super();
		this.customerId = customerId;
		this.employeeId = employeeId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Employee getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Employee employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public String toString() {
		return "CustomerAided [customerId=" + customerId + ", employeeId=" + employeeId.getEmployeeId() + "]";
	}
	
		

}
