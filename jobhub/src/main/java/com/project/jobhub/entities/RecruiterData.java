package com.project.jobhub.entities;

public class RecruiterData {

	private String recruiter_id;
	
	private String category_id;
	
	private String contact;
	
	private String gender;
	
	private String experience;
	
	private String education;
	
	private String location;
	
	private String job_status;

	public RecruiterData() {
		
	}

	public RecruiterData(String recruiter_id, String category_id, String contact, String gender, String experience,
			String education, String location, String job_status) {
		super();
		this.recruiter_id = recruiter_id;
		this.category_id = category_id;
		this.contact = contact;
		this.gender = gender;
		this.experience = experience;
		this.education = education;
		this.location = location;
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

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
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

	public String getJobStatus() {
		return job_status;
	}

	public void setJobStatus(String job_status) {
		this.job_status = job_status;
	}
	
}
