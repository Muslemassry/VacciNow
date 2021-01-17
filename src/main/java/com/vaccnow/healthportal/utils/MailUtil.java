package com.vaccnow.healthportal.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.vaccnow.healthportal.model.Application;
import com.vaccnow.healthportal.reponse.FawryResponse;


public class MailUtil {

	final static String username = "vaccnow123@gmail.com";
    final static String password = "";

    static Properties prop = new Properties();
    static {
    	prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    }
    
	public static void sendConfirmationEmail(String toEmail, Application application) {
		Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("vaccnow123@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail)
            );
            message.setSubject("VaccNow application confirmation");
            String text = "<h1 style=\"color: #5e9ca0;\">VacciNow Confirmation!</h1>\r\n" + 
            		"<h2 style=\"color: #2e6c80;\">Congratulation for vaccination:</h2>\r\n" + 
            		"<h2 style=\"color: #2e6c80;\">Cleaning options:</h2>\r\n" + 
            		"<table class=\"editorDemoTable\" style=\"height: 298px;\">\r\n" + 
            		"<thead>\r\n" + 
            		"<tr style=\"height: 18px;\">\r\n" + 
            		"<td style=\"height: 18px; width: 274px;\">Patient Name</td>\r\n" + 
            		"<td style=\"height: 18px; width: 363px;\">" +application.getPatient().getName()+ "</td>\r\n" + 
            		"<td style=\"height: 18px; width: 0px;\">&nbsp;</td>\r\n" + 
            		"</tr>\r\n" + 
            		"</thead>\r\n" + 
            		"<tbody>\r\n" + 
            		"<tr style=\"height: 18px;\">\r\n" + 
            		"<td style=\"height: 18px; width: 274px;\">Patient ID</td>\r\n" + 
            		"<td style=\"height: 18px; width: 363px;\">" +application.getPatient().getId()+ "</td>\r\n" + 
            		"<td style=\"height: 18px; width: 0px;\">&nbsp;</td>\r\n" + 
            		"</tr>\r\n" + 
            		"<tr style=\"height: 18px;\">\r\n" + 
            		"<td style=\"height: 18px; width: 274px;\">Vaccine Name</td>\r\n" + 
            		"<td style=\"height: 18px; width: 363px;\"><span style=\"color: #008000;\"><span style=\"font-size: 13px;\">"+application.getVaccine().getName()+"</span></span></td>\r\n" + 
            		"<td style=\"height: 18px; width: 0px;\">&nbsp;</td>\r\n" + 
            		"</tr>\r\n" + 
            		"<tr style=\"height: 18px;\">\r\n" + 
            		"<td style=\"height: 18px; width: 274px;\">Vaccine ID</td>\r\n" + 
            		"<td style=\"height: 18px; width: 363px;\">"+application.getVaccine().getId()+"</td>\r\n" + 
            		"<td style=\"height: 18px; width: 0px;\">&nbsp;</td>\r\n" + 
            		"</tr>\r\n" + 
            		"<tr style=\"height: 18px;\">\r\n" + 
            		"<td style=\"height: 18px; width: 274px;\">Appointment</td>\r\n" + 
            		"<td style=\"height: 18px; width: 363px;\">"+(Instant.ofEpochMilli(application.getAppointment().getTime()).atZone(ZoneId.systemDefault()).toLocalDate()).toString()+"</td>\r\n" + 
            		"<td style=\"height: 18px; width: 0px;\">&nbsp;</td>\r\n" + 
            		"</tr>\r\n" + 
            		"<tr style=\"height: 18px;\">\r\n" + 
            		"<td style=\"height: 18px; width: 274px;\">Application number</td>\r\n" + 
            		"<td style=\"height: 18px; width: 363px;\">"+application.getId()+"</td>\r\n" + 
            		"<td style=\"height: 18px; width: 0px;\">&nbsp;</td>\r\n" + 
            		"</tr>\r\n" + 
            		"<tr style=\"height: 18px;\">\r\n" + 
            		"<td style=\"height: 18px; width: 274px;\">Price</td>\r\n" + 
            		"<td style=\"height: 18px; width: 363px;\">"+application.getVaccine().getPrice()+"</td>\r\n" + 
            		"<td style=\"height: 18px; width: 0px;\">&nbsp;</td>\r\n" + 
            		"</tr>\r\n" + 
            		"<tr style=\"height: 18px;\">\r\n" + 
            		"<td style=\"height: 18px; width: 274px;\">Payment type</td>\r\n" + 
            		"<td style=\"height: 18px; width: 363px;\">"+application.getPaymentType()+"</td>\r\n" + 
            		"<td style=\"height: 18px; width: 0px;\">&nbsp;</td>\r\n" + 
            		"</tr>\r\n" + 
            		"<tr style=\"height: 18px;\">\r\n" + 
            		"<td style=\"height: 18px; width: 274px;\">Branch Name</td>\r\n" + 
            		"<td style=\"height: 18px; width: 363px;\">"+application.getBranch().getName()+"<!-- HELLO! --></td>\r\n" + 
            		"<td style=\"height: 18px; width: 0px;\">&nbsp;</td>\r\n" + 
            		"</tr>\r\n" + 
            		"<tr style=\"height: 26px;\">\r\n" + 
            		"<td style=\"height: 26px; width: 274px;\">Branch ID</td>\r\n" + 
            		"<td style=\"height: 26px; width: 363px;\"><span style=\"color: #ff0000;\"><span style=\"font-size: 17px;\">"+application.getBranch().getId()+"</span></span></td>\r\n" + 
            		"<td style=\"height: 26px; width: 0px;\">&nbsp;</td>\r\n" + 
            		"</tr>\r\n" + 
            		"<tr style=\"height: 18px;\">\r\n" + 
            		"<td style=\"height: 18px; width: 274px;\">Branch Address</td>\r\n" + 
            		"<td style=\"height: 18px; width: 363px;\">"+application.getBranch().getAddress()+"</td>\r\n" + 
            		"<td style=\"height: 18px; width: 0px;\">&nbsp;</td>\r\n" + 
            		"</tr>\r\n" + 
            		"</tbody>\r\n" + 
            		"</table>\r\n" + 
            		"<p><strong>&nbsp;</strong></p>\r\n" + 
            		"<p><strong>Enjoy your life</strong></p>\r\n" + 
            		"<p><strong>&nbsp;</strong></p>";
     	   message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
     	   message.setContent(text,"text/html");
     	  System.out.println("To send ========================>>>> ");
          Transport.send(message);
          System.out.println("SENT ========================>>>> ");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
	}
	
