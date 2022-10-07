package com.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BcryptionEncoder {
	@Bean
	public BCryptPasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}
}
