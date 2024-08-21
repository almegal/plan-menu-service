package org.aston.client;

import org.aston.dto.NotificationDtoRequest;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
    public static void sendMail(NotificationDtoRequest notification) {
        final String fromEmail = "";
        final String password = "";
        final String toEmail = "";

        System.out.println("SSLEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");


        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

        Session session = Session.getDefaultInstance(props, auth);
        SendMail.sendEmail(session, toEmail, notification);

    }
    private static void sendEmail(Session session, String toEmail, NotificationDtoRequest notification){
        try
        {
            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress("Meal planning Team"));

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            msg.setSubject("Meal-plan notification");
            msg.setText(notification.message());

            Transport.send(msg);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

