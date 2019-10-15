/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundacion.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class Mail {

    private final static String KEY_SMTP_SERVER = "mail.smtp.host";
    private final static String KEY_SMTP_FROM = "mail.smtp.user";
    private final static String KEY_SMTP_PASSWORD = "mail.smtp.password";

    private static Properties props;

    private static void loadConfig() {
        if (props == null) {
            props = new Properties();
            props.put(KEY_SMTP_SERVER, "smtp.gmail.com"); // Servidor SMTP de Google
            props.put(KEY_SMTP_FROM, "fundacionpasosdefe01@gmail.com"); // Correo
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            props.put(KEY_SMTP_PASSWORD, "QwertY123456789"); // Clave del correo
            props.put("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
            props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
            // Los puertos pueden ser 25, 465 o 587
            props.put("mail.smtp.port", "25"); // El puerto SMTP seguro de Google
        }
    }

    //Solo texto HTML
    public static void sendMail(String destinatario, String asunto, String cuerpoHTML) {
        loadConfig();
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(props.getProperty(KEY_SMTP_FROM)));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject("[Fundacion pasos de fe] " + asunto);

            Multipart parts = new MimeMultipart();
            BodyPart bodyMail = new MimeBodyPart();
            String html = Mail.plantillaMail(cuerpoHTML);
            bodyMail.setContent(html, "text/html");
            parts.addBodyPart(bodyMail);
            message.setContent(parts);

            Transport transport = session.getTransport("smtp");
            transport.connect(props.getProperty(KEY_SMTP_SERVER),
                    props.getProperty(KEY_SMTP_FROM),
                    props.getProperty(KEY_SMTP_PASSWORD));
            transport.sendMessage(message, message.getAllRecipients());
        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }

    private static String plantillaMail(String html) {
        String rh = "<div style=\"display: flex; flex-direction: row; width: 100%;\">";
        rh += "<div style=\"padding: 10px; width: 100px;\">";
        rh += "<img src=\"https://drive.google.com/uc?export=view&id=1yIb0yGTYiihZ_sMSCjd4MCwMmqjAScsi\" alt=\"Logo\" width=\"80\" height=\"auto\">";
        rh += "</div>";
        rh += "<div style=\"padding: 10px;\">";
        rh += html; // Es el cuerpo que se inserta
        rh += "<p style=\"margin-top: 25px;\"><strong>Atentamente</strong><br>";
        rh += "Fundacion pasos de fe</p>";
        rh += "</div>";
        rh += "</div>";
        
        //https://drive.google.com/open?id=1yIb0yGTYiihZ_sMSCjd4MCwMmqjAScsi

        return rh;
    }
}
