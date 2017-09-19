package com.m4u.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m4u.domain.Sms;
import com.m4u.repository.SmsRepository;

@Service
public class SmsService {

	@Autowired
	SmsRepository repository;
	
	private final String URI = "recruitment.m4u.com.br/api/v1";
	
	public void sendSms(Sms sms) {
		
	}
}
