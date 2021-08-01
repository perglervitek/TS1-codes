package cz.cvut.fel.ts1.refactoring;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author balikm1
 */
public class MailHelper {
   private DBManager dbManager = new DBManager();
    public void createAndSendMail(String to, String subject, String body)
    {
        Mail mail = new Mail();
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setBody(body);
        mail.setIsSent(false);

        saveMail(mail);
        sendMail(mail);

    }
    
    public void sendMail(int mailId)
    {
        try
        {
            // get entity
            Mail mail = new DBManager().findMail(mailId);
            if (mail == null) {
                return;
            }

            if (mail.isSent()) {
                return;
            }

            String from = "user@fel.cvut.cz";
            String smtpHostServer = "smtp.cvut.cz";
            Properties props = System.getProperties();
            props.put("mail.smtp.host", smtpHostServer);
            Session session = Session.getInstance(props, null);
            MimeMessage message = new MimeMessage(session);

            message.setFrom(from);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getTo(), false));
            message.setSubject(mail.getSubject());
            message.setText(mail.getBody(), "UTF-8");

            // send
            Transport.send(message);
            mail.setIsSent(true);
            new DBManager().saveMail(mail);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
    }
    public void saveMail(Mail mail){


        dbManager.saveMail(mail);
    }public void sendMail(Mail mail){

        if (!Configuration.isDebug) {
            (new Thread(() -> {
                sendMail(mail.getMailId());
            })).start();
        }
    }
}
