package com.project.jobhub.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.jobhub.dao.IApplicantDao;
import com.project.jobhub.dao.IRecruiterDao;
import com.project.jobhub.entities.ApplicantData;
import com.project.jobhub.entities.Category;
import com.project.jobhub.entities.MailDetails;
import com.project.jobhub.entities.RecruiterData;
import com.project.jobhub.services.IApplicantService;
import com.project.jobhub.services.IMailService;

@Service
public class ApplicantService implements IApplicantService{
	
	@Autowired
	IApplicantDao iApplicantDao;
	
	@Autowired
	IMailService iMailService;
	
	@Autowired
	IRecruiterDao iRecruiterDao;

	@Override
	public void applyToCategory(String applicant_id, List<Category> appliedCategoryList) {
		iApplicantDao.applyToCategory(applicant_id, appliedCategoryList);
	}

	@Override
	public void addApplicantData(ApplicantData applicantData) {
		iApplicantDao.addApplicantData(applicantData);
	}

	@Override
	public List<ApplicantData> getApplicantData(String applicant_id) {
		return iApplicantDao.getApplicantData(applicant_id);
	}

	@Override
	public List<RecruiterData> getApplicantRecommendedJobs(String applicant_id) {
		return iApplicantDao.getApplicantRecommendedJobs(applicant_id);
	}

	@Override
	public void mailHr(String recruiter_id, String applicant_id) {
		List<String> emails = iRecruiterDao.getRecruiterEmail(recruiter_id);
		MailDetails mailDetails = iApplicantDao.getApplicantProfile(applicant_id);
		iMailService.mail(mailDetails, emails);
	}

	@Override
	public List<RecruiterData> filterJobBySalary(String applicant_id) {
		return iApplicantDao.filterJobBySalary(applicant_id);
	}

}
