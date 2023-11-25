package com.thisisujjawal.springsecurityexample.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;

@Entity
public class Authority {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String authorityId;
	private com.thisisujjawal.springsecurityexample.constants.Authority authority;
	@ManyToOne
	@JoinColumn(name = "user")
	@JsonIgnoreProperties("authorities")
	private MyUser myUser;

	public Authority() {}
	public Authority(String authorityId, com.thisisujjawal.springsecurityexample.constants.Authority authority,
			MyUser myUser) {
		super();
		this.authorityId = authorityId;
		this.authority = authority;
		this.myUser = myUser;
	}

	public String getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}

	public com.thisisujjawal.springsecurityexample.constants.Authority getAuthority() {
		return authority;
	}

	public void setAuthority(com.thisisujjawal.springsecurityexample.constants.Authority authority) {
		this.authority = authority;
	}

	public MyUser getMyUser() {
		return myUser;
	}

	public void setMyUser(MyUser myUser) {
		this.myUser = myUser;
	}

	@Override
	public String toString() {
		return "Authority [authorityId=" + authorityId + ", authority=" + authority + ", myUser=" + myUser + "]";
	}

	
}
