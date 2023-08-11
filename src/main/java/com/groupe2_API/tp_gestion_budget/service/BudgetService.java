package com.groupe2_API.tp_gestion_budget.service;

import com.groupe2_API.tp_gestion_budget.exception.DuplicateException;
import com.groupe2_API.tp_gestion_budget.exception.NoContentException;
import com.groupe2_API.tp_gestion_budget.exception.NotFoundException;
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

    public Budget creerBudget(Budget budget) {


           double mt_tot = budget.getMontant();

        if (budgetRepository.findByIdBudget(budget.getIdBudget()) == null) {

            budget.setMontantRestant(mt_tot);
            return  budgetRepository.save(budget);
        }

        else {
            throw new DuplicateException("On peut pas creer un autre categorie qui existé déjà!");
        }
      //  throw new DuplicateException("On peut pas creer un autre categorie qui existé déjà!");

    }

    //Methode pour modifer un budget
    public Budget modifierBudget(Budget budget) {
        if (budgetRepository.findByIdBudget(budget.getIdBudget()) != null) {
            return budgetRepository.save(budget);
        } else {
            throw new NotFoundException("On peut modifier quelque chose qui n'existe pas !");
        }
    }

    // Liste des budgets
    public ResponseEntity<List<Budget>> getAllBudget() {
        List<Budget> budgetList = budgetRepository.findAll();
        if (budgetList.isEmpty())
            throw new NoContentException("La liste de budget est introuvable");

        return new ResponseEntity<>(budgetList, HttpStatus.OK);
        /*try {
            return new ResponseEntity<>(budgetRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
*/
    }
    //Pour supprimer un budget
    /*public String SupprimerBudget(Budget budget) {
        if (budgetRepository.findById(budget.getIdBudget()) != null) {
            budgetRepository.delete(budget);
            return "supprimer avec succèss";
        }
        throw new NotFoundException("On peut supprimer quelque chose qui n'existe pas !");
    }*/

    public String deleteBudget(Budget budget) {
       if (budgetRepository.findByIdBudget(budget.getIdBudget()) != null ) {
           budgetRepository.delete(budget);
           return "Succès";
       } else
           return "Budget n'existe pas";
    }
}
