package com.groupe2_API.tp_gestion_budget.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;


@Data
@Entity
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private int idBudget;

    // ==============================  =======================

    @NotNull(message = "Champs ne doit pas être vide")
    @Column(nullable = false)
    private double montant;

    // ==============================  =======================

    @Column(nullable = false)
    private Date date;

    //========================Relation entre budget et categorie====================
    @OneToOne
    @JoinColumn(name = "idCategorie")
    private Categorie categorie;
    //========================Relation entre budget et user====================
   @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;
}