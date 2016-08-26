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
    private boolean sendFail;

    public MailSender(String toAddr, String recipientName, String content){
        this.toAddr = toAddr;
        this.recipientName = recipientName;
        this.content = content;
        this.sendFail = false;
    }

    public boolean isSendFail() {
        return sendFail;
    }

    public void run() {
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "localhost");

        Session session = Session.getInstance(props, null);

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("noreply@codit.com", "codit"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddr, recipientName));
            msg.setSubject("invitation from codit");

            msg.setContent(content, "text/html; charset=utf-8");

            Transport.send(msg);
        } catch( UnsupportedEncodingException ex){
            ex.printStackTrace();
        } catch (MessagingException ex) {
            sendFail = true;
            ex.printStackTrace();
        }
    }
}

