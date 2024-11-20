package com.bluebarry.project_managment_sytem.service.implementation;

import com.bluebarry.project_managment_sytem.service.interfaces.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
@Service
public class EmailServiceImple implements EmailService {

    private final JavaMailSender javaMailSender;
    private Logger logger= LoggerFactory.getLogger(EmailServiceImple.class);

    public EmailServiceImple(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmailToSingleUser(String to, String subject, String text) {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        simpleMailMessage.setFrom("siraj14202@gmail.com");
        javaMailSender.send(simpleMailMessage);
        logger.info("email has been sent...");

    }

    @Override
    public void sendEmailToMultipleUsers(String[] to, String subject, String text) {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setText(text);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setFrom("siraj14202@gmail.com");
        javaMailSender.send(simpleMailMessage);
        logger.info("mail has been sent to all....");

    }



    @Override
    public void sendEmailWithAttachment(String[] to, String subject, String message, String attachmentfile) {
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
       try{
           MimeMessageHelper helper= new MimeMessageHelper(mimeMessage, true); // true indicates multipart message
           helper.setFrom("siraj14202@gmail.com");
           helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(message);

           FileSystemResource file = new FileSystemResource(new File(attachmentfile));
           helper.addAttachment(file.getFilename(), file);

           javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
           throw new RuntimeException(e);
       }

    }
}
