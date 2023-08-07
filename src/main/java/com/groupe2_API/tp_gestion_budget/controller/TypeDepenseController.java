package com.groupe2_API.tp_gestion_budget.controller;


import com.groupe2_API.tp_gestion_budget.model.TypeDepense;
import com.groupe2_API.tp_gestion_budget.service.TypeDepenseService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("typeDepense")
@Data
public class TypeDepenseController {

    public final TypeDepenseService typeDepenseService;


    @PostMapping("/creer")
    public TypeDepense creer(@RequestBody TypeDepense typeDepense){
        return typeDepenseService.creer(typeDepense);
    }

    @GetMapping("")
    public List<TypeDepense> list(){
        return typeDepenseService.list();
    }


    @PutMapping("/modifier/{id}")
    public TypeDepense modifier(@PathVariable Long id,@RequestBody TypeDepense typeDepense){
        return typeDepenseService.modifier(id, typeDepense);
    }



    @DeleteMapping("/supprimer/{id}")
    public String TypeDepense(@PathVariable Long id, @RequestBody TypeDepense typeDepense){
        return typeDepenseService.supprimer(id, typeDepense);
    }

}
