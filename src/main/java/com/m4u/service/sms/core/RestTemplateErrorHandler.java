package com.m4u.service.sms.core;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import com.m4u.service.sms.core.exception.SmsException;

public class RestTemplateErrorHandler implements ResponseErrorHandler {

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		String msg;
		Integer status = response.getRawStatusCode();

		switch (status) {
		case 400:
			msg = "Validation exception";
			break;
		case 404:
			msg = "Mobile User not found";
			break;
		case 500:
		default:
			msg = "Internal Server Error";
		}

		throw new SmsException(msg);
	}

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return !HttpStatus.CREATED.equals(response.getStatusCode());
	}

}
