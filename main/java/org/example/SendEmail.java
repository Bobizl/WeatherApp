package org.example;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import java.util.Date;
import java.util.Properties;

public class SendEmail {
    public static void sendMail(String fromEmail, String password,String receiver) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.office365.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail, "Sent from JVM"));

            msg.setReplyTo(InternetAddress.parse(fromEmail, false));

            msg.setSubject("Available day to send a shuttle to Space");

            Multipart multipart = new MimeMultipart();

            MimeBodyPart attachmentPart = new MimeBodyPart();
            String filename = "src\\WeatherReport.csv";
            FileDataSource source = new FileDataSource(filename);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(filename);
            multipart.addBodyPart(attachmentPart);
            msg.setContent(multipart);

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver, false));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("E-mail Sent Successfully!!");
        } catch (AuthenticationFailedException e) {
            System.out.println("Authentication failed. Please check your email and password.");

        } catch (Exception e) {
            System.out.println("File is not found");;
        }
    }
}
