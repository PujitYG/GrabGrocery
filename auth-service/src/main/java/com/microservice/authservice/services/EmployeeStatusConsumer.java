package com.microservice.authservice.services;

import com.microservice.authservice.DTO.EmployeeRegistrationStatusEventDTO;

public interface EmployeeStatusConsumer {
	
	public void consumerEmployeeStatusCreation(EmployeeRegistrationStatusEventDTO dto);

}
