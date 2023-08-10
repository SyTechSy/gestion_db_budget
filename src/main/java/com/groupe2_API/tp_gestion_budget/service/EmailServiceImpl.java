package com.groupe2_API.tp_gestion_budget.service;

import com.groupe2_API.tp_gestion_budget.model.EmailDetails;
import com.groupe2_API.tp_gestion_budget.repository.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.File;

@Service

// Implémentation de l'interface EmailService
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    // Method 1
    // To send a simple email
    public String sendSimpleMail(EmailDetails details)
    {

        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getEmail());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Email envoyé avec succès !";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Email modification échoué !";
        }
    }
    @Override
    public String sendMailWithAttachment(EmailDetails details) {
        return null;
    }
}
