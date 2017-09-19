package com.m4u.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m4u.domain.Sms;
import com.m4u.repository.SmsRepository;

@RestController
@RequestMapping("/sms")
public class SmsController {
	
	
	@PostMapping("/send")
    public String sendSms() {
        return "Greetings from Spring Boot!";
    }

}
