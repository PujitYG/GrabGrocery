package com.microservice.EmployeeService.Entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.microservice.EmployeeService.Entity.Enums.Shift;

@Entity
public class Employee {
	
	@Id
	@Column(name="EMPLOYEE_ID")
	private String employeeId;
	
	@Column(name="EMPLOYEE_NAME", nullable = false)
	private String employeeName;
	
	@Column(name="DATE_OF_BIRTH", updatable = false, nullable = false)
	private LocalDateTime dob;
	
	@Column(name="PHONE_NUMBER", nullable = false)
	private String phoneNumber;
	
	@Column(name="EMAIL_ID", nullable = false)
	private String emailId;
	
	@Column(name="SALARY")
	private Integer salary;
	
	@Enumerated(EnumType.STRING)
	private Shift shift;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(joinColumns = {@JoinColumn(name = "EMPLOYEE_ID")},
	inverseJoinColumns = {@JoinColumn(name = "JOB_ID")})
	private List<JobDescription> jobDescriptition;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="employeeId")
	private List<CustomerAided> customerAided;

	public Employee() {
		super();
	}

	public Employee(String employeeId, String employeeName, LocalDateTime dob, String phoneNumber, String emailId,
			Integer salary, Shift shift, List<JobDescription> jobDescriptition, List<CustomerAided> customerAided) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.dob = dob;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		this.salary = salary;
		this.shift = shift;
		this.jobDescriptition = jobDescriptition;
		this.customerAided = customerAided;
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

	public LocalDateTime getDob() {
		return dob;
	}

	public void setDob(LocalDateTime dob) {
		this.dob = dob;
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

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Shift getShift() {
		return shift;
	}

	public void setShift(Shift shift) {
		this.shift = shift;
	}

	public List<JobDescription> getJobDescriptition() {
		return jobDescriptition;
	}

	public void setJobDescriptition(List<JobDescription> jobDescriptition) {
		this.jobDescriptition = jobDescriptition;
	}

	public List<CustomerAided> getCustomerAided() {
		return customerAided;
	}

	public void setCustomerAided(List<CustomerAided> customerAided) {
		this.customerAided = customerAided;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", dob=" + dob
				+ ", phoneNumber=" + phoneNumber + ", emailId=" + emailId + ", salary=" + salary + ", shift=" + shift
				+ ", jobDescriptition=" + jobDescriptition + ", customerAided=" + customerAided + "]";
	}
	
	
	

}
