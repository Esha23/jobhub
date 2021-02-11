package com.project.jobhub.entities;

public class ApplicantToCategory {
	
	private String applicant_id;
	
	private Integer category_id;

	public ApplicantToCategory() {
		
	}

	public ApplicantToCategory(String applicant_id, Integer category_id) {
		super();
		this.applicant_id = applicant_id;
		this.category_id = category_id;
	}

	public String getApplicant_id() {
		return applicant_id;
	}

	public void setApplicant_id(String applicant_id) {
		this.applicant_id = applicant_id;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

}
