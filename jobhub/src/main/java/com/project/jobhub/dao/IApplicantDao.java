package com.project.jobhub.dao;

import java.util.List;

import com.project.jobhub.entities.ApplicantData;
import com.project.jobhub.entities.Category;
import com.project.jobhub.entities.MailDetails;
import com.project.jobhub.entities.RecruiterData;

public interface IApplicantDao {
	
	public void applyToCategory(String applicant_id, List<Category> appliedCategoryList);
	
	public void addApplicantData(ApplicantData applicantData);
	
	public List<ApplicantData> getApplicantData(String applicant_id);
	
	public List<RecruiterData> getApplicantRecommendedJobs(String applicant_id);
	
	public MailDetails getApplicantProfile(String applicant_id);
	
	public List<RecruiterData> filterJobBySalary(String applicant_id);

}
