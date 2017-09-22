package com.m4u;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.m4u.mock.MockServer;

@SpringBootApplication
public class CaseM4UApplication {

	public static void main(String[] args) {
		MockServer.startMockServer();
		
		SpringApplication.run(CaseM4UApplication.class, args);
	}
}
