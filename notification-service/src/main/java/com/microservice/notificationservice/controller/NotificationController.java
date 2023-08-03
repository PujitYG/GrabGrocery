package com.microservice.notificationservice.controller;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
	
	@RequestMapping("test")
	public String test() {
		
		return "test";
	}
	
	
}
