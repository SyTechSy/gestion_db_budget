package com.groupe2_API.tp_gestion_budget.service;


import com.groupe2_API.tp_gestion_budget.exception.NotFoundException;
import com.groupe2_API.tp_gestion_budget.model.TypeDepense;
import com.groupe2_API.tp_gestion_budget.repository.TypeDepenseRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class TypeDepenseService {

    public final TypeDepenseRepository typeDepenseRepository;


    public TypeDepense creer(TypeDepense typeDepense) {
        if(typeDepense.getLibelle()!=null){
            return typeDepenseRepository.save(typeDepense);
        }else{
            throw new NotFoundException("Ce Type de dépense n'exixte pas");

        }


    }


    public TypeDepense modifier (Long id,TypeDepense typeDepense){
        return typeDepenseRepository.findById(id)
                .map(t-> {
                    t.setLibelle(t.getLibelle());
                    t.setDescription(t.getDescription());
                    return typeDepenseRepository.save(typeDepense);
                    }).orElseThrow(()->new NotFoundException("type de depense non trouvé"));
    }

    public List<TypeDepense> list(){
        List<TypeDepense> typeDepenseList= typeDepenseRepository.findAll();
        if(typeDepenseList.isEmpty())
            throw new NotFoundException("Cette liste est introuvable");
        return typeDepenseList;
    }


    public String supprimer(Long id, TypeDepense typeDepense){
        if(typeDepenseRepository.findById(id)!=null){
            typeDepenseRepository.save(typeDepense);
            return "suppression effectuée";
        }
        throw new NotFoundException("Suppression est impossible");
    }



}
