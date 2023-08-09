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

    @Column(nullable = false)
    private double montantRestant;
    // ==============================  =======================

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "date")
    private Date date;


    //========================Relation entre budget et categorie====================


    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "idCategorie")
    private Categorie categorie;
    //========================Relation entre budget et user====================
    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;


  @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
  private List<Depense> depenses = new ArrayList<>();
}
