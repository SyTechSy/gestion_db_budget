package com.groupe2_API.tp_gestion_budget.controller;
import com.groupe2_API.tp_gestion_budget.model.Budget;
import com.groupe2_API.tp_gestion_budget.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("budget")
public class BudgetController {

    @Autowired
    BudgetService budgetService;

    //ajout de budget
    @PostMapping("/ajouter")
    public ResponseEntity<Object> ajouterBudget(@RequestBody Budget budget){
        Budget verificationBudget = budgetService.creerBudget(budget);
        if (verificationBudget != null){
            return new ResponseEntity<>("Budget créée avec Succès", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cet budget existe déjà", HttpStatus.NOT_FOUND);
        }
    }
    //liste des budgets

    @GetMapping("/listeBudget")
    public ResponseEntity<List<Budget>> getAllBudget(){
        return budgetService.getAllBudget();
    }

    //modiefier
    @PutMapping("/modifier")
    public ResponseEntity<Object> modifierBudget(@RequestBody Budget budget) {
        Budget verificationBudget =  budgetService.modifierBudget(budget);
        if (verificationBudget != null) {
            return new ResponseEntity<>("Modification faite avec Succès", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Modification à echouer", HttpStatus.NOT_FOUND);
        }
    }
    //suppression
    @DeleteMapping("/supprimer/{idBudget}")
    public String Budget(@PathVariable int id, @RequestBody Budget budget){
        budgetService.SupprimerBudget(budget);
        return "supprimer avec succèss";
    }

}
