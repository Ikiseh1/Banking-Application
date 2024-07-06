package com.ikiseh.World.Banking.Application.service.impl;

import com.ikiseh.World.Banking.Application.payload.request.EmailDetails;
import com.ikiseh.World.Banking.Application.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;


    @Value("${spring.mail.username}")
    private String senderEmail;

    @Override
    public void sendEmailAlert(EmailDetails emailDetails) {

//        try{
//            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//            simpleMailMessage.setFrom(senderEmail);
//            simpleMailMessage.setTo(emailDetails.getRecipient());
//            simpleMailMessage.setText(emailDetails.getMessageBody());
//            simpleMailMessage.setSubject(emailDetails.getSubject());
//
//            javaMailSender.send(simpleMailMessage);
//        } catch (MailException e) {
//            throw new RuntimeException(e);
//        }

        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(senderEmail);
            helper.setTo(emailDetails.getRecipient());
            helper.setSubject(emailDetails.getSubject());
            helper.setText(emailDetails.getMessageBody());

            // Add attachment if present
            if (emailDetails.getAttachment() != null) {
                FileSystemResource file = new FileSystemResource(new File(emailDetails.getAttachment()));
                helper.addAttachment(file.getFilename(), file);
            }

            // Send the email
            javaMailSender.send(message);
        } catch (MessagingException e) {
            // Handle exception
            e.printStackTrace();

        }
    }
}
