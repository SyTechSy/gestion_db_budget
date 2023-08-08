package com.groupe2_API.tp_gestion_budget.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


import lombok.Data;

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

    // ==============================  =======================

    @Column(nullable = false)
    private Date date;


    //========================Relation entre budget et categorie====================

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "idCategorie")
    private Categorie categorie;
    //========================Relation entre budget et user====================
    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;


  @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
  private List<Depense> depenses = new ArrayList<>();
}
