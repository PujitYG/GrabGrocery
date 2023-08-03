package com.microservice.EmployeeService.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.EmployeeService.Entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
