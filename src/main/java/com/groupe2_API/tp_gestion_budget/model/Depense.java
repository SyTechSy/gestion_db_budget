package com.groupe2_API.tp_gestion_budget.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Entity
@Data
public class Depense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    private String titre;

    private String note;

    private double montant;

    @DateTimeFormat
    private Date date;


    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "idTypeDepense", nullable = false)
    private TypeDepense typeDepense;

}
