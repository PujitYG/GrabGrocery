package com.microservice.authservice.controller.constraints;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.enums.EnumUtils;

import com.microservice.authservice.Entity.Enums.EmployeeRoles;

public class EmployeeRolesValidator implements ConstraintValidator<EmployeeRolesValidation, List<String>>{

	@Override
	public boolean isValid(List<String> value, ConstraintValidatorContext context) {
		System.out.println("INININ");
		
		List<String> roles = Stream.of(EmployeeRoles.values())
				.map(val->val.toString()).collect(Collectors.toList());
		
		return value
		.stream()
		.allMatch(val->roles.contains(val));
	}



}
