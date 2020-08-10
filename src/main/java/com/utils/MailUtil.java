package com.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.RecipientStringTerm;

public class MailUtil {
     String from = "";
     String password="";
    //Get the session object  
     Properties properties = new Properties();    
     Session session = Session.getDefaultInstance(properties);  
 
     public MailUtil() {
    	 
     }
     
     public void sendMail(String body, String to_email) throws MessagingException
     {
    	properties.put("mail.smtp.auth","true");
		 properties.put("mail.smtp.starttls.enable", "true");
		 properties.put("mail.smtp.host","smtp.gmail.com");
		 properties.put("mail.smtp.port", "587");
		 
		 Session session = Session.getInstance(properties, new Authenticator() {
			 @Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(from, password);
			}
		});
		Message message = prepareMesssage(session, from, to_email, body);
		Transport.send(message);
		System.out.print("Sending password");
     } 
     
     private Message prepareMesssage(Session session2, String from2, String to_email, String body) {
		Message message = new MimeMessage(session2);
		try {
			message.setFrom(new InternetAddress(from2));
			message.setSubject("OTP Details");
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to_email));
			message.setText(body);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return message;
	}
} 
