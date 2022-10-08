package com.controller;

import java.util.HashSet;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.AuthorityEntity;
import com.entity.CustomResponse;
import com.entity.RoleEntity;
import com.entity.UserEntity;
import com.entity.UserRoleEntity;
import com.service.UserService;


@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	private UserService userService;
	
	//creating user
	@PostMapping("/signup")
	public CustomResponse<UserEntity> createUser(@RequestBody UserEntity user) throws Exception{
		
		Set<UserRoleEntity> users = new HashSet<>();
		
		RoleEntity role = new RoleEntity();
		role.setRolename("Normal");
		role.setRoleid(2);
		
		UserRoleEntity userRole = new UserRoleEntity();
		userRole.setUser(user);
		userRole.setRole(role);
		users.add(userRole);
		
		UserEntity ansuser= userService.createUser(user, users);

		if(ansuser == null) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ansuser);
			return new CustomResponse<>(400,"Duplicate User Found",ansuser);
			
		}else {
//			return ResponseEntity.status(HttpStatus.OK).body(ansuser);
			return new CustomResponse<>(200,"User Registerd Successfully",ansuser);
		}
		
	}
	
	//delete user using id
	@DeleteMapping("/delete")
	public CustomResponse<UserEntity> deleteUser(@RequestHeader("userid") String userid){
		Boolean user = userService.deleteUser(userid);
		if(user == false) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id is incorrect");
			return new CustomResponse<>(404,"Id is incorrect",null);
		}else {
//			return ResponseEntity.status(HttpStatus.OK).body("User Delete Successfully");
			return new CustomResponse<>(200,"User Delete Successfully",null);
		}
	}
	
	//login user 
	@PostMapping("/login")
	public CustomResponse<UserEntity> loginUser(@RequestBody UserEntity user) throws Exception{
		System.out.println(user.getUsername());
		UserEntity ansuser= userService.checkingUser(user);
		if(ansuser == null) {
			return new CustomResponse<>(404,"Invalid Credentials",null);
		}else {
	
//			System.out.println("fdsa->"+ ansuser.getAuthorities().stream().filter(auth->auth.getAuthority().equals("Normal")).findFirst().orElse(null));
			if(!ansuser.isEnabled()) {
				return new CustomResponse<>(406,"Corrupt User! Please Contact to Admin.",ansuser);
			}
			
			AuthorityEntity authority = new AuthorityEntity();
			boolean b=false;
			ansuser.getAuthorities().forEach(auth->{
				if(auth.getAuthority().equals("Admin")) {
					authority.setStr("Admin");
				}else if(auth.getAuthority().equals("Normal")) {
					authority.setStr("User");
				}	
			});
			
			return new CustomResponse<>(200,authority.getStr()+" Login Successfully",ansuser);
		}
	}
	
	//forgot password
	@PostMapping("/forgotpassword")
	public CustomResponse<UserEntity> forgotPasswordUser(@RequestBody UserEntity user) throws Exception{
		UserEntity ansuser= userService.checkingUserEmail(user);
		if(ansuser == null) {
			return new CustomResponse<>(404,"Invalid Credentials",null);
		}else {
			return new CustomResponse<>(200,"Login Successfully",ansuser);
		}
	}
	
	//change account details
	@PutMapping("/updateAccountDetails")
	public CustomResponse<?> updateResponse(@RequestBody UserEntity user) throws Exception{
		String ansuser= userService.updateAccountDetails(user);
		if(ansuser == null) {
			return new CustomResponse<>(404,"Invalid Credentials",null);
		}else {
			return new CustomResponse<>(200,"Login Successfully",ansuser);
		}
	}
	
}
