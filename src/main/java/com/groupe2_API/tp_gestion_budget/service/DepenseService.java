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
        var budgetMontantRestant = budgetRepository.findByIdBudget(depense.getBudget().getIdBudget()).
                getMontantRestant();
        if(montantDepense > montantBudget)  {
            return "Le montant de la dépense ne doit pas dépasser celui du budget.";
        } else if (budgetMontantRestant == 0) {
            return "Impossible d'effectue une depense quelconque car votre budget est epuisé";
        } else if (budgetMontantRestant <= budget.getMontantRestant() * 0.3 ) {
                depenseRepository.save(depense);
               // System.out.println("Attention il ne vous reste plus que "+budget.getMontantRestant() * 0.3+" dans votre budget" );
            String msg = "Attention il ne vous reste plus que "+budget.getMontantRestant()+" dans votre budget";
            EmailDetails details = new EmailDetails(depense.getUser().getEmail(),msg,"Urgent Urgent");
            emailService.sendSimpleMail(details);
        } else {

                // Enregistrer la dépense
                depenseRepository.save(depense);


                // Mettre à jour le montant restant dans le budget
                double montantRestant = budget.getMontantRestant() - montantDepense;
                budget.setMontantRestant(montantRestant);
                budgetRepository.save(budget);


                // =========================================================
                /*
                String msg = "Attention Vous ne pouvez plus effectuez un autre depense. \nVotre budget ne doit pas diminuer au dessous de " +  budget.getMontantRestant() + " FCFA !";
                EmailDetails details = new EmailDetails(depense.getUser().getEmail(),msg,"Urgent");
                emailService.sendSimpleMail(details);
                */


            }

            String msg = "Votre budget etait de " + budget.getMontant() + " FCFA. " +
                    "\nPaiement de " + depense.getMontant() + " FCFA pour une depense de " +
                    budget.getCategorie().getTitre() + "\nDette: 0 FCFA. Nouveau Solde est : " + budget.getMontantRestant() + " FCFA !";
            EmailDetails details = new EmailDetails(depense.getUser().getEmail(),msg,"Détaille de votre depense");
            emailService.sendSimpleMail(details);

            return "Dépense créée avec succès. Montant restant dans le budget : " + budget.getMontantRestant();
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



}
