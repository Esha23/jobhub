package com.project.jobhub.dao;

import java.util.List;

import com.project.jobhub.entities.RecruiterData;

public interface IRecruiterDao {
	
	public void addRecruiterData(RecruiterData recruiterData);
	
	public List<RecruiterData> getRecruiterData(String recruiter_id);

}
