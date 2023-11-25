package com.thisisujjawal.springsecurityexample.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thisisujjawal.springsecurityexample.model.MyUser;
/**
 * In this we are using hard code values. You can connect that to any DB
 * @author ujjawal
 *
 */
public interface MyUserRepo extends JpaRepository<MyUser, String>{

	
//	public Optional<MyUser> findByUsername(String username);
	
	
}
