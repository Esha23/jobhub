package com.project.jobhub.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.jobhub.dao.IApplicantDao;
import com.project.jobhub.entities.ApplicantData;
import com.project.jobhub.entities.Category;
import com.project.jobhub.services.IApplicantService;

@Service
public class ApplicantService implements IApplicantService{
	
	@Autowired
	IApplicantDao iApplicantDao;

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

}
