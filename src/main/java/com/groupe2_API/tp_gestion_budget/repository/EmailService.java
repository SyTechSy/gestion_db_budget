package com.groupe2_API.tp_gestion_budget.repository;

import com.groupe2_API.tp_gestion_budget.model.EmailDetails;

public interface EmailService {

    // To send a simple email
    String sendSimpleMail(EmailDetails details);

    // To send an email with attachment
    String sendMailWithAttachment(EmailDetails details);
}
