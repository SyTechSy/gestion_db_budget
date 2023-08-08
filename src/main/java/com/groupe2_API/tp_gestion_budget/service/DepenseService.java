package com.groupe2_API.tp_gestion_budget.service;


import com.groupe2_API.tp_gestion_budget.model.Depense;
import com.groupe2_API.tp_gestion_budget.repository.DepenseRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class DepenseService {

    public final DepenseRepository depenseRepository;
    public final BudgetService budgetService;

    public Depense creer(Depense depense){

        return depenseRepository.save(depense);
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
