package com.groupe2_API.tp_gestion_budget.service;

import com.groupe2_API.tp_gestion_budget.model.Budget;
import com.groupe2_API.tp_gestion_budget.model.Categorie;
import com.groupe2_API.tp_gestion_budget.model.Depense;
import com.groupe2_API.tp_gestion_budget.repository.BudgetRepository;
import com.groupe2_API.tp_gestion_budget.repository.CategorieRepository;
import com.groupe2_API.tp_gestion_budget.repository.SuiviRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuiviService {

    @Autowired
    SuiviRepository suiviRepository;
    @Autowired
    BudgetRepository budgetRepository;
    @Autowired
    BudgetService budgetService;
    @Autowired
    CategorieService categorieService;
    @Autowired
    CategorieRepository categorieRepository;

    public ResponseEntity<String> suivreBudget(List<Depense> depenses) {

        double montantMin = 5000;
        double montantDepenses = 0;

        Categorie categorie = new Categorie();
        Budget budget = budgetRepository.findByCategorie(categorie);

        if (budget != null) {

            for (Depense depense : depenses) {
                montantDepenses += depense.getMontant();
            }

            double montantRestant = budget.getMontant() - montantDepenses;

            if (montantRestant <= montantMin) {
                return ResponseEntity.ok("Attention, vous vous rapprochez du seuil minimum");
            } else {
                return ResponseEntity.ok("Montant restant : " + montantRestant);
            }

        } else {
            return  new ResponseEntity<>("Budget non trouvé pour cette categorie", HttpStatus.NOT_FOUND);
        }
    }
}
