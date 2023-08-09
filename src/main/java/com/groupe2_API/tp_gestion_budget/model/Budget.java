package com.groupe2_API.tp_gestion_budget.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private double montant;

    @NotNull(message = "Le champs date debut ne doit pas être vide")
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateDebut;

    @NotNull(message = "Le champs date fin ne doit pas être vide")
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateFin;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<Depense> depenses = new ArrayList<>();

    @ManyToOne
    @JsonIgnoreProperties(value = {"budgets"})
    @JoinColumn(name = "idUser", nullable = false)
    private User user;


    /*
    @DateTimeFormat(pattern = "dd-MM-yyyy")
=======
    @Column(nullable = false)
    private double montantRestant;
    // ==============================  =======================

    //@DateTimeFormat(pattern = "dd-MM-yyyy")
>>>>>>> 9a0267b3d03b5819a419d3fb222f64c425df3c2e
    @Column(name = "date")
    private Date date;
    /*@DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "dateD")
    private Date dateDebut;

   // @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "dateF")
    private Date dateFin;*/

    //========================Relation entre budget et categorie====================


    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "idCategorie")
    private Categorie categorie;
    //========================Relation entre budget et user====================
    @ManyToOne
<<<<<<< HEAD
    @JoinColumn(name = "idUser")
    private User user;*/




}
