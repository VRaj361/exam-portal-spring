package com.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		role.setRolename("Admin");
		role.setRoleid(1);
		
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
		UserEntity ansuser= userService.checkingUser(user);
		if(ansuser == null) {
			return new CustomResponse<>(404,"Invalid Credentials",null);
		}else {
			return new CustomResponse<>(200,"Login Successfully",ansuser);
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
}
