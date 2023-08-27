package com.example.gatewayservice.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.gatewayservice.DTO.ServiceNotAvailableDTO;

@RestController
@RequestMapping
public class RouteFallBackController {
	
	
	@PostMapping("/fallback")
	@ResponseBody
	public ResponseEntity<ServiceNotAvailableDTO> fallback() {
		ServiceNotAvailableDTO dto = new ServiceNotAvailableDTO("Service Not available", 404, "NOT FOUND");
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(dto);
	}


}
