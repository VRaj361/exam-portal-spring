package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.entity.UserEntity;
import com.repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity user = userRepo.findByUsername(email);
		if(user == null) {
			System.out.println("Not found UserDetailService ");
			throw new UsernameNotFoundException("User not found");
		}
		return user;
	}

}
