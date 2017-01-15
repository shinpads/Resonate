package lemonapps.localmusicscene;

/**
 * Created by Rob on 1/14/2017.
 */
import android.util.Log;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSend {
    private boolean SendEmail(String to, String subject, String message){
        String host = "";
        String from = "";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host",host);
        Session session = Session.getDefaultInstance(properties);
        try{
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            msg.setSubject(subject);
            msg.setText(message);
            Transport.send(msg);
            return true;
        }catch (Exception ex){
            Log.e("JAVAMAIL",ex.getLocalizedMessage());
        }
        return false;
    }
}
