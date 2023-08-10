package com.groupe2_API.tp_gestion_budget.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    //@DateTimeFormat(pattern = "dd-MM-yyyy")
    /*@Column(name = "date")
    private Date date;*/

    //===========================================================================================

    // creation date de debut
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "start_date")
    private LocalDate startDate;

    // crreation date de fin
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "end_date")
    private LocalDate endDate;


    //===========================================================================================



    //========================Relation entre budget et categorie====================


    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "idCategorie")
    private Categorie categorie;
    //========================
    // Relation entre budget et user====================
    @ManyToOne
    @JsonIgnoreProperties(value = {"budgets"})
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

    @JsonIgnore
  @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
  private List<Depense> depenses = new ArrayList<>();
}
