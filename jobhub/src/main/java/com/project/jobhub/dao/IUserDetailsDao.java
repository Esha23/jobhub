package com.project.jobhub.dao;

import com.project.jobhub.entities.UserDetails;

public interface IUserDetailsDao {
	
	public UserDetails addUser(UserDetails userDetails);

	public UserDetails getUserType(String user_id);
	
}
