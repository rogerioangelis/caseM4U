package com.m4u.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.m4u.domain.enums.StatusSms;

@Entity
public class Sms {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column(name = "sender")
	private String from;
	
	@NotNull
	private String to;
	
	@Column(length = 160)
	@NotNull
	private String body;
	
	@Enumerated(EnumType.STRING)
	private StatusSms status;
	
	private Date expiration;

	public Sms() {

	}

	public Sms(String from, String to, String body, Date expiration) {
		super();
		this.from = from;
		this.to = to;
		this.body = body;
		this.expiration = expiration;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}


	public String getTo() {
		return to;
	}

	public String getBody() {
		return body;
	}


	public Date getExpiration() {
		return expiration;
	}
	
	public StatusSms getStatus() {
		return status;
	}

	public void setStatus(StatusSms status) {
		this.status = status;
	}
}
