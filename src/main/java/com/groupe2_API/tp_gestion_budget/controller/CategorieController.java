package com.groupe2_API.tp_gestion_budget.controller;

import com.groupe2_API.tp_gestion_budget.model.Categorie;

import com.groupe2_API.tp_gestion_budget.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categorie")
public class CategorieController {

    @Autowired
    CategorieService categorieService;

    //ajout de categorie
    @PostMapping("/ajouter")
    public ResponseEntity<Object> ajouterCategorie(@RequestBody Categorie categorie){
        Categorie verificationCategorie = categorieService.creerCategory(categorie);
        if (verificationCategorie == null){
            return new ResponseEntity<>("Categorie créée avec Succès", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cette catégorie existe déjà", HttpStatus.CONFLICT);
        }
    }


    //liste des categorie

    @GetMapping("/listeCategorie")
    public ResponseEntity<List<Categorie>> getAllCategorie(){
        return categorieService.getAllCategorie();
    }

    @PutMapping("/modifier")
    public ResponseEntity<Object> modifierCategory(@RequestBody Categorie categorie) {
        Categorie verificationCategorie =  categorieService.modifierCategorie(categorie);
        if (verificationCategorie != null) {
            return new ResponseEntity<>("Modification faite avec Succès", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Modification à echouer", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/categorie/{idCategorie}")
    public String Categorie(@PathVariable int id, @RequestBody Categorie categorie){
        categorieService.SupprimerCategorie(categorie);
        return "supprimer avec succèss";
    }

}
