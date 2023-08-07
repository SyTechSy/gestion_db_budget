package com.groupe2_API.tp_gestion_budget.repository;

import com.groupe2_API.tp_gestion_budget.model.Budget;
import com.groupe2_API.tp_gestion_budget.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Integer> {

    public Budget findByCategorie(Categorie categorie);

    public Budget findByIdBudget(int id);
}
