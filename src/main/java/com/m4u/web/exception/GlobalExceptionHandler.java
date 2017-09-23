package com.m4u.web.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.m4u.service.sms.core.exception.SmsException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = { SmsException.class } )
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String,String> handleException(SmsException ex) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("error", ex.getMessage());

		return errorMap;
	}

}
