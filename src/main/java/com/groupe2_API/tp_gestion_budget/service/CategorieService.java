package com.groupe2_API.tp_gestion_budget.service;

import com.groupe2_API.tp_gestion_budget.exception.NoContentException;
import com.groupe2_API.tp_gestion_budget.exception.NotFoundException;
import com.groupe2_API.tp_gestion_budget.model.Budget;
import com.groupe2_API.tp_gestion_budget.model.Categorie;
import com.groupe2_API.tp_gestion_budget.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
        List<Categorie> categorieList = categorieRepository.findAll();
        if (categorieList.isEmpty())
            throw new NoContentException("La liste de catégorie est introuvable");

        return new ResponseEntity<>(categorieList, HttpStatus.OK);
        /*try {
            return new ResponseEntity<>(categorieRepository.findAll(), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);*/
    }

    //Modifier un objet categorire
    public Categorie modifierCategorie(Categorie categorie) {
        if (categorieRepository.findByIdCategorie(categorie.getIdCategorie()) != null) {
            return categorieRepository.save(categorie);
        } else {
            throw new NotFoundException("On peut modifier quelque chose qui n'existe pas !");
        }
    }

    // Suppresion de mon categorie

    public String deledeCategorie(Categorie categorie) {
        if (categorieRepository.findByIdCategorie(categorie.getIdCategorie()) != null) {
            categorieRepository.delete(categorie);
            return "Succès";
        } else {
            return "existe pas";
        }
    }

}
