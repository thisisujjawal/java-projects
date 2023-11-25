package com.thisisujjawal.springsecurityexample.model;

import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class MyUser {

	@Id
	private String username;
	private String name;
	private String password;
	
	@OneToMany(mappedBy = "myUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Authority> authorities;
	
	public MyUser() {
		
	}
	public MyUser(String username, String name, String password, List<Authority> authorities) {
		super();
		this.username = username;
		this.name = name;
		this.password = password;
		this.authorities = authorities;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Authority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}
	@Override
	public String toString() {
		return "MyUser [username=" + username + ", name=" + name + ", password=" + password + ", authorities="
				+ authorities + "]";
	}
	
}
