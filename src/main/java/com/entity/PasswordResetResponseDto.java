package com.entity;

import com.repository.OtpStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//Below you are importing wrong Value class



public class PasswordResetResponseDto {
	private OtpStatus status;
    private String message;
	public PasswordResetResponseDto(OtpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public OtpStatus getStatus() {
		return status;
	}
	public void setStatus(OtpStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
    
    
}
