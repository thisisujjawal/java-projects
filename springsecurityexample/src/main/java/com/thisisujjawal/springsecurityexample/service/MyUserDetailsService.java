package com.thisisujjawal.springsecurityexample.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.thisisujjawal.springsecurityexample.model.MyUser;
import com.thisisujjawal.springsecurityexample.repo.MyUserRepo;
import com.thisisujjawal.springsecurityexample.security.MyUserDetails;
/**
 * This class fetch the MyUser from the DB and then pass the object
 * to the MyUserDetails to convert MyUser to UserDetails(Entity to Spring Security)
 * @author ujjawal
 *
 */
public class MyUserDetailsService implements UserDetailsService {
	

	@Autowired
	private MyUserRepo myUserRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<MyUser> myUser = myUserRepo.findById(username);
		return myUser.map(MyUserDetails :: new)
				.orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
	}

}
