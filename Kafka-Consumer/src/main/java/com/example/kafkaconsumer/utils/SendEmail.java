package com.example.kafkaconsumer.utils;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
    final String User_Email = "DoctorBryde27@outlook.com"; //your email
    final String Password = "DummyUser22"; // your email password
    final String Sender = "DoctorBryde27@outlook.com"; // Insert Your email again
    final String Receiver = "DoctorBryde27@outlook.com"; // Insert Receiver's Email
    final String Email_Subject = "Test Email Subject";

    final String Content = "Hello! This is delftstack program for sending email.";

    

    public void Send_Email(String email_Subject,String receiver,String message) {
        final Session newsession = Session.getInstance(this.Mail_Properties(), new Authenticator() {
            @Override
            // password authentication
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(User_Email, Password);
            }
        });
        // MimeMessage is used to create the email message
        try {
            final Message Demo_Message = new MimeMessage(newsession);
            Demo_Message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            Demo_Message.setFrom(new InternetAddress(Sender));
            Demo_Message.setSubject(email_Subject); // email subject
            Demo_Message.setText(message); // The content of email
            Demo_Message.setSentDate(new Date());
            Transport.send(Demo_Message);// Transport the email
            System.out.println("Your Email has been sent successfully!");
        }
        catch (final MessagingException e) { // exception to catch the errors
            System.out.println("Email Sending Failed"); // failed
            e.printStackTrace();
        }
    }

    // The permanent  set of properties containing string keys, the following
    // setting the properties for SMPT function
    public Properties Mail_Properties() {
        final Properties Mail_Prop = new Properties();
        Mail_Prop.put("mail.smtp.host", "smtp.office365.com");
        Mail_Prop.put("mail.smtp.port", "587");
        Mail_Prop.put("mail.smtp.auth", true);
        Mail_Prop.put("mail.smtp.starttls.enable", true);
        Mail_Prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        return Mail_Prop;
    }

    public static void main(final String[] args) {
        new SendEmail().Send_Email("Service Unavailable","mattibenhansen@gmail.com","Service Unavailable: "+"test");
    }
}