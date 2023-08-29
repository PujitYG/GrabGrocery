package com.microservice.EmployeeService.Configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicsConfig {

	public NewTopic employeeRegistrationTopic() {
		return TopicBuilder.name("EMPLOYEE-REGISTRATION-CREATION")
				.build();
	}
	
}
