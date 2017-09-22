package com.m4u.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

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

	public Long getId() {
		return id;
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

	public void setFrom(String from) {
		this.from = from;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
}
