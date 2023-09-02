package com.microservice.EmployeeService.Configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicsConfig {
	
	@Value("${employee.creaton.termination}")
	String EMPLOYEE_CREATION_TERMINATION;

	@Bean
	public NewTopic employeeRegistrationTopic() {
		return TopicBuilder.name("EMPLOYEE-CREATION-TERMINATION")
				.build();
	}
	
}
