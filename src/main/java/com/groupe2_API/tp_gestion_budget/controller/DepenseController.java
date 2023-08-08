package com.groupe2_API.tp_gestion_budget.controller;


import com.groupe2_API.tp_gestion_budget.model.Depense;
import com.groupe2_API.tp_gestion_budget.service.DepenseService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("depense")
@Data
public class DepenseController {

    public final DepenseService depenseService;


    @PostMapping("/creer")
    public Depense creer(@RequestBody Depense depense){
        return depenseService.creer(depense);
    }

    @GetMapping("")
    public List<Depense> list(){
    return depenseService.list();
    }

    @GetMapping("/recherche/{titre}")
    public List<Depense> recherche (@PathVariable String titre){
        return depenseService.recherche(titre);
    }

    @PutMapping("/modifier/{id}")
    public Depense modifier(@PathVariable Long id,@RequestBody Depense depense){
        return depenseService.modifier(id,depense);
    }

    @DeleteMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Long id,@RequestBody Depense depense){
        return depenseService.supprimer(id,depense);
    }


}
