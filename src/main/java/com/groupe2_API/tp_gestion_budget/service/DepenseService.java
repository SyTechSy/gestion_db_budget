package com.groupe2_API.tp_gestion_budget.service;


import com.groupe2_API.tp_gestion_budget.exception.NoContentException;
import com.groupe2_API.tp_gestion_budget.exception.NotFoundException;
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
            String msg = "Votre budget est de " + budget.getMontant() + " Fcfa." + "\nPour une depense de " + budget.getCategorie().getTitre() + ". \nMaintenant votre solde principale est de : " + budget.getMontantRestant() + " Fcfa !";
            EmailDetails details = new EmailDetails(depense.getUser().getEmail(),msg,"Détaille de votre depense");
            emailService.sendSimpleMail(details);

            return "Dépense créée avec succès. Montant restant dans le budget : " + montantRestant;
        }


    }


    public List<Depense> list(){
        List <Depense> depenseList=depenseRepository.findAll();
        if(depenseList.isEmpty())
            throw new NoContentException("La liste de dépense est introuvable");

        return depenseList;
    }

    public Depense modifier(Long id , Depense depense){

        return depenseRepository.findById(id)
                .map(d->{
                  d.setTitre(d.getTitre());
                  d.setNote(d.getNote());
                  d.setMontant(d.getMontant());
                  d.setDate(d.getDate());
                  return depenseRepository.save(d);
                }).orElseThrow(()->new RuntimeException("Dépense non trouvée"));
    }

    public List<Depense> recherche(String titre){
        return (List<Depense>) depenseRepository.findByTitre(titre);
    }

    public String supprimer(Long id,Depense depense){
        if(depenseRepository.findById(id)!=null){
            depenseRepository.save(depense);
            return "suppression effectuée";
        }
        throw new NotFoundException("Cette dépense n'existe pas et ne pas être supprimer");
    }

// bien


}
