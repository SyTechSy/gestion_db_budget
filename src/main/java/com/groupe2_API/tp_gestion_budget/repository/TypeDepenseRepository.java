package com.groupe2_API.tp_gestion_budget.repository;


import com.groupe2_API.tp_gestion_budget.model.TypeDepense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeDepenseRepository extends JpaRepository<TypeDepense,Long> {

    public TypeDepense findByLibelle(String typeDepense);
}
