package com.thisisujjawal.springsecurityexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thisisujjawal.springsecurityexample.model.AuthRequest;
import com.thisisujjawal.springsecurityexample.model.Authority;
import com.thisisujjawal.springsecurityexample.model.MyUser;
import com.thisisujjawal.springsecurityexample.service.AuthorityService;
import com.thisisujjawal.springsecurityexample.service.JWTService;
import com.thisisujjawal.springsecurityexample.service.MyUserDetailsService;
import com.thisisujjawal.springsecurityexample.service.MyUserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	MyUserService myUserService;
	
	@Autowired
	AuthorityService authorityService;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
    private JWTService jwtService;
	
	@PostMapping("register")
	public String register(@RequestBody MyUser myUser) {
		return myUserService.registerUser(myUser);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
	}
	
	@GetMapping("/getUser")
	public MyUser getUser(@RequestParam String username) {
		return myUserService.getUser(username);
	}
	
	@PostMapping("/authority")
	public Authority addAuthority(@RequestParam com.thisisujjawal.springsecurityexample.constants.Authority authority, String username) {
		return authorityService.addAuthority(authority, username);
	}

}
