package com.microservice.EmployeeService.Service;

import org.springframework.stereotype.Service;

import com.microservice.EmployeeService.DTO.EmployeeRegistrationStatusEventDTO;

@Service
public class EmployeeStatusPublisherImpl implements EmployeeStatusPublisher {
	
	public void publishEmployeeStatus(EmployeeRegistrationStatusEventDTO event) {
		return;
	}

}
