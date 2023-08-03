package com.microservice.EmployeeService.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.microservice.EmployeeService.Entity.Enums.Roles;

@Entity
public class JobDescription {
	
	@Id
	@Column(name="JOB_ID")
	@SequenceGenerator(name = "jobSequenceGenerator", sequenceName = "JOB_SEQUENCE",initialValue = 1000,allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jobSequenceGenerator")
	private String jobId;
	
	@Column(name="ROLE")
	@Enumerated(EnumType.STRING)
	private Roles jobName;
	
	@Column(name="JOB_DESCRIPTION")
	private String jobDescription;
	

	public JobDescription() {
		super();
	}
	
	
	public JobDescription(String jobId, Roles jobName, String jobDescription) {
		super();
		this.jobId = jobId;
		this.jobName = jobName;
		this.jobDescription = jobDescription;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public Roles getJobName() {
		return jobName;
	}

	public void setJobName(Roles jobName) {
		this.jobName = jobName;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	@Override
	public String toString() {
		return "JobDescription [jobId=" + jobId + ", jobName=" + jobName + ", jobDescription=" + jobDescription + "]";
	}
	
	
	
}
