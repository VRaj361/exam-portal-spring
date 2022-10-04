package com.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, String>{
	public UserEntity findByEmail(String email); 
	
	public UserEntity findByUserid(String userid);
	
	
}
