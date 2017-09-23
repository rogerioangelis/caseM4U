package com.m4u.service.sms.core;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ValidationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.m4u.domain.Sms;
import com.m4u.domain.enums.StatusSms;
import com.m4u.repository.SmsRepository;
import com.m4u.service.sms.SmsService;

@Service
public class SmsServiceImpl implements SmsService {

	private SmsRepository repository;

	@Value("${m4u.service.url}")
	private String serviceUrl;

	@Autowired
	public SmsServiceImpl(SmsRepository repository) {
		this.repository = repository;
	}

	@Retryable(value = Exception.class, backoff = @Backoff(delay = 500, multiplier = 2), maxAttempts = 3)
	public void sendSms(Sms sms) {
		validateSms(sms);
		
		RestTemplate restTemplate = createRestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("from", sms.getFrom());
		requestMap.put("to", sms.getTo());
		requestMap.put("body", sms.getBody());

		HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestMap, headers);

		ResponseEntity<String> response = restTemplate.exchange(serviceUrl + "/api/v1", HttpMethod.PUT, request, String.class);
		
		if (response.getStatusCode().equals(HttpStatus.CREATED)) {
			sms.setStatus(StatusSms.SENT);
		} else {
			sms.setStatus(StatusSms.FAILED);
		}
		
		repository.save(sms);
	}
	
	private void validateSms(Sms sms) {
		if (StringUtils.isBlank(sms.getFrom()) || StringUtils.isBlank(sms.getTo()) || StringUtils.isBlank(sms.getBody())) {
			throw new ValidationException("Sms inválido!");
		}
	}
	
	public RestTemplate createRestTemplate() {
		RestTemplate template = new RestTemplate();
		template.setErrorHandler(new RestTemplateErrorHandler());

		return template;
	}

}
