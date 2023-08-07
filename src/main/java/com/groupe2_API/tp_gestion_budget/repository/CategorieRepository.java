package com.groupe2_API.tp_gestion_budget.repository;

import com.groupe2_API.tp_gestion_budget.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository  extends JpaRepository<Categorie, Integer> {
    public Categorie findByTitre(String titre);

    public Categorie findByIdCategorie(int id);
    //public Categorie findByIdCategorie(int id);
}
