package com.microservice.authservice.controller.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Constraint(validatedBy = EmployeeRolesValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmployeeRolesValidation {
	
    String message() default "Invalid Role";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
