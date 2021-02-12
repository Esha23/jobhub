package com.project.jobhub.entities;

public class RecruiterData {
	
	private Integer job_id;

	private String recruiter_id;
	
	private String category_id;
	
	private String contact;
	
	private String gender;
	
	private Integer experience;
	
	private String education;
	
	private String location;
	
	private Integer salary;

	private String title;
	
	private String description;
	
	private String job_status;

	public RecruiterData() {
		
	}

	public RecruiterData(Integer job_id, String recruiter_id, String category_id, String contact, String gender,
			Integer experience, String education, String location, Integer salary, String title, String description,
			String job_status) {
		super();
		this.job_id = job_id;
		this.recruiter_id = recruiter_id;
		this.category_id = category_id;
		this.contact = contact;
		this.gender = gender;
		this.experience = experience;
		this.education = education;
		this.location = location;
		this.salary = salary;
		this.title = title;
		this.description = description;
		this.job_status = job_status;
	}

	public String getRecruiter_id() {
		return recruiter_id;
	}

	public void setRecruiter_id(String recruiter_id) {
		this.recruiter_id = recruiter_id;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJobStatus() {
		return job_status;
	}

	public void setJobStatus(String job_status) {
		this.job_status = job_status;
	}

	public Integer getJob_id() {
		return job_id;
	}

	public void setJob_id(Integer job_id) {
		this.job_id = job_id;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	
}
