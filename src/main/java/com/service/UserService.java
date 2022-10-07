package com.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;


import com.entity.UserEntity;
import com.entity.UserRoleEntity;
import com.repository.RoleRepository;
import com.repository.UserRepository;

@Controller
public class UserService {
	@Autowired 
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired(required=true)
	private BCryptPasswordEncoder bcrypt;
	
	
	//create User
	public UserEntity createUser(UserEntity user, Set<UserRoleEntity> users) throws Exception {
		
		UserEntity userTemp = userRepo.findByUsername(user.getUsername());
		if(userTemp != null) {
			return null;
		}else {
			for(UserRoleEntity us : users) {
				roleRepo.save(us.getRole());
			}
			user.getUserroles().addAll(users);
			user.setPassword(bcrypt.encode(user.getPassword()));
			userTemp = this.userRepo.save(user); 
		}
		
		return userTemp; 
	}
	
	//delete user using id
	public boolean deleteUser(String id) {
		UserEntity userTemp = userRepo.findByUserid(id);
		System.out.println(userTemp);
		if(userTemp != null) {
			userRepo.deleteById(id);
			return true;
		}else {			
			return false;
		}
	}
	
	//login user
	public UserEntity checkingUser(UserEntity user) {
		UserEntity userTemp = userRepo.findByUsername(user.getUsername());
		if(userTemp != null) {
			if(bcrypt.matches(user.getPassword(), userTemp.getPassword())){
				return userTemp;
			}else {
				return null;
			}	
		}else {
			return null;
		}
	}
	
	//Forgot Password user
	public UserEntity checkingUserEmail(UserEntity user) {
		UserEntity userTemp = userRepo.findByUsername(user.getUsername());
		//otp assign and check otp after create new password
		return null;
	}
	
}
