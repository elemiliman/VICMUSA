package org.vicmusa.church.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;


public class SmtpMailSender implements MailSender{

	private static final Log log = LogFactory.getLog(SmtpMailSender.class);
	
	private JavaMailSender javaMailSender;
	
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	@Async
	public void send(String to, String subject, String body) throws MessagingException {
		
		log.info("Sending SMTP mail from thread " + Thread.currentThread().getName());
		
		MimeMessage message = javaMailSender.createMimeMessage(); // true indicates multipart messages
		MimeMessageHelper helper;
		
		helper = new MimeMessageHelper(message, true); // true indicates html
		
		// helper can also be used for adding attachments etc.
		
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(body, true);
		
		javaMailSender.send(message);
	}
}
