package com.project.jobhub.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.project.jobhub.entities.MailDetails;
import com.project.jobhub.entities.UserDetails;
import com.project.jobhub.services.IMailService;

@Service
public class MailService implements IMailService{
	
	@Autowired
    private JavaMailSender javaMailSender;

	@Override
	public String mail(MailDetails mailDetails, List<String> emails) {
		SimpleMailMessage msg = new SimpleMailMessage();

        for(String mail: emails) {
        	msg.setTo(mail);
        	msg.setSubject(mailDetails.getSubject());
	        msg.setText(mailDetails.getBody());

	        javaMailSender.send(msg);
        }
        return "mail sent";
	}

	@Override
	public UserDetails setSignUpMailDetails(UserDetails userDetails) {
		MailDetails mailDetails = new MailDetails();
		mailDetails.setSubject("Thank you for signing up with JobHub");
		mailDetails.setBody("You have successfully signed up ! All the best :)");
		userDetails.setMailDetails(mailDetails);
		return userDetails;
	}
	
}
