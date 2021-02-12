package com.project.jobhub.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.jobhub.dao.IRecruiterDao;
import com.project.jobhub.entities.RecruiterData;
import com.project.jobhub.services.IRecruiterService;

@Service
public class RecruiterService implements IRecruiterService{
	
	@Autowired
	IRecruiterDao iRecruiterDao;

	@Override
	public void addRecruiterData(RecruiterData recruiterData) {
		iRecruiterDao.addRecruiterData(recruiterData);
	}

	@Override
	public List<RecruiterData> getRecruiterData(String recruiter_id) {
		return iRecruiterDao.getRecruiterData(recruiter_id);
	}

	@Override
	public void closeJob(Integer job_id) {
		iRecruiterDao.closeJob(job_id);
	}

}
