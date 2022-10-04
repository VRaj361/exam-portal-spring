package com.service;

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

import com.entity.RoleEntity;
import com.entity.UserEntity;
import com.entity.UserRoleEntity;


@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	private UserService userService;
	
	//creating user
	@PostMapping("/")
	public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) throws Exception{
		
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
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ansuser);
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(ansuser);
		}
		
	}
	
	//delete user using id
	@DeleteMapping("/")
	public ResponseEntity<String> deleteUser(@RequestHeader("userid") String userid){
		Boolean user = userService.deleteUser(userid);
		if(user == false) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id is incorrect");
		}else {
			return ResponseEntity.status(HttpStatus.OK).body("User Delete Successfully");
		}
	}
	
}
