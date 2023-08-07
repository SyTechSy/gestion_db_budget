package com.groupe2_API.tp_gestion_budget.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class TypeDepense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @NotNull(message = "ce champ doit etre remplit")
    @Column(nullable = false)
    @Size(min=2, message = "trop court")
    private String libelle;


    @Column(nullable = false)
    @Size(min=2, message = "trop court")
    private String description;


    @OneToMany(mappedBy = "typeDepense" , cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Depense> depenses;
}
