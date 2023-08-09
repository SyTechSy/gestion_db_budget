package com.groupe2_API.tp_gestion_budget.controller;

import com.groupe2_API.tp_gestion_budget.model.Categorie;

import com.groupe2_API.tp_gestion_budget.service.CategorieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Ajout des catégories")
    @ApiResponse(responseCode = "200", description = "Avec succès",
            content = @Content(schema = @Schema(implementation = Categorie.class)))
    @ApiResponse(responseCode = "404", description = "Non trouvé")
    public ResponseEntity<Object> ajouterCategorie(@RequestBody Categorie categorie){
        Categorie verificationCategorie = categorieService.creerCategory(categorie);
        if (!(verificationCategorie == null)){
            return new ResponseEntity<>("Categorie créée avec Succès", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cette catégorie existe déjà", HttpStatus.NOT_FOUND);
        }
    }


    //liste des categorie

    @GetMapping("/listeCategorie")
    @Operation(summary = "Liste des catégories")
    @ApiResponse(responseCode = "200", description = "Avec Succès",
            content = @Content(schema = @Schema(implementation = Categorie.class)))
    @ApiResponse(responseCode = "404", description = "Non trouvé")
    public ResponseEntity<List<Categorie>> getAllCategorie(){
        return categorieService.getAllCategorie();
    }

    @PutMapping("/modifier")
    @Operation(summary = "Modification des catégories")
    @ApiResponse(responseCode = "200", description = "Succès",
            content = @Content(schema = @Schema(implementation = Categorie.class)))
    @ApiResponse(responseCode = "404", description = "Non trouvé")
    public ResponseEntity<Object> modifierCategory(@RequestBody Categorie categorie) {
        Categorie verificationCategorie =  categorieService.modifierCategorie(categorie);
        if (verificationCategorie != null) {
            return new ResponseEntity<>("Modification faite avec Succès", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Modification à echouer", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/categorie/{idCategorie}")
    @Operation(summary = "suppression  des catégories par son identifiant")
    @ApiResponse(responseCode = "200", description = "Succès",
            content = @Content(schema = @Schema(implementation = Categorie.class)))
    @ApiResponse(responseCode = "404", description = "Non trouvé")
    public String Categorie(@PathVariable int id, @RequestBody Categorie categorie){
        categorieService.SupprimerCategorie(categorie);
        return "supprimer avec succèss";
    }

}
