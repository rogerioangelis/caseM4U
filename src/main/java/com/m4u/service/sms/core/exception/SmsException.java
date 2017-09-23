package com.m4u.service.sms.core.exception;

public class SmsException extends RuntimeException {
	public SmsException(String message) {
		super( message );
	}

	public SmsException() {
		super();
	}

	public SmsException(String message, Throwable cause) {
		super( message, cause );
	}

	public SmsException(Throwable cause) {
		super( cause );
	}
}
