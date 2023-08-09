package com.groupe2_API.tp_gestion_budget.controller;


import com.groupe2_API.tp_gestion_budget.model.Depense;
import com.groupe2_API.tp_gestion_budget.model.TypeDepense;
import com.groupe2_API.tp_gestion_budget.service.TypeDepenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("typeDepense")
@Data
public class TypeDepenseController {

    public final TypeDepenseService typeDepenseService;


    @PostMapping("/creer")
    @Operation(summary = "Ajout d'un type de dépense")
    @ApiResponse(responseCode = "200", description = "Succès",
            content = @Content(schema = @Schema(implementation = TypeDepense.class)))
    @ApiResponse(responseCode = "404", description = "Non trouvé")
    public TypeDepense creer(@RequestBody TypeDepense typeDepense){
        return typeDepenseService.creer(typeDepense);
    }

    @GetMapping("")
    @Operation(summary = "Liste des types de dépense")
    @ApiResponse(responseCode = "200", description = "Succès",
            content = @Content(schema = @Schema(implementation = TypeDepense.class)))
    @ApiResponse(responseCode = "404", description = "Non trouvé")
    public List<TypeDepense> list(){
        return typeDepenseService.list();
    }


    @PutMapping("/modifier/{id}")
    @Operation(summary = "Modification des types de dépenses")
    @ApiResponse(responseCode = "200", description = "Succès",
            content = @Content(schema = @Schema(implementation = TypeDepense.class)))
    @ApiResponse(responseCode = "404", description = "Non trouvé")
    public TypeDepense modifier(@PathVariable Long id,@RequestBody TypeDepense typeDepense){
        return typeDepenseService.modifier(id, typeDepense);
    }



    @DeleteMapping("/supprimer/{id}")
    @Operation(summary = "Suppression des types de dépenses")
    @ApiResponse(responseCode = "200", description = "Succès",
            content = @Content(schema = @Schema(implementation = TypeDepense.class)))
    @ApiResponse(responseCode = "404", description = "Non trouvé")
    public String TypeDepense(@PathVariable Long id, @RequestBody TypeDepense typeDepense){
        return typeDepenseService.supprimer(id, typeDepense);
    }

}
