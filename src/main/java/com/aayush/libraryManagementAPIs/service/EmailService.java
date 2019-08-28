package com.aayush.libraryManagementAPIs.service;

import com.aayush.libraryManagementAPIs.model.entity.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender)
    {
        this.javaMailSender=javaMailSender;
    }

    public void sendEmail(UserRegistration user) throws MessagingException
    {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo("caayush96@gmail.com");
        helper.setText("<html><body style='color:red'>Here is a cat picture!<br><br> <img src='cid:id101'/><body></html>", true);
        helper.setSubject("Uploading Attachment");
//        ClassPathResource file = new ClassPathResource("/images/boot.png");
//        helper.addAttachment(file.getFilename(),file);
        javaMailSender.send(message);
    }
}
