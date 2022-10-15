package com.controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.entity.CustomResponse;
import com.entity.*;
import com.resourse.*;



@Configuration
@RestController
@CrossOrigin("*")
public class TwilioRouterConfig {
	@Autowired
    private TwilioOTPHandler handler;
		
	

    @PostMapping("/sendOTP")
    public CustomResponse<?> sendOTP(@RequestHeader("number") String phone){
    	
    	
    	return this.handler.sendOTP1(phone);
    }
    
    

}
