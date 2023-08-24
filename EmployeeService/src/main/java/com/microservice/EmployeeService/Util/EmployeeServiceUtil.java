package com.microservice.EmployeeService.Util;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.microservice.EmployeeService.DTO.EmployeeServiceResponseDTO;

@Component
public class EmployeeServiceUtil {
	
	public EmployeeServiceResponseDTO getEmployeeServiceResponseDTO(Integer statusCode,
			String status, Object data, String path) {
		EmployeeServiceResponseDTO response = new EmployeeServiceResponseDTO();
		response.setData(data);
		response.setPath(path);
		response.setStatus(status);
		response.setStatusCode(statusCode);
		response.setTimestamp(LocalDateTime.now());
		
		return response;
	}

}
