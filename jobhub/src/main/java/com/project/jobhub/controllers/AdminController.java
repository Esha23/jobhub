package com.project.jobhub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.jobhub.services.IAdminService;

@RestController
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	IAdminService iAdminService;
	
	@PostMapping("/addCategory/{category_name}")
	@CrossOrigin(origins = "http://localhost:3000")
	public void addCategory(@PathVariable String category_name){
		iAdminService.addCategory(category_name);
	}

}
