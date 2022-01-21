package com.sportify.email;

import com.sportify.user.UserEntity;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.System.*;

public class EmailSender {

    public void sendPlayerEmail(String sport, int courtID, String sportCenterAddress, int startTime, int finishTime){
        try (InputStream input = new FileInputStream("src/main/resources/com.sportify.email/email.properties")) {

            Properties prop = new Properties();

            // carica il file properties
            prop.load(input);

            String from = prop.getProperty("email.from");
            String pass = prop.getProperty("email.psw");
            String host = prop.getProperty("email.host");

            Properties properties = this.setProperties(host, from, pass);

            // Get the default Session object.
            Session session = Session.getDefaultInstance(properties);

            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(UserEntity.getInstance().getEmail()));

            // Set Subject: header field
            message.setSubject("Booking confirmation");

            //create the message
            String messageBody = """
                    Hello, this email is a confirmation and a reminder of the booking you made through Sportify!
                    
                    Details:
                    You booked the %s field/court at %s, %s.
                    You booked this court/filed from %d to %d, don't be late!
                    
                    Thanks for using our service,
                    Sportify Team
                    """;
            messageBody =  String.format(messageBody, sport, courtID, sportCenterAddress, startTime, finishTime);

            // Now set the actual message
            message.setText(messageBody);

            // Send message
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            out.println("Sent message successfully....");

        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendOwnerEmail(String ownerEmail, String sport, int courtID, int startTime, int finishTime) {
        //carica dal file properties le informazioni sulla email da utilizzare per il servizio di notifiche email
        //la relativa password e il server host
        try (InputStream input = new FileInputStream("src/main/resources/com/sportify/email/email.properties")) {

            Properties prop = new Properties();

            // carica il file properties
            prop.load(input);

            String from = prop.getProperty("email.from");
            String pass = prop.getProperty("email.psw");
            String host = prop.getProperty("email.host");

            Properties properties = this.setProperties(host, from, pass);

            // Get the default Session object.
            Session session = Session.getDefaultInstance(properties);

            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(ownerEmail));

            // Set Subject: header field
            message.setSubject("A new match has been booked at your sports center!");

            //create the message
            String messageBody = """
                    Hello, good news!
                    A new match has been booked at your sport center through Sportify.
                    
                    Details of the booking:
                    %s court/field number %d is now booked from %d to %d!
                    
                    Thanks for using our service,                    
                    Sportify Team
                    """;
            messageBody =  String.format(messageBody, sport, courtID, startTime, finishTime);

            // Now set the actual message
            message.setText(messageBody);

            // Send message
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            out.println("Sent message successfully....");

        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
    }

    private Properties setProperties(String host, String from, String pass) {
        // Get system properties
        Properties properties = System.getProperties();
        // Setup mail server
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", from);
        properties.put("mail.smtp.password", pass);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");

        return properties;
    }
}
