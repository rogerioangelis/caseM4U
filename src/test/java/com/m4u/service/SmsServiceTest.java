package com.m4u.service;

import java.util.Date;

import javax.validation.ValidationException;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockserver.integration.ClientAndServer;

import com.m4u.domain.Sms;
import com.m4u.domain.enums.StatusSms;
import com.m4u.repository.SmsRepository;
import com.m4u.service.sms.SmsService;
import com.m4u.service.sms.core.SmsServiceImpl;


public class SmsServiceTest {
		
	private SmsService smsService;
	private SmsRepository repo;
	
	@Before
	public void setup() {
		repo = Mockito.mock(SmsRepository.class);
		smsService = new SmsServiceImpl(repo);
	}
			
	@Test(expected = ValidationException.class)
	public void testSmsWithFromEmpty() {
		Sms sms = new Sms(null, "to", "message", new Date());
		
		try {
			smsService.sendSms(sms);
		} catch (ValidationException ve) {
			Assert.assertEquals("Sms inválido!", ve.getMessage());
			sms.setStatus(StatusSms.FAILED);
			Mockito.verify(repo).save(sms);
			throw ve;
		}
	}
	
	@Test(expected = ValidationException.class)
	public void testSmsWithToEmpty() {
		Sms sms = new Sms("From", null, "message", new Date());
		
		try {
			smsService.sendSms(sms);
		} catch (ValidationException ve) {
			Assert.assertEquals("Sms inválido!", ve.getMessage());
			sms.setStatus(StatusSms.FAILED);
			Mockito.verify(repo).save(sms);
			throw ve;
		}
	}
	
	@Test(expected = ValidationException.class)
	public void testSmsWithBodyEmpty() {
		Sms sms = new Sms("Body", "to", null, new Date());
		
		try {
			smsService.sendSms(sms);
		} catch (ValidationException ve) {
			Assert.assertEquals("Sms inválido!", ve.getMessage());
			sms.setStatus(StatusSms.FAILED);
			Mockito.verify(repo).save(sms);
			throw ve;
		}
	}
	
	@Test(expected = ValidationException.class)
	public void testSmsWithDateExpired() {
		Sms sms = new Sms("Body", "to", "body", new DateTime(2015, 10, 10, 0, 0).toDate());
		
		try {
			smsService.sendSms(sms);
		} catch (ValidationException ve) {
			Assert.assertEquals("Sms inválido!", ve.getMessage());
			sms.setStatus(StatusSms.FAILED);
			Mockito.verify(repo).save(sms);
			throw ve;
		}
	}

}
