package com.m4u.service;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.verify.VerificationTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.m4u.domain.Sms;
import com.m4u.domain.enums.StatusSms;
import com.m4u.repository.SmsRepository;
import com.m4u.service.sms.SmsService;
import com.m4u.service.sms.core.exception.SmsException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsServiceIntegrationTest {
	
	@Autowired
	private SmsService smsService;
	
	@Autowired
	private SmsRepository repository;
	
	private static MockServerClient mockServer;
	
	@BeforeClass
	public static void setup() {
		mockServer = ClientAndServer.startClientAndServer(8081);
	}
	
	@AfterClass
	public static void tearDown() {
		mockServer.stop();
	}
	
	@Test
	public void testSendSmsSuccess() {
		mockServer.reset();
		mockServer.when(
				HttpRequest.request()
				.withMethod("PUT")
				.withPath("/api/v1"))
		.respond(HttpResponse.response()
				.withStatusCode(201));
		
		Sms sms = new Sms("from", "to", "body", new DateTime(2018, 10, 10, 0, 0).toDate());
		sms.setId(1L);
		
		smsService.sendSms(sms);
		
		mockServer.verify(HttpRequest.request()
				.withMethod("PUT")
				.withPath("/api/v1")
				.withBody( "{\"from\":\"from\",\"to\":\"to\",\"body\":\"body\"}"), 
				VerificationTimes.once());
		
		Sms persistedSms = repository.findOne(1L);
		Assert.assertNotNull("Sms foi persistido", persistedSms);
		Assert.assertEquals("Status enviado", StatusSms.SENT, persistedSms.getStatus());
	}
	
	@Test(expected = SmsException.class)
	public void testSendSmsValidationException() {
		mockServer.reset();
		mockServer.when(
				HttpRequest.request()
				.withMethod("PUT")
				.withPath("/api/v1"))
		.respond(HttpResponse.response()
				.withStatusCode(400));
		
		Sms sms = new Sms("from", "to", "body", new DateTime(2018, 10, 10, 0, 0).toDate());
		sms.setId(2L);
		
		try {
			smsService.sendSms(sms);
		} catch(SmsException se) {
			mockServer.verify(HttpRequest.request()
					.withMethod("PUT")
					.withPath("/api/v1")
					.withBody( "{\"from\":\"from\",\"to\":\"to\",\"body\":\"body\"}"), 
					VerificationTimes.once());
			
			Sms persistedSms = repository.findOne(2L);
			Assert.assertNotNull("Sms foi persistido", persistedSms);
			Assert.assertEquals("Status enviado", StatusSms.FAILED, persistedSms.getStatus());
			Assert.assertEquals("Validation exception", se.getMessage());
			
			throw se;
		}
	}
	
	@Test(expected = SmsException.class)
	public void testSendSmsUserNotFound() {
		mockServer.reset();
		mockServer.when(
				HttpRequest.request()
				.withMethod("PUT")
				.withPath("/api/v1"))
		.respond(HttpResponse.response()
				.withStatusCode(404));
		
		Sms sms = new Sms("from", "to", "body", new DateTime(2018, 10, 10, 0, 0).toDate());
		sms.setId(3L);
		
		try {
			smsService.sendSms(sms);
		} catch(SmsException se) {
			mockServer.verify(HttpRequest.request()
					.withMethod("PUT")
					.withPath("/api/v1")
					.withBody( "{\"from\":\"from\",\"to\":\"to\",\"body\":\"body\"}"), 
					VerificationTimes.once());
			
			Sms persistedSms = repository.findOne(3L);
			Assert.assertNotNull("Sms foi persistido", persistedSms);
			Assert.assertEquals("Status enviado", StatusSms.FAILED, persistedSms.getStatus());
			Assert.assertEquals("Mobile User not found", se.getMessage());
			
			throw se;
		}
	}
	
	@Test(expected = SmsException.class)
	public void testSendSmsInternalServerError() {
		mockServer.reset();
		mockServer.when(
				HttpRequest.request()
				.withMethod("PUT")
				.withPath("/api/v1"))
		.respond(HttpResponse.response()
				.withStatusCode(500));
		
		Sms sms = new Sms("from", "to", "body", new DateTime(2018, 10, 10, 0, 0).toDate());
		sms.setId(4L);
		
		try {
			smsService.sendSms(sms);
		} catch(SmsException se) {
			mockServer.verify(HttpRequest.request()
					.withMethod("PUT")
					.withPath("/api/v1")
					.withBody( "{\"from\":\"from\",\"to\":\"to\",\"body\":\"body\"}"), 
					VerificationTimes.once());
			
			Sms persistedSms = repository.findOne(4L);
			Assert.assertNotNull("Sms foi persistido", persistedSms);
			Assert.assertEquals("Status enviado", StatusSms.FAILED, persistedSms.getStatus());
			Assert.assertEquals("Internal Server Error", se.getMessage());
			
			throw se;
		}
	}

}
