package com.microservice.EmployeeService.Client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="SuperMarket-Service")
public interface SuperMarketServiceClient {

	
	@GetMapping(path = "api/supermarket/details/test")
	public ResponseEntity<String> isUp();
	
}
