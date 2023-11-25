package com.thisisujjawal.springsecurityexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thisisujjawal.springsecurityexample.model.Authority;
import com.thisisujjawal.springsecurityexample.model.MyUser;
import com.thisisujjawal.springsecurityexample.service.AuthorityService;
import com.thisisujjawal.springsecurityexample.service.MyUserDetailsService;
import com.thisisujjawal.springsecurityexample.service.MyUserService;

@RestController
public class UserController {
	
	@Autowired
	MyUserService myUserService;
	
	@Autowired
	AuthorityService authorityService;
	
	@PostMapping("/user/register")
	public String register(@RequestBody MyUser myUser) {
		return myUserService.registerUser(myUser);
	}
	
	@GetMapping("/user/getUser")
	public MyUser getUser(@RequestParam String username) {
		return myUserService.getUser(username);
	}
	
	@PostMapping("/user/authority")
	public Authority addAuthority(@RequestParam com.thisisujjawal.springsecurityexample.constants.Authority authority, String username) {
		return authorityService.addAuthority(authority, username);
	}

}
