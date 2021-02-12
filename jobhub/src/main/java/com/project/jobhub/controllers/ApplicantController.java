package com.project.jobhub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.jobhub.entities.ApplicantData;
import com.project.jobhub.entities.Category;
import com.project.jobhub.entities.RecruiterData;
import com.project.jobhub.services.IApplicantService;

@RestController
@RequestMapping("applicant")
public class ApplicantController {
	
	@Autowired
	IApplicantService iApplicantService;
	
	@PostMapping("/applyToCategory/{applicant_id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public void applyToCategory(@PathVariable String applicant_id, @RequestBody List<Category> appliedCategoryList){
		iApplicantService.applyToCategory(applicant_id, appliedCategoryList);
	}
	
	@PostMapping("/addApplicantData")
	@CrossOrigin(origins = "http://localhost:3000")
	public void addApplicantData(@RequestBody ApplicantData applicantData){
		iApplicantService.addApplicantData(applicantData);
	}
	
	@GetMapping("/getApplicantData/{applicant_id}")
	@CrossOrigin(origins = "http://localhost:3000")
	@ResponseBody
	public List<ApplicantData> getApplicantData(@PathVariable String applicant_id) {
		return iApplicantService.getApplicantData(applicant_id);
	}
	
	@GetMapping("/getApplicantRecommendedJobs/{applicant_id}")
	@CrossOrigin(origins = "http://localhost:3000")
	@ResponseBody
	public List<RecruiterData> getApplicantRecommendedJobs(@PathVariable String applicant_id) {
		return iApplicantService.getApplicantRecommendedJobs(applicant_id);
	}
	
	@GetMapping("/mailHr/{recruiter_id}/{applicant_id}")
	@CrossOrigin(origins = "http://localhost:3000")
	@ResponseBody
	public void mailHr(@PathVariable String recruiter_id, @PathVariable String applicant_id) {
		iApplicantService.mailHr(recruiter_id, applicant_id);
	}

}
