package com.mvc.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	final static String username = "SEteam5project@gmail.com";
	final static String password = "SE12345@";
	final static String adminEmail="separkingadm@gmail.com";
	
	public static void send(String email, String data) {
		// TODO Auto-generated method stub
		final String username1 = "SEteam5project@gmail.com";
		final String password = "SE12345@";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username1, password);
			}
		});

		try {
			String dataarray[] = data.split(",");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from-email@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("Booking Confirmation");

			message.setContent("Dear " + dataarray[0] + "," + "<br/>Your booking id " + dataarray[6]
					+ " has been recieved and confirmed at <br/> <b>Parking Location: <b>" + dataarray[3] + "<br/><b>In Time:</b> "
					+ dataarray[4] + "<br/><b>Out Time:</b>" + dataarray[5] + " <br/><br/><br/><br/>Please Scan the code at the gate: <br/><img alt='' src='https://chart.googleapis.com/chart?cht=qr&chl="+dataarray[7]+"&chs=150x150&chld=L|0 '>","text/html");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public static void sendMailToAdmin(String data) {
		// TODO Auto-generated method stub

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			String text[] = data.split(",");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from-email@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(adminEmail));
			message.setSubject("Booking Confirmation");
			message.setText("Admin,\n\n A Parking Owner: "+text[1]+" has added a new Parking: "+text[0]+" at "+text[2]+","+text[3]+","+text[4]+"\n\n Please review the application.");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void sendApproval(String email, String pname, String poname) 
	{
		// TODO Auto-generated method stub
		final String username1 = "separkingadm@gmail.com";
		final String password = "ParkingAdmin";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username1, password);
			}
		});

		try {
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from-email@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("Booking Confirmation");

			message.setText("Dear " + poname + "," + "\n\n Your request for Parking: " + pname
					+ " has been approved.");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	public static void sendDenial(String email, String pname, String poname) 
	{
		// TODO Auto-generated method stub
		final String username1 = "separkingadm@gmail.com";
		final String password = "ParkingAdmin";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username1, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from-email@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("Booking Confirmation");

			message.setText("Dear " + poname + "," + "\n\n Your request for Parking: " + pname
					+ " has been declined.\n\n Reason: Insufficient Documents.");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}


}
