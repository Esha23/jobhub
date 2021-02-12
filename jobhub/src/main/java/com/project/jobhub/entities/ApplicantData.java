package com.project.jobhub.entities;

public class ApplicantData {
	
	private String applicant_id;
	
	private String contact;
	
	private String gender;
	
	private Integer experience;
	
	private String education;
	
	private String location;
	
	private Integer salary;

	public ApplicantData() {
		
	}

	public ApplicantData(String applicant_id, String contact, String gender, Integer experience, String education,
			String location, Integer salary) {
		super();
		this.applicant_id = applicant_id;
		this.contact = contact;
		this.gender = gender;
		this.experience = experience;
		this.education = education;
		this.location = location;
		this.salary = salary;
	}

	public String getApplicant_id() {
		return applicant_id;
	}

	public void setApplicant_id(String applicant_id) {
		this.applicant_id = applicant_id;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

}
