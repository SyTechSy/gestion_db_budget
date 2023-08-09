package com.groupe2_API.tp_gestion_budget.service;

import com.groupe2_API.tp_gestion_budget.exception.NoContentException;
<<<<<<< HEAD
import com.groupe2_API.tp_gestion_budget.model.Budget;
=======
import com.groupe2_API.tp_gestion_budget.exception.NotFoundException;
>>>>>>> 61d1113f4644fdcbf1f43e547fdba4d2b8d3c6ca
import com.groupe2_API.tp_gestion_budget.model.User;
import com.groupe2_API.tp_gestion_budget.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BudgetService budgetService;

    public User creerUser(User user){
        if(userRepository.findByEmail(user.getEmail()) == null) {
            return userRepository.save(user);
        } else {
            throw new NoContentException("On peut pas creer un autre categorie qui existé déjà!");
        }
    }

    public User connexionUser(String email , String motDePasse) {
        if (userRepository.findByEmailAndMotDePasse(email, motDePasse) != null) {
            return userRepository.findByEmailAndMotDePasse(email, motDePasse);
        } else {
            throw new NotFoundException("Utilisateur n'existe Deja");
        }
    }


    public List<User> listUser(){
        if (!userRepository.findAll().isEmpty())
            return userRepository.findAll();
        else
            throw new NoContentException("Aucun User n'a été trouver");
    }



    public User modifierUser(User user) {
        if (userRepository.findByIdUser(user.getIdUser()) != null ) {
            return userRepository.save(user);
        } else {
            throw new NotFoundException("On peut modifier quelque chose qui n'existe pas !");
        }
    }

    public String suppressionUser(User user) {
        if (userRepository.findByIdUser(user.getIdUser()) != null) {
            userRepository.delete(user);
            return "Succès";
        } else {
            throw new NotFoundException("On peut supprimer quelque chose qui n'existe pas !");
        }
    }
    //Methode permettant à utilisateur d'ajouter des budgets

    public User ajouterBudget(User user, Budget budget){
        user.getBudgets().add(budget);
        budget.setUser(user);
        return userRepository.save(user);
    }

}
