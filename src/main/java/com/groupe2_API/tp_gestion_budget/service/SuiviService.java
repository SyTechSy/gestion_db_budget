package com.groupe2_API.tp_gestion_budget.service;

import com.groupe2_API.tp_gestion_budget.model.Budget;
import com.groupe2_API.tp_gestion_budget.repository.BudgetRepository;
import com.groupe2_API.tp_gestion_budget.repository.SuiviRepository;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SuiviService {

    @Autowired
    SuiviRepository suiviRepository;
    @Autowired
    BudgetRepository budgetRepository;
    @Autowired
    BudgetService budgetService;

    public ResponseEntity<String> suivreBudget(long idBudget, Budget budget){

        Budget budget1 = new Budget();

        double  montantbudget = budget1.getMontant();


    return null;
    }

}
