package com.project.jobhub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.jobhub.entities.UserDetails;
import com.project.jobhub.services.IUserDetailsService;

@RestController
@RequestMapping("user")
public class UserDetailsController {
	
	@Autowired
	IUserDetailsService iUserDetailsService;
	
	@PostMapping("/addUser")
	@CrossOrigin(origins = "http://localhost:3000")
	public UserDetails addUser(@RequestBody UserDetails userDetails){
		return iUserDetailsService.addUser(userDetails);
	}
	
	@GetMapping("/getUserType/{user_id}")
	@CrossOrigin(origins = "http://localhost:3000")
	@ResponseBody
	public UserDetails getUserType(@PathVariable String user_id) {
		return iUserDetailsService.getUserType(user_id);
	}

}
