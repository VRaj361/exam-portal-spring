package com.resourse;

import com.entity.CustomResponse;
import com.entity.PasswordResetRequestDto;
import com.service.TwilioOTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TwilioOTPHandler {
	@Autowired
    private TwilioOTPService service;


    public CustomResponse<?> sendOTP1(String number) {
    	return service.sendOTPForPasswordReset(number);
    }
    
    public CustomResponse<?> validateOTP(String number,String otp){
    	return service.validateOTP1(number,otp);
    }


}
