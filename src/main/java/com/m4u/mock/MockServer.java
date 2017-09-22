package com.m4u.mock;

import org.mockserver.client.server.MockServerClient;
import org.mockserver.model.Header;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.HttpStatusCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;

public class MockServer {

	@Value("${m4u.service.url}")
	private static String serviceUrl;
	
	public static MockServerClient mockServer;
	
	public static void startMockServer() {
		mockServer = new MockServerClient("http://recruitment.m4u.com.br", 8888);
//
//		mockServer.when(
//				HttpRequest.request().withMethod("PUT"))
//		.respond(
//				HttpResponse.response().withStatusCode(200));		
	}

}
