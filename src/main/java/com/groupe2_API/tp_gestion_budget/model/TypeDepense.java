package com.groupe2_API.tp_gestion_budget.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class TypeDepense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;


    private String libelle;

    private String description;


    @OneToMany(mappedBy = "typeDepense" , cascade = CascadeType.ALL)
    private List<Depense> depenses;
}
