package com.microservice.SupermarketService.Controller;

import java.util.Optional;
import java.util.function.Supplier;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/supermarket/details")
public class SuperMarketDetailsController {
	
	@Autowired
	KafkaTemplate<String, String> template;
	
	@Autowired
	StreamBridge bridge;
		
	@GetMapping("test")
	public String test(HttpServletRequest request) {
//		template.send("topic-example-10", "one1", "test6");
		System.out.println(request.getHeader("Header-Example"));
		
		return "SuperMarketDetails-1";
	}
	
	@GetMapping("test2")
	public String test2() {
		template.send("topic-example-10", "two2", "test2");
		return "SuperMarketDetails-2";
	}
	
	
	@GetMapping("test3")
	public String output(){
		bridge.send("generateEvents-out-0", "HELLO LETS SEEE");
		Optional<String> op = null;

		return "value";
	}
	

	
}
