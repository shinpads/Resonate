package lemonapps.localmusicscene;

/**
 * Created by Rob on 1/14/2017.
 */
import android.util.Log;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSend {
    public static boolean SendEmail(String to, String subject, String message){
        Properties properties = new Properties();
        properties.put("mail.smtp.host","smpt.gmail.com");
        properties.put("mail.smtp.socketFactory.port","465");
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.port","465");

        Session session = Session.getDefaultInstance(properties);
        new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuth(){
                return new PasswordAuthentication("localmusicsceneconfirm@gmail.com","thesolohoarder@123");
            }
        };

        try{
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("localmusicsceneconfirm@gmail.com"));
            msg.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            msg.setSubject(subject);
            msg.setText(message);
            Transport.send(msg);

        }catch (Exception ex){
            Log.e("EMAILSEND",ex.getLocalizedMessage());
        }
        return false;
    }
}
