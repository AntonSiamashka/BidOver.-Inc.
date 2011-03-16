package com.bidover.common.mail;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.SimpleEmail;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.commons.mail.EmailException;

public class MailSender {

    public static String hostname,  mailfrom,  hostlogin,  hostpass;
    public static int smtpport;

    public MailSender()
            throws ClassNotFoundException {
        /* ResourceBundle set = ResourceBundle.getBundle("mail.MailBundle", Locale.ENGLISH);
        hostname = set.getString("HOSTNAME");
        mailfrom = set.getString("MAILFROM");
        hostlogin = set.getString("HOSTLOGIN");
        hostpass = set.getString("HOSTPASS");
        smtpport = Integer.parseInt(set.getString("SMTPPORT"));*/
    }

    public boolean sendMail(String mailto, String nameto, String namefrom, String subject, String message) {
        boolean flag = false;
        /*try {
        SimpleEmail email = new SimpleEmail();
        email.setCharset("utf-8");
        email.setHostName(hostname);
        email.setSmtpPort(smtpport);
        email.addTo(mailto, nameto);
        email.setFrom(mailfrom, namefrom);
        email.setSubject(subject);
        email.setMsg(message);
        email.setAuthentication(hostlogin, hostpass);
        email.send();
        flag = true;
        } catch (EmailException ex) {
        Logger.getLogger(MailSender.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        return true;
    }
}