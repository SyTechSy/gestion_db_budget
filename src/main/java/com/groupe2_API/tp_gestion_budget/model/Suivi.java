package com.groupe2_API.tp_gestion_budget.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Suivi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private Integer idSuiviBudget;

    @OneToMany(mappedBy = "suivi", cascade = CascadeType.ALL)
    private List<Depense> depense = new ArrayList<>();

    @OneToOne(mappedBy = "suivi", cascade = CascadeType.ALL)
    private Budget budget;
}
