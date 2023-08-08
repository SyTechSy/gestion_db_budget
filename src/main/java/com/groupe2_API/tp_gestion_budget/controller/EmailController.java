package com.groupe2_API.tp_gestion_budget.controller;

import com.groupe2_API.tp_gestion_budget.model.EmailDetails;
import com.groupe2_API.tp_gestion_budget.repository.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class EmailController {

    @Autowired private EmailService emailService;

    // Envoi d'e-mail avec pièce jointe
    @PostMapping("/sendMail")
    public String
    sendMail(@RequestBody EmailDetails details)
    {
        return emailService.sendSimpleMail(details);
    }
}
