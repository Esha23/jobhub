package com.project.jobhub.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.jobhub.dao.ICategoryDao;
import com.project.jobhub.entities.Category;
import com.project.jobhub.services.ICategoryService;

@Service
public class CategoryService implements ICategoryService{
	
	@Autowired
	ICategoryDao iCategoryDao;

	@Override
	public List<Category> getCategoryList() {
		return iCategoryDao.getCategoryList();
	}

}
