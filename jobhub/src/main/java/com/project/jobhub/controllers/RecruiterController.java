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
import com.project.jobhub.entities.RecruiterData;
import com.project.jobhub.services.IRecruiterService;

@RestController
@RequestMapping("recruiter")
public class RecruiterController {
	
	@Autowired
	IRecruiterService iRecruiterService;
	
	@PostMapping("/addRecruiterData")
	@CrossOrigin(origins = "http://localhost:3000")
	public void addRecruiterData(@RequestBody RecruiterData recruiterData){
		iRecruiterService.addRecruiterData(recruiterData);
	}
	
	@GetMapping("/getRecruiterData/{recruiter_id}")
	@CrossOrigin(origins = "http://localhost:3000")
	@ResponseBody
	public List<RecruiterData> getRecruiterData(@PathVariable String recruiter_id) {
		return iRecruiterService.getRecruiterData(recruiter_id);
	}

}
