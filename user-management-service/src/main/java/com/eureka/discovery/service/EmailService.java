package com.eureka.discovery.service;

import javax.mail.MessagingException;

public interface EmailService {
	
	public String sendSimpleEmail(String to, String body , String subject);
	
	public String sendEmailWithAttachment(String to , String body , String subject) throws MessagingException;
	

}
