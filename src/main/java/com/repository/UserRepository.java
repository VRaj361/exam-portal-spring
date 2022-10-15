package com.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, String>{
	public UserEntity findByUsername(String username); 
	
	public UserEntity findByUserid(String userid);

	public UserEntity findByPhoneNum(String username);
	
	
}
