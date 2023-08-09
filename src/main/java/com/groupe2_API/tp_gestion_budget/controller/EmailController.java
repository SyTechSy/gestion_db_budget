package com.groupe2_API.tp_gestion_budget.controller;

import com.groupe2_API.tp_gestion_budget.model.Depense;
import com.groupe2_API.tp_gestion_budget.model.EmailDetails;
import com.groupe2_API.tp_gestion_budget.repository.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Alerte du budget")
    @ApiResponse(responseCode = "200", description = "Succès",
            content = @Content(schema = @Schema(implementation =EmailDetails.class)))
    @ApiResponse(responseCode = "404", description = "Non trouvé")
    public String
    sendMail(@RequestBody EmailDetails details)
    {
        return emailService.sendSimpleMail(details);
    }
}
