package com.groupe2_API.tp_gestion_budget.service;

import com.groupe2_API.tp_gestion_budget.model.User;
import com.groupe2_API.tp_gestion_budget.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User creerUser(User user){
        if(userRepository.findByEmail(user.getEmail()) == null) {
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    public User connexionUser(String email , String motDePasse) {
        if (userRepository.findByEmailAndMotDePasse(email, motDePasse) != null) {
            return userRepository.findByEmailAndMotDePasse(email, motDePasse);
        } else {
            return null;
        }
    }

    public List<User> userList() {
        if (userRepository.findAll() != null) {
            return userRepository.findAll();
        } else {
            return null;
        }
    }

    public User modifierUser(User user) {
        if (userRepository.findByIdUser(user.getIdUser()) != null ) {
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    public String suppressionUser(User user) {
        if (userRepository.findByIdUser(user.getIdUser()) != null) {
            userRepository.delete(user);
            return "Succès";
        } else {
            return "supprimer avec succès";
        }
    }

}
