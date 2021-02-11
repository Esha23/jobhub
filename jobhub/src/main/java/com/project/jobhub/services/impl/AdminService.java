package com.project.jobhub.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.jobhub.dao.IAdminDao;
import com.project.jobhub.services.IAdminService;

@Service
public class AdminService implements IAdminService{
	
	@Autowired
	IAdminDao iAdminDao;

	@Override
	public void addCategory(String category_name) {
		iAdminDao.addCategory(category_name);
	}

}
