package org.dnyanyog.dto.request;

import org.springframework.stereotype.Component;

@Component
public class NotificationRequest {

	private String clientId;
	private String mode;
	private String subject;
	private String body;
	private String footer;
	private String from;
	private String to;
	
	public static NotificationRequest getInstance() {
		return new NotificationRequest();
	}
	
	public String getClientId() {
		return clientId;
	}

	public NotificationRequest setClientId(String clientId) {
		this.clientId = clientId;
		return this;
	}

	public String getMode() {
		return mode;
	}

	public NotificationRequest setMode(String mode) {
		this.mode = mode;
		return this;
	}

	public String getSubject() {
		return subject;
	}

	public NotificationRequest setSubject(String subject) {
		this.subject = subject;
		return this;
	}

	public String getBody() {
		return body;
	}

	public NotificationRequest setBody(String body) {
		this.body = body;
		return this;
	}

	public String getFooter() {
		return footer;
	}

	public NotificationRequest setFooter(String footer) {
		this.footer = footer;
		return this;
	}

	public String getFrom() {
		return from;
	}

	public NotificationRequest setFrom(String from) {
		this.from = from;
		return this;
	}

	public String getTo() {
		return to;
	}

	public NotificationRequest setTo(String to) {
		this.to = to;
		return this;
	}

}
