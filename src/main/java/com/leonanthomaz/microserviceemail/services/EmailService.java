package com.leonanthomaz.microserviceemail.services;

import com.leonanthomaz.microserviceemail.enums.StatusEmail;
import com.leonanthomaz.microserviceemail.models.Email;
import com.leonanthomaz.microserviceemail.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Properties;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    EmailRepository repository;

    public EmailService() {
    }

    @Transactional
    public Email sendEmail(Email emailModel) {

        emailModel.setStatusEmail(StatusEmail.PROCESSING);
        emailModel.setSendDateEmail(LocalDateTime.now());

        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);

        } catch (MailException e){
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return repository.save(emailModel);
        }
    }
}
