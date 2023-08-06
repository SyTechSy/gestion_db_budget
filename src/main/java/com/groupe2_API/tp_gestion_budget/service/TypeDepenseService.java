package com.groupe2_API.tp_gestion_budget.service;


import com.groupe2_API.tp_gestion_budget.model.TypeDepense;
import com.groupe2_API.tp_gestion_budget.repository.TypeDepenseRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class TypeDepenseService {

    public final TypeDepenseRepository typeDepenseRepository;


    public TypeDepense creer(TypeDepense typeDepense) {

        return typeDepenseRepository.save(typeDepense);
    }


    public TypeDepense modifier (Long id,TypeDepense typeDepense){
        return typeDepenseRepository.findById(id)
                .map(t-> {
                    t.setLibelle(t.getLibelle());
                    t.setDescription(t.getDescription());
                    return typeDepenseRepository.save(typeDepense);
                    }).orElseThrow(()->new RuntimeException("type de depense non trouvé"));
    }
}
