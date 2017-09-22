package com.m4u.service.sms;

import org.springframework.http.ResponseEntity;

import com.m4u.domain.Sms;

public interface SmsService {
	
	public ResponseEntity<String> sendSms(Sms sms);

}
