package com.microservice.EmployeeService.Service;

import com.microservice.EmployeeService.DTO.EmployeeRegistrationStatusEventDTO;

public interface EmployeeStatusPublisher {
	
	public void publishEmployeeStatus(EmployeeRegistrationStatusEventDTO event);

}
