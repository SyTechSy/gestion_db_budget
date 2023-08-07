package com.groupe2_API.tp_gestion_budget.service;

import com.groupe2_API.tp_gestion_budget.model.Budget;
import com.groupe2_API.tp_gestion_budget.model.Categorie;
import com.groupe2_API.tp_gestion_budget.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BudgetService {

    @Autowired
    BudgetRepository budgetRepository;

    //Methode pour créer un budget
    public Budget creerBudget(Budget budget){
        if (budget.getCategorie() == null){
            return budgetRepository.save(budget);
        } else {
            return null;
        }
    }
    //Methode pour modifer un budget
    public Budget modifierBudget(Budget budget) {
        if (budgetRepository.findByIdBudget(budget.getIdBudget()) != null) {
            return budgetRepository.save(budget);
        } else {
            return null;
        }
    }

    // Liste des budgets
    public ResponseEntity<List<Budget>> getAllBudget(){
        try {
            return new ResponseEntity<>(budgetRepository.findAll(), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
    //Pour supprimer un budget
    public String SupprimerBudget(Budget budget){
        if (budgetRepository.findById(budget.getIdBudget()) != null){
            budgetRepository.delete(budget);
            return "supprimer avec succèss";
        }
        return  null;
    }
}