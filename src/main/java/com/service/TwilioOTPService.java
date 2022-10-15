package com.service;
import com.config.TwilioConfig;
import com.entity.CustomResponse;
import com.entity.*;
import com.repository.*;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class TwilioOTPService {
	@Autowired
    private TwilioConfig twilioConfig;

	
	@Autowired
	private UserRepository userRepo;
    Map<String, String> otpMap = new HashMap<>();

    public CustomResponse<?> sendOTPForPasswordReset(String number) {
    	
        PasswordResetResponseDto passwordResetResponseDto = null;
        number ="+91"+number;
        System.out.println("fdsafdsaasdfasdfasdfasdf "+number);
        try {
            PhoneNumber to = new PhoneNumber(number);
            PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
            String otp = generateOTP();
            String otpMessage = "Dear User , Your OTP is ##" + otp + "##. Use this Passcode to Forgot your Password. Thank You. PrepArials";
            Message message = Message
                    .creator(to, from,
                            otpMessage)
                    .create();
            otpMap.put(number, otp);
            passwordResetResponseDto = new PasswordResetResponseDto(OtpStatus.DELIVERED, otpMessage);
        } catch (Exception ex) {
            passwordResetResponseDto = new PasswordResetResponseDto(OtpStatus.FAILED, ex.getMessage());
        }

        return new CustomResponse<PasswordResetResponseDto>(200,"Get",passwordResetResponseDto);
    }

    
    public CustomResponse<?> validateOTP1(String number, String otp) {
    	System.out.println(otpMap);
    	System.out.println(number + " "+otp);
        if (otp.equals(otpMap.get("+91"+number))) {
            otpMap.remove(number,otp);
            UserEntity user = this.userRepo.findByPhoneNum(number);
            return new CustomResponse<UserEntity>(200, "Valid OTP please proceed with your change Password !", user);
        } else {
        	return new CustomResponse<UserEntity>(400, "Invalid otp please retry !", null);

        }
    }

    //4 digit otp
    private String generateOTP() {
        return new DecimalFormat("0000")
                .format(new Random().nextInt(9999));
    }
}
