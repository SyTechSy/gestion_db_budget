package com.groupe2_API.tp_gestion_budget.repository;

import com.groupe2_API.tp_gestion_budget.model.Depense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepenseRepository extends JpaRepository<Depense,Long> {

    public Depense findByTitre(String titre);
}
