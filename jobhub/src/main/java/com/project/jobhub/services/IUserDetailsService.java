package com.project.jobhub.services;

import com.project.jobhub.entities.UserDetails;

public interface IUserDetailsService {
	
	public UserDetails addUser(UserDetails userDetails);
	
	public UserDetails getUserType(String user_id);

}
