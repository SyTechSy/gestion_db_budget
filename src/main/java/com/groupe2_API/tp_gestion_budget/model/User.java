package com.groupe2_API.tp_gestion_budget.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class User {
    // ============================== User ID =======================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private int idUser;

    // ============================== NOM =======================

    @NotNull(message = "Champs ne doit pas être vide")

    @Size(min = 2, message = "Votre nom est court")

    @Column(nullable = false)
    private String nom;

    // ============================== PRENOM =======================

    @NotNull(message = "Champs ne doit pas être vide")

    @Size(min = 2, message = "Votre prenom est court")

    @Column(nullable = false)
    private String prenom;

    // ============================== EMAIL =======================

    @NotNull(message = "Champs ne doit pas être vide")

    @Email(message = "Email non valide")

    @Column(nullable = false)
    private String email;

    // ============================== Password =======================

    @NotNull(message = "Champs ne doit pas être vide")

    @Size(min = 6, message = "Votre mot de passe est court")

    @Column(nullable = false)
    private String motDePasse;

}
