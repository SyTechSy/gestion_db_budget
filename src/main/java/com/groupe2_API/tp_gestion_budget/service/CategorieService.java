package com.groupe2_API.tp_gestion_budget.service;

import com.groupe2_API.tp_gestion_budget.exception.NoContentException;
import com.groupe2_API.tp_gestion_budget.exception.NotFoundException;
import com.groupe2_API.tp_gestion_budget.model.Categorie;
import com.groupe2_API.tp_gestion_budget.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategorieService {

    @Autowired
    CategorieRepository categorieRepository;

    //Methode pour créer une categorie
    public Categorie creerCategory(Categorie categorie){
        if (categorieRepository.findByTitre(categorie.getTitre()) == null){
            return categorieRepository.save(categorie);
        } else {
            throw new NoContentException("On peut pas creer un autre categorie qui existé déjà!");
        }
    }
    //La liste des categories

    public ResponseEntity<List<Categorie>> getAllCategorie(){
        try {
            return new ResponseEntity<>(categorieRepository.findAll(), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    //Modifier un objet categorire
    public Categorie modifierCategorie(Categorie categorie) {
        if (categorieRepository.findByIdCategorie(categorie.getIdCategorie()) != null) {
            return categorieRepository.save(categorie);
        } else {
            throw new NotFoundException("On peut modifier quelque chose qui n'existe pas !");
        }
    }
    public String SupprimerCategorie(Categorie categorie){
       if (categorieRepository.findById(categorie.getIdCategorie()) != null){
           categorieRepository.delete(categorie);
           return "supprimer avec succèss";
       }
       throw new NotFoundException("On peut Supprimer quelque chose qui n'existe pas !");
    }

}
