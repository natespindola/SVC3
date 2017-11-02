package core;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.activation.*;

public class SendEmail
{
    public void sendMail(String subject, String message, String to) {

        // Sender's email ID needs to be mentioned
        String from = "contatosebovirtual@gmail.com";

        try {
            // Create a default MimeMessage object.
            MimeMessage mMessage = new MimeMessage(this.getMailSession());

            // Set From: header field of the header.
            mMessage.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            mMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            mMessage.setSubject(subject);

            // Now set the actual message
            mMessage.setContent(message,"text/html");
            // Send message
            Transport.send(mMessage);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    private Session getMailSession()
    {

        final String username = "contatosebovirtual@gmail.com";
        final String password = "sebovirtualtop";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        return session;
    }
}
