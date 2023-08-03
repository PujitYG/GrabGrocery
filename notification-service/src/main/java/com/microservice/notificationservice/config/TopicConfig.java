package com.microservice.notificationservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {
	
	
	@Bean
	public NewTopic topicExample() {
		return TopicBuilder.name("topic-example-10")
				.partitions(2)
				.build();
	}
	

}
