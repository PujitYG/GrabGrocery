package com.microservice.EmployeeService.DTO;

import java.util.List;

import com.microservice.EmployeeService.Entity.JobDescription;

public class EmployeeBasicDetailsDTO {
	
	private String employeeId;
	private String employeeName;
	private Integer age;
	private String phoneNumber;
	private String emailId;
	
	private List<JobDescription> jobDescriptition;
	
	public EmployeeBasicDetailsDTO() {
		super();
	}

	public EmployeeBasicDetailsDTO(String employeeId, String employeeName, Integer age, String phoneNumber, String emailId,
			List<JobDescription> jobDescriptition) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		this.jobDescriptition = jobDescriptition;
	}
	

	public List<JobDescription> getJobDescriptition() {
		return jobDescriptition;
	}

	public void setJobDescriptition(List<JobDescription> jobDescriptition) {
		this.jobDescriptition = jobDescriptition;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
		
	

}
