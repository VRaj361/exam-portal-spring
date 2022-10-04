package com.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "userrole")
public class UserRoleEntity {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid" , strategy = "uuid2")
	private String userroleid;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private UserEntity user;
	//one user contain many userroles
	
	@ManyToOne
	private RoleEntity role;

	
	
	public UserRoleEntity(UserEntity user, RoleEntity role) {
		super();
		this.user = user;
		this.role = role;
	}

	public UserRoleEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserroleid() {
		return userroleid;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}
	
	
	
}
