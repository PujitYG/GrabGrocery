package com.microservice.notificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;

@SpringBootApplication
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}
	
	@KafkaListener(topicPartitions = @TopicPartition(topic = "topic-example-10",partitions = "0"))
	public void getData(String value) {
		System.out.print(value+" one ");
	}
	
	@KafkaListener(topicPartitions = @TopicPartition(topic = "topic-example-10",partitions = "1"))
	public void getData1(String value) {
		System.out.print(value+" two  ");
	}

}
