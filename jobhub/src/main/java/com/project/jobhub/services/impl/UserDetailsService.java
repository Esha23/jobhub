package com.project.jobhub.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.jobhub.dao.IUserDetailsDao;
import com.project.jobhub.entities.UserDetails;
import com.project.jobhub.services.IMailService;
import com.project.jobhub.services.IUserDetailsService;

@Service
public class UserDetailsService implements IUserDetailsService{
	
	@Autowired
	IMailService iMailService;
	
	@Autowired
	IUserDetailsDao iUserDetailsDao;

	@Override
	public UserDetails addUser(UserDetails userDetails) {
		List<String> emails = new ArrayList<>();
		emails.add(userDetails.getEmail());
		userDetails = iMailService.setSignUpMailDetails(userDetails);
		iMailService.mail(userDetails.getMailDetails() , emails);
		return iUserDetailsDao.addUser(userDetails);
	}

	@Override
	public UserDetails getUserType(String user_id) {
		return iUserDetailsDao.getUserType(user_id);
	}

}
