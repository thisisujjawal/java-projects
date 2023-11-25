package com.thisisujjawal.springsecurityexample.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thisisujjawal.springsecurityexample.model.Authority;
import com.thisisujjawal.springsecurityexample.model.MyUser;
import com.thisisujjawal.springsecurityexample.repo.AuthorityRepo;
import com.thisisujjawal.springsecurityexample.repo.MyUserRepo;

@Service
public class AuthorityService {
	
	@Autowired
	AuthorityRepo authorityRepo;
	
	@Autowired
	MyUserRepo myUserRepo;

	public Authority addAuthority(com.thisisujjawal.springsecurityexample.constants.Authority authority, String username) {
		Optional<MyUser> myUser = myUserRepo.findById(username);
		Authority obj = new Authority();
		obj.setAuthority(authority);
		obj.setMyUser(myUser.get());
		return authorityRepo.save(obj);
	}
}
