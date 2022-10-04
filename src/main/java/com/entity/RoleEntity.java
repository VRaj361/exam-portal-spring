package com.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private int roleid;
	private String rolename;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "role")
	private Set<UserRoleEntity> userroles = new HashSet<>();
	
	
	public RoleEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RoleEntity(int roleid, String rolename) {
		super();
		this.roleid = roleid;
		this.rolename = rolename;
	}
	
	public Set<UserRoleEntity> getUserroles() {
		return userroles; 
	}
	public void setUserroles(Set<UserRoleEntity> userroles) {
		this.userroles = userroles;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int id) {
		this.roleid=id;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	
	
}
