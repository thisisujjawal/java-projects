package com.thisisujjawal.springsecurityexample.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.thisisujjawal.springsecurityexample.model.MyUser;
import com.thisisujjawal.springsecurityexample.repo.MyUserRepo;

@Service
public class MyUserService {

	@Autowired
	MyUserRepo myUserRepo;
	
	@Autowired
	PasswordEncoder encoder;
	
	public String registerUser(MyUser myUser) {
		myUser.setPassword(encoder.encode(myUser.getPassword()));
		myUserRepo.save(myUser);
		return myUser.getUsername() + " added to the DB successfully";
	}
	
	public MyUser getUser(String username) {
		Optional<MyUser> myUser = myUserRepo.findById(username);
		return myUser.get();
	}
	
}
