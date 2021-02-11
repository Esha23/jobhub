package com.project.jobhub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.jobhub.entities.Category;
import com.project.jobhub.services.ICategoryService;

@RestController
@RequestMapping("common")
public class CommonController {
	
	@Autowired
	ICategoryService iCategoryService;
	
	@GetMapping("/getCategoryList")
	@CrossOrigin(origins = "http://localhost:3000")
	@ResponseBody
	public List<Category> getCategoryList() {
		return iCategoryService.getCategoryList();
	}

}
