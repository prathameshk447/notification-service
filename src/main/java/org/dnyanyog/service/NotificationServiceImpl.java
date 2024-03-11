package org.dnyanyog.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.dnyanyog.common.Constant.NotificationMode;
import org.dnyanyog.dto.request.NotificationRequest;
import org.dnyanyog.dto.response.NotificationResponse;
import org.dnyanyog.entity.Notification;
import org.dnyanyog.repo.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	NotificationRepository repo;

	@Override
	public NotificationResponse sendNotification(NotificationRequest request) {
		NotificationResponse response = NotificationResponse.getInstance();

		if (request.getMode() == null || request.getSubject() == null || request.getBody() == null
				|| request.getTo() == null) {
			response.setStatus("error");
			response.setCode("NOTI0001");
			response.setMessage("Incomplete data sent");
			return response;
		}
		try {
			NotificationMode.valueOf(request.getMode().toUpperCase());
		} catch (IllegalArgumentException e) {
			response.setStatus("error");
			response.setCode("NOTI0002");
			response.setMessage("Invalid notification mode");
			return response;
		}
		if ((request.getMode().equals("EMAIL")) && !isValidEmail(request.getTo())) {
			response.setStatus("error");
			response.setCode("NOTI0003");
			response.setMessage("Invalid email address for To EMAIL");
			return response;
		}

		Notification notificatonEntity = Notification.getInstance()
				.setClient_id(request.getClientId())
				.setBody(request.getBody())
				.setMode(request.getMode())
				.setSubject(request.getSubject())
				.setFooter(request.getFooter())
				.setFrom_email(request.getFrom())
				.setTo_email(request.getTo())
				.setCreated_date(LocalDateTime.now())
				.setUpdated_date(LocalDateTime.now());

		try {
			notificatonEntity = repo.save(notificatonEntity);
			sendEmail(request.getTo(), request.getSubject(), request.getBody(), request.getFooter());
			response.setStatus("Success");
			response.setCode("0000");
			response.setMessage("Notification sent successfully!");
			response.setTimestamp(notificatonEntity.getCreated_date());
		} catch (Exception e) {
			response.setStatus("error");
			response.setCode("NOTI0004");
			response.setMessage("Error occurred while saving or sending notification");
			e.printStackTrace();
		}
		return response;
	}

	private boolean isValidEmail(String email) {
		return email != null && email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
	}

	private void sendEmail(String recipientEmail, String subject, String body, String footer) throws IOException {
		
		Properties properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("application.config"));

		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(properties.getProperty("email.sender.address"),
                        properties.getProperty("email.sender.password"));
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(properties.getProperty("email.sender.address")));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
			message.setSubject(subject);
			message.setText(body + "\n" + footer);
			Transport.send(message);

			System.out.println("Email sent successfully to: " + recipientEmail);
		} catch (MessagingException e) {
			System.out.println("Failed to send email to: " + recipientEmail);
			e.printStackTrace();
		}
	}
}
