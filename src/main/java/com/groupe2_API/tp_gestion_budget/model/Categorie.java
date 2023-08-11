package com.groupe2_API.tp_gestion_budget.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private int idCategorie;

    // ============================== TITRE =======================

    @NotNull(message = "Champs ne doit pas être vide")

    @Size(min = 2, message = "Saisissez au moins deux caractères")

    @Column(nullable = false)
    private String titre;
    // ============================== DESCRIPTION =======================

    @NotNull(message = "Champs ne doit pas être vide")

    @Size(min = 2, message = "Saisissez au moins deux caractères")

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Budget> budgets = new ArrayList<>();
}
