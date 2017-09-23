package com.m4u.mock;

import org.mockserver.client.server.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

public class MockServer {

	public static MockServerClient mockServer;
	
	public static void startMockServer() {
		mockServer = ClientAndServer.startClientAndServer(8081);

		mockServer.when(
				HttpRequest.request()
				.withMethod("PUT")
				.withPath("/api/v1"))
		.respond(HttpResponse.response()
				.withStatusCode(201));		
	}

}
