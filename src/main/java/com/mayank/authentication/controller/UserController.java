package com.mayank.authentication.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mayank.authentication.model.User;
import com.mayank.authentication.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostConstruct 
	public void initRolesAndUsers() {
		userService.initRolesAndUser();
	}
	@PostMapping("/registerNewUser")
	public User registerNewUser(@RequestBody User user)
	{
		return  userService.registerNewUser(user);
	}
	
	@GetMapping({"/forAdmin"})
	public String forAdmin() {
		return "This URl is only accessible to admin";
	}
	
	@GetMapping({"/forUser"})
	public String forUser() {
		return "This Url is only accessible to users";
		
	}

}
