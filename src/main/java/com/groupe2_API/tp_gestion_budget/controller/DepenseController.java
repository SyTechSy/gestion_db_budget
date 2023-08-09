package com.groupe2_API.tp_gestion_budget.controller;


import com.groupe2_API.tp_gestion_budget.model.Categorie;
import com.groupe2_API.tp_gestion_budget.model.Depense;
import com.groupe2_API.tp_gestion_budget.service.DepenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("depense")
@Data
public class DepenseController {

    public final DepenseService depenseService;


    @PostMapping("/creer")
    @Operation(summary = "Création des dépenses")
    @ApiResponse(responseCode = "200", description = "Succès",
            content = @Content(schema = @Schema(implementation = Depense.class)))
    @ApiResponse(responseCode = "404", description = "Non trouvé")
    public String creer(@RequestBody Depense depense){
        return depenseService.creerDepense(depense);
    }

    @GetMapping("")
    @Operation(summary = "Liste des dépenses")
    @ApiResponse(responseCode = "200", description = "Succès",
            content = @Content(schema = @Schema(implementation = Depense.class)))
    @ApiResponse(responseCode = "404", description = "Non trouvé")
    public List<Depense> list(){
    return depenseService.list();
    }

    @GetMapping("/recherche")
    @Operation(summary = "Recherche des dépenses par le titre")
    @ApiResponse(responseCode = "200", description = "Succès",
            content = @Content(schema = @Schema(implementation = Depense.class)))
    @ApiResponse(responseCode = "404", description = "Non trouvé")
    public List<Depense> recherche (@PathVariable String titre){
        return depenseService.recherche(titre);
    }

    @PutMapping("/modifier/{id}")
    @Operation(summary = "Modification des dépenses par l'identifiant")
    @ApiResponse(responseCode = "200", description = "Succès",
            content = @Content(schema = @Schema(implementation = Depense.class)))
    @ApiResponse(responseCode = "404", description = "Non trouvé")
    public Depense modifier(@PathVariable Long id,@RequestBody Depense depense){

        return depenseService.modifier(id,depense);
    }

    @DeleteMapping("/supprimer/{id}")
    @Operation(summary = "Suppression des dépenses par l'identifiant")
    @ApiResponse(responseCode = "200", description = "Succès",
            content = @Content(schema = @Schema(implementation = Depense.class)))
    @ApiResponse(responseCode = "404", description = "Non trouvé")
    public String supprimer(@PathVariable Long id,@RequestBody Depense depense){
        return depenseService.supprimer(id,depense);
    }


}
