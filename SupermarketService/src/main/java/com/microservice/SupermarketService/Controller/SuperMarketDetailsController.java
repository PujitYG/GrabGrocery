package com.microservice.SupermarketService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/supermarket/details")
public class SuperMarketDetailsController {
	
	@Autowired
	KafkaTemplate<String, String> template;
		
	@GetMapping("test")
	public String test() {
		template.send("topic-example-10", "one1", "test6");
		return "SuperMarketDetails-1";
	}
	
	@GetMapping("test2")
	public String test2() {
		template.send("topic-example-10", "two2", "test2");
		return "SuperMarketDetails-2";
	}
	
}
