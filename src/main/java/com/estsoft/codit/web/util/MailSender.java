package com.estsoft.codit.web.util;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created by estsoft on 2016-08-02.
 */
public class MailSender implements Runnable{

    private String toAddr ;
    private String recipientName ;
    private String content ;

    public MailSender(String toAddr, String recipientName, String content){
        this.toAddr = toAddr;
        this.recipientName = recipientName;
        this.content = content;
    }


    public void run() {
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "localhost.localdomain");

        Session session = Session.getInstance(props, null);

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("noreply@codit.com", "codit"));
//      InternetAddress[] address = {new InternetAddress(toAddr)};
//      msg.setRecipients(Message.RecipientType.TO, address);
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddr, recipientName));
            msg.setSubject("invitation from codit");
//      MimeMultipart multipart = new MimeMultipart();
//
//      MimeBodyPart part = new MimeBodyPart();
//      part.setContent( "<a href>"+ content + "</a>", "text/html; charset=utf-8");
//      multipart.addBodyPart(part);

//      part = new MimeBodyPart();
//      FileDataSource fds = new FileDataSource("파일 경로");
//      part.setDataHandler(new DataHandler(fds));
//      part.setFileName("파일명");
//      multipart.addBodyPart(part);

//      msg.setContent(multipart);
            msg.setContent(content, "text/html; charset=utf-8");

            Transport.send(msg);
        } catch( UnsupportedEncodingException ex){
            ex.printStackTrace();
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
}

