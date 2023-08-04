package com.groupe2_API.tp_gestion_budget.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private long idBudget;

    @NotNull(message = "Le champs montant de doit pas être vide")
    @Min(value = 5000, message = "Désole vous montant doivent être superieur ou egale a 5000 Fcfa")
    @Column(nullable = false)
    private int montant;


    @NotNull(message = "Le champs montant de doit pas être vide")
    @Column(nullable = false)
    private Date dateDebut;

    @NotNull(message = "Le champs montant de doit pas être vide")
    @Column(nullable = false)
    private Date dateFin;


}
