package com.project.jobhub.services;

import java.util.List;

import com.project.jobhub.entities.MailDetails;
import com.project.jobhub.entities.UserDetails;

public interface IMailService {
	
	public String mail(MailDetails mailDetails, List<String> email);
	
	public UserDetails setSignUpMailDetails(UserDetails userDetails);

}
