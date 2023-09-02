package com.microservice.EmployeeService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.EmployeeService.DTO.EmployeeRegistrationStatusEventDTO;
import com.microservice.EmployeeService.Entity.Employee;
import com.microservice.EmployeeService.Service.Enums.EmployeeStatus;

@Service
public class EmployeeStatusPublisherImpl implements EmployeeStatusPublisher {
	
	@Autowired
	KafkaTemplate<String, EmployeeRegistrationStatusEventDTO> kafkaTemplate;
	
	@Transactional
	public void publishEmployeeStatusCreation(Employee emp, String... others) {
		
		EmployeeRegistrationStatusEventDTO employeeEventDTO= new EmployeeRegistrationStatusEventDTO();
		employeeEventDTO.setEmployeeId(emp.getEmployeeId());
		employeeEventDTO.setPassword(others[0]);
		employeeEventDTO.setStatus(EmployeeStatus.CREATED);
		employeeEventDTO.setEmployeeEmail(emp.getEmailId());
		
		kafkaTemplate.send("EMPLOYEE-CREATION-TERMINATION", employeeEventDTO);
	}

}
