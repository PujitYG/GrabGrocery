package com.microservice.EmployeeService.Service;

import com.microservice.EmployeeService.Entity.Employee;

public interface EmployeeStatusPublisher {
	
	public void publishEmployeeStatusCreation(Employee event, String... others);

}
