package com.groupe2_API.tp_gestion_budget.service;


import com.groupe2_API.tp_gestion_budget.model.Budget;
import com.groupe2_API.tp_gestion_budget.model.Categorie;
import com.groupe2_API.tp_gestion_budget.model.Depense;
import com.groupe2_API.tp_gestion_budget.model.EmailDetails;
import com.groupe2_API.tp_gestion_budget.repository.BudgetRepository;
import com.groupe2_API.tp_gestion_budget.repository.DepenseRepository;
import com.groupe2_API.tp_gestion_budget.repository.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepenseService {

    @Autowired
     DepenseRepository depenseRepository;
    @Autowired
    BudgetService budgetService;
    @Autowired
    BudgetRepository budgetRepository;

    @Autowired
    EmailService emailService;


   /* public Depense creer(Depense depense){

        return depenseRepository.save(depense);
    }*/

    public String creerDepense(Depense depense) {
        // Récupérer le budget associé à la dépense
        Budget budget = budgetRepository.findById(depense.getBudget().getIdBudget()).orElse(null);

        if (budget == null) {
            return "Budget non trouvé pour l'ID spécifié.";
        }

        double montantDepense = depense.getMontant();
        double montantBudget = budget.getMontant();

        if (montantDepense > montantBudget) {
            return "Le montant de la dépense ne doit pas dépasser celui du budget.";
        } else {
            // Enregistrer la dépense
            depenseRepository.save(depense);

            // Mettre à jour le montant restant dans le budget
            double montantRestant = montantBudget - montantDepense;
            budget.setMontantRestant(montantRestant);
            budgetRepository.save(budget);
            // envoyer emaill a chaque depense
            String msg = "Votre budget est de " + budget.getMontant() + " Fcfa, maintenant il vous reste " + montantRestant;
            EmailDetails details = new EmailDetails(depense.getUser().getEmail(),msg,"Détaille de votre depense");
            emailService.sendSimpleMail(details);

            return "Dépense créée avec succès. Montant restant dans le budget : " + montantRestant;
        }


    }


    public List<Depense> list(){
        return depenseRepository.findAll();
    }

    public Depense modifier(Long id , Depense depense){

        return depenseRepository.findById(id)
                .map(d->{
                  d.setTitre(d.getTitre());
                  d.setNote(d.getNote());
                  d.setMontant(d.getMontant());
                  d.setDate(d.getDate());
                  return depenseRepository.save(d);
                }).orElseThrow(()->new RuntimeException("Depense non trouver"));
    }

    public List<Depense> recherche(String titre){

        return (List<Depense>) depenseRepository.findByTitre(titre);

    }

    public String supprimer(Long id,Depense depense){
        depenseRepository.deleteById(id);
        return "suppression effectuée";
    }



}
