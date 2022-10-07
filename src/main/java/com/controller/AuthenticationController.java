package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.config.JwtUtils;
import com.entity.JwtRequest;
import com.entity.JwtResponse;
import com.entity.UserEntity;



@RestController
@CrossOrigin("*")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	
	//generate Token for login
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
	
		try {
			authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
			
		}catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User not found AuthenticationController");
		}
		//authenticated user
		UserDetails user = this.userService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtils.generateToken(user);
		return ResponseEntity.ok(new JwtResponse(token));
		
	}
	
	//generate Token for update and forgetpassword
	@PostMapping("/generate-tokens")
	public ResponseEntity<?> generateTokens(@RequestBody UserEntity user) throws Exception{
		
		//authenticated user
		UserDetails user1 = this.userService.loadUserByUsername(user.getUsername());
		if(user!=null) {
			String token = this.jwtUtils.generateToken(user1);
			return ResponseEntity.ok(new JwtResponse(token));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	}
	
	
	private void authenticate(String username, String password) throws Exception{
		System.out.println("Password AuthenticationController "+password +"username "+username);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}catch(DisabledException e) {
			throw new Exception("User Disable AuthenticationController "+e.getMessage());
			
		}catch(BadCredentialsException e) {
			throw new Exception("Invalid Credentials AuthenticationController "+e.getMessage());
		}
	}
}
