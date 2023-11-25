package com.thisisujjawal.springsecurityexample.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.el.stream.Stream;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.thisisujjawal.springsecurityexample.model.MyUser;

/**
 * This is like a mapping class
 * DB -> MyUser
 * Spring Security -> UserDetails
 * 
 * This class takes the MyUser and convert it into UserDetails
 * @author ujjawal
 *
 */
public class MyUserDetails implements UserDetails{
	
	private String username;
	private String password;
	private List<GrantedAuthority> authorities;
	
	public MyUserDetails(MyUser myUser) {
		username = myUser.getUsername();
		password = myUser.getPassword();
		authorities = myUser.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority.getAuthority().toString()))
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
