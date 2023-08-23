package com.microservice.authservice.controller.constraints;

import java.util.List;
import java.util.Optional;
import java.util.Set;
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
		
		Set<String> roles = Stream.of(EmployeeRoles.values())
				.map(EmployeeRoles::toString).collect(Collectors.toSet());
		
		return Optional.ofNullable(value)
				.map(val -> val.size() > 0 && val
						.stream()
						.allMatch(roles::contains))
				.orElse(Boolean.FALSE);
		
	}

 

}


