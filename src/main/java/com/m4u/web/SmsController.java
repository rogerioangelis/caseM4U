package com.m4u.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m4u.domain.Sms;
import com.m4u.service.sms.SmsService;

@RestController
@RequestMapping("/sms")
public class SmsController {

	private SmsService smsService;

	@Autowired
	public SmsController(SmsService smsService) {
		this.smsService = smsService;
	}

	@PostMapping("/send")
	public ResponseEntity<String> sendSms(@RequestBody Map<String, Object> body) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		String sender = (String) body.get("sender");
		String destination = (String) body.get("destination");
		String bodyMsg = (String) body.get("body");
		Date expiration = format.parse((String) body.get("expiration"));

		smsService.sendSms(new Sms(sender, destination, bodyMsg, expiration));

		return new ResponseEntity<String>("Sms sent", HttpStatus.OK);
	}
}
