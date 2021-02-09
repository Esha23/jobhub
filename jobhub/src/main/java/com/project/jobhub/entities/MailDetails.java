package com.project.jobhub.entities;

public class MailDetails {
	
	private String subject;
	
	private String body;

	public MailDetails() {
		
	}

	public MailDetails(String subject, String body) {
		super();
		this.subject = subject;
		this.body = body;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
