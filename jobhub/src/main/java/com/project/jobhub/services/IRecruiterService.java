package com.project.jobhub.services;

import java.util.List;

import com.project.jobhub.entities.RecruiterData;

public interface IRecruiterService {
	
	public void addRecruiterData(RecruiterData recruiterData);
	
	public List<RecruiterData> getRecruiterData(String recruiter_id);
	
	public void closeJob(Integer job_id);

}