	public static void sendInvoiceEmail(String toEmail, FawryResponse fawryResponse) {
		Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("vaccnow123@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail)
            );
            message.setSubject("VaccNow application Invoice");
            String text = "<h1 style=\"color: #5e9ca0;\">VacciNow Confirmation!</h1>\r\n" + 
            		"<h2 style=\"color: #2e6c80;\">Congratulation for vaccination:</h2>\r\n" + 
            		"<h2 style=\"color: #2e6c80;\">Cleaning options:</h2>\r\n" + 
            		"<table class=\"editorDemoTable\" style=\"height: 298px;\">\r\n" + 
            		"<thead>\r\n" + 
            		"<tr style=\"height: 18px;\">\r\n" + 
            		"<td style=\"height: 18px; width: 274px;\">Type</td>\r\n" + 
            		"<td style=\"height: 18px; width: 363px;\">" +fawryResponse.getType() + "</td>\r\n" + 
            		"<td style=\"height: 18px; width: 0px;\">&nbsp;</td>\r\n" + 
            		"</tr>\r\n" + 
            		"</thead>\r\n" + 
            		"<tbody>\r\n" + 
            		"<tr style=\"height: 18px;\">\r\n" + 
            		"<td style=\"height: 18px; width: 274px;\">Reference Number</td>\r\n" + 
            		"<td style=\"height: 18px; width: 363px;\">" +fawryResponse.getReferenceNumber()+ "</td>\r\n" + 
            		"<td style=\"height: 18px; width: 0px;\">&nbsp;</td>\r\n" + 
            		"</tr>\r\n" + 
            		"<tr style=\"height: 18px;\">\r\n" + 
            		"<td style=\"height: 18px; width: 274px;\">Status Description</td>\r\n" + 
            		"<td style=\"height: 18px; width: 363px;\"><span style=\"color: #008000;\"><span style=\"font-size: 13px;\">"+fawryResponse.getStatusDescription()+"</span></span></td>\r\n" + 
            		"<td style=\"height: 18px; width: 0px;\">&nbsp;</td>\r\n" + 
            		"</tr>\r\n" + 
            		"<tr style=\"height: 18px;\">\r\n" + 
            		"<td style=\"height: 18px; width: 274px;\">Merchant RefNumber</td>\r\n" + 
            		"<td style=\"height: 18px; width: 363px;\">"+fawryResponse.getMerchantRefNumber()+"</td>\r\n" + 
            		"<td style=\"height: 18px; width: 0px;\">&nbsp;</td>\r\n" + 
            		"</tr>\r\n" + 
            		"<tr style=\"height: 18px;\">\r\n" + 
            		"<td style=\"height: 18px; width: 274px;\">Order Amount</td>\r\n" + 
            		"<td style=\"height: 18px; width: 363px;\">"+fawryResponse.getOrderAmount()+"</td>\r\n" + 
            		"<td style=\"height: 18px; width: 0px;\">&nbsp;</td>\r\n" + 
            		"</tr>\r\n" + 
            		"<tr style=\"height: 18px;\">\r\n" + 
            		"<td style=\"height: 18px; width: 274px;\">Payment Amount</td>\r\n" + 
            		"<td style=\"height: 18px; width: 363px;\">"+fawryResponse.getPaymentAmount()+"</td>\r\n" + 
            		"<td style=\"height: 18px; width: 0px;\">&nbsp;</td>\r\n" + 
            		"</tr>\r\n" + 
            		"<tr style=\"height: 18px;\">\r\n" + 
            		"<td style=\"height: 18px; width: 274px;\">Fawry Fees</td>\r\n" + 
            		"<td style=\"height: 18px; width: 363px;\">"+fawryResponse.getFawryFees()+"</td>\r\n" + 
            		"<td style=\"height: 18px; width: 0px;\">&nbsp;</td>\r\n" + 
            		"</tr>\r\n" + 
            		"<tr style=\"height: 18px;\">\r\n" + 
            		"<td style=\"height: 18px; width: 274px;\">Payment Method</td>\r\n" + 
            		"<td style=\"height: 18px; width: 363px;\">"+fawryResponse.getPaymentMethod()+"</td>\r\n" + 
            		"<td style=\"height: 18px; width: 0px;\">&nbsp;</td>\r\n" + 
            		"</tr>\r\n" + 
            		"<tr style=\"height: 26px;\">\r\n" + 
            		"<td style=\"height: 26px; width: 274px;\">Customer Mobile</td>\r\n" + 
            		"<td style=\"height: 26px; width: 363px;\"><span style=\"color: #ff0000;\"><span style=\"font-size: 17px;\">"+fawryResponse.getCustomerMobile()+"</span></span></td>\r\n" + 
            		"<td style=\"height: 26px; width: 0px;\">&nbsp;</td>\r\n" + 
            		"</tr>\r\n" + 
            		"<tr style=\"height: 18px;\">\r\n" + 
            		"<td style=\"height: 18px; width: 274px;\">Customer Mail</td>\r\n" + 
            		"<td style=\"height: 18px; width: 363px;\">"+fawryResponse.getCustomerMail()+"</td>\r\n" + 
            		"<td style=\"height: 18px; width: 0px;\">&nbsp;</td>\r\n" + 
            		"</tr>\r\n" + 
            		"</tbody>\r\n" + 
            		"</table>\r\n" + 
            		"<p><strong>&nbsp;</strong></p>\r\n" + 
            		"<p><strong>Enjoy your life</strong></p>\r\n" + 
            		"<p><strong>&nbsp;</strong></p>";
     	   message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
     	   message.setContent(text,"text/html");
     	  System.out.println("To send ========================>>>> ");
          Transport.send(message);
          System.out.println("SENT ========================>>>> ");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
	}
}
