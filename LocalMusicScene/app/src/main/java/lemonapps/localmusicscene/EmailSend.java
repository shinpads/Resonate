package lemonapps.localmusicscene;

/**
 * Created by Rob on 1/14/2017.
 */
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSend extends AppCompatActivity {

    public static boolean sendEmail(String to, String subject, String text) {

                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "465");

                Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {

                        return new PasswordAuthentication("localmusicsceneconfrimation@gmail.com", "thesolohoarder@123");
                    }
                });

                try {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("localmusicsceneconfirmation@gmail.com"));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                    message.setSubject(subject);
                    message.setContent(text, "text/html; charset=utf-8");
                    Transport.send(message);
                    return true;

                } catch (Exception e) {
                    String error = (e.getLocalizedMessage() == null)?"error message null":e.getLocalizedMessage();
                   Log.e("ERROR",error);
                }
        return false;
    }


}