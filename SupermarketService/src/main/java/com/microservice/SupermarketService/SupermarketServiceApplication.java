package com.microservice.SupermarketService;

import java.util.function.Supplier;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class SupermarketServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupermarketServiceApplication.class, args);
	}

	
//    @Bean
    public Supplier<String> generateEvents() {
        return () -> "Hi I'm an event";
    }
	
    
	@Bean
	public NewTopic topicExample() {
		return TopicBuilder.name("topic-example-101")
				.partitions(2)
				.build();
	}
	
    
}
