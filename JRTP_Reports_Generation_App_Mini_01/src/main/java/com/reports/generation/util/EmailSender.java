package com.reports.generation.util;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailSender {

	@Autowired
	private JavaMailSender javaMailSender;

	public boolean mailSender(String subject, String body, String[] to,File f) throws MessagingException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
		try {
			helper.setSubject(subject);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		try { 
			helper.setText(body, true);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		try {
			helper.setTo(to);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		try {
			helper.addAttachment("plans_info_data.pdf", f);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {

			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

}
