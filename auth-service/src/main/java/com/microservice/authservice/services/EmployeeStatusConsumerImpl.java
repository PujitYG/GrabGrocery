package com.microservice.authservice.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.microservice.authservice.DTO.EmployeeRegistrationStatusEventDTO;


@Service
public class EmployeeStatusConsumerImpl implements EmployeeStatusConsumer {

	@KafkaListener(topics = "EMPLOYEE-CREATION-TERMINATION", groupId = "Employee-creation-termination")
	public void consumerEmployeeStatusCreation(EmployeeRegistrationStatusEventDTO dto) {
		
		System.out.println("inininni");
		
		System.out.println(dto.getPassword());
		
	}

}
