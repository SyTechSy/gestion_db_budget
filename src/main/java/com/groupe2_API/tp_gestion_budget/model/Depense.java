package com.groupe2_API.tp_gestion_budget.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;


@Entity
@Data
public class Depense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @NotNull(message = "ce champ doit etre remplit")
    @Column(nullable = false)
    @Size(min=2, message = "trop court")
    private String titre;


    @Column(nullable = false)
    @Size(min=2, message = "trop court")
    private String note;

    @NotNull(message = "ce champ doit etre remplit")
    @Column(nullable = false)
    private double montant;

    @DateTimeFormat
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private LocalDate date;


    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "idTypeDepense", nullable = false)
    private TypeDepense typeDepense;

    @ManyToOne
    @JoinColumn(name = "idBudget", nullable = false)
    private Budget budget;

}
