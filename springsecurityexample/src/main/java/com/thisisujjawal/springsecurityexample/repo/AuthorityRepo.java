package com.thisisujjawal.springsecurityexample.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thisisujjawal.springsecurityexample.model.Authority;

public interface AuthorityRepo extends JpaRepository<Authority, String>{

}
