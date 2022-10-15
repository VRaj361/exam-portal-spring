package com;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.service.UserService;
import com.twilio.Twilio;
import com.config.TwilioConfig;
import com.entity.RoleEntity;
import com.entity.UserEntity;
import com.entity.UserRoleEntity;


import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableWebMvc
@SpringBootApplication
public class ExamPortalApplication implements CommandLineRunner{

	@Autowired
	private UserService userService;

	@Autowired
	private TwilioConfig twilioConfig;

	@PostConstruct
	public void initTwilio(){
		Twilio.init(twilioConfig.getAccountSid(),twilioConfig.getAuthToken());
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(ExamPortalApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
	
//		UserEntity user=new UserEntity();
//		user.setEmail("harmit@gmail.com");
//		user.setFirstName("Harmit");
//		user.setLastName("Patel");
//		user.setPassword("Harmit1234@");
//		user.setPhoneNum("1234567890");
//		user.setProfilePic("harmit.png");
//		
//		RoleEntity role=new RoleEntity();
//		role.setRolename("Admin");
//		role.setRoleid(1);
//		
//		Set<UserRoleEntity> users=new HashSet<UserRoleEntity>();
//		UserRoleEntity userrole=new UserRoleEntity();
//		userrole.setUser(user);
//		userrole.setRole(role);
//		
//		users.add(userrole);
//		userService.createUser(user, users);
		
	}
	
	@Bean
	public Docket generateDoc() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com"))
				.build();
	}
	
	
	

}
