package domain;

import javax.persistence.Entity;

@Entity
public class Message {

	private Integer id;
	private String sender;
	private String destination;
	private String body;

	public Message() {

	}

	public Message(Integer id, String sender, String destination, String body) {
		super();
		this.id = id;
		this.sender = sender;
		this.destination = destination;
		this.body = body;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
