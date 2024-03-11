package org.dnyanyog.entity;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Component
@Table
public class Notification {

	@Column(nullable = false, insertable = true, updatable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long notification_code;

	
	@Column(nullable = false, insertable = true, updatable = false, length = 36)
	private String client_id;

	
	@Column(nullable = false ,length =255)
	private String mode;

	
	@Column(nullable = false,length =255)
	private String subject;

	
	@Column(nullable = false,length =255)
	private String body;

	
	@Column(nullable = false,length =255)
	private String footer;

	
	@Column(nullable = false,length =255)
	private String from_email;

	
	@Column(nullable = false,length =255)
	private String to_email;

	
	@Column(nullable = false, insertable = true, updatable = false,length =255)
	private LocalDateTime created_date;

	
	@Column(nullable = false, insertable = true, updatable = true,length =255)
	private LocalDateTime updated_date;
	
	public static Notification getInstance() {
		return new Notification();
	}


	public long getNotification_code() {
		return notification_code;
	}

	public Notification setNotification_code(long notification_code) {
		this.notification_code = notification_code;
		return this;
	}


	public String getClient_id() {
		return client_id;
	}


	public Notification setClient_id(String client_id) {
		this.client_id = client_id;
		return this;
	}


	public String getMode() {
		return mode;
	}


	public Notification setMode(String mode) {
		this.mode = mode;
		return this;
	}


	public String getSubject() {
		return subject;
	}


	public Notification setSubject(String subject) {
		this.subject = subject;
		return this;
	}


	public String getBody() {
		return body;
	}


	public Notification setBody(String body) {
		this.body = body;
		return this;
	}


	public String getFooter() {
		return footer;
	}


	public Notification setFooter(String footer) {
		this.footer = footer;
		return this;
	}


	public String getFrom_email() {
		return from_email;
	}


	public Notification setFrom_email(String from_email) {
		this.from_email = from_email;
		return this;
	}


	public String getTo_email() {
		return to_email;
	}


	public Notification setTo_email(String to_email) {
		this.to_email = to_email;
		return this;
	}



	public LocalDateTime getCreated_date() {
		return created_date;
	}


	public Notification setCreated_date(LocalDateTime created_date) {
		this.created_date = created_date;
		return this;
	}


	public LocalDateTime getUpdated_date() {
		return updated_date;
	}


	public Notification setUpdated_date(LocalDateTime updated_date) {
		this.updated_date = updated_date;
		return this;
	}
	
}
