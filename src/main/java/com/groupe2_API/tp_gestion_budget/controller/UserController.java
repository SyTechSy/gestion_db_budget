package com.groupe2_API.tp_gestion_budget.controller;

import com.groupe2_API.tp_gestion_budget.model.User;
import com.groupe2_API.tp_gestion_budget.service.UserService;
import jakarta.validation.Valid;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired

    UserService userService;

    @PostMapping("/ajouter")
    public ResponseEntity<Object> ajouterUser(@Valid @RequestBody User user) {
        User verificationUser = userService.creerUser(user);
        if (verificationUser != null){
            return new ResponseEntity<>("Inscription fait avec Succès", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Compte existe déja", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/list")
    public List<User> listUser() {
        return userService.listUser();
    }

    @PostMapping("/connexion")
    public ResponseEntity<Object> connexion(@RequestParam("email") String email,
                                            @RequestParam("motDePasse") String motDePasse) {
        User verificationUser = userService.connexionUser(email, motDePasse);
        if (verificationUser != null) {
            return new ResponseEntity<>("Connection avec Succès", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User n'existe pas", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/modifier")
    public ResponseEntity<Object> modificationUser(@Valid @RequestBody User user) {
        User verificationUser = userService.modifierUser(user);
        if (verificationUser != null) {
            return new ResponseEntity<>("Modification fait avec Succès", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Modification echouer", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/supprimer")
    public ResponseEntity<String> supprimer(@Valid @RequestBody User user) {
        String message = userService.suppressionUser(user);
        if (message.equals("Succès")) {
            return new ResponseEntity<>("Suppression avec Succès", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User n'existe pas", HttpStatus.NOT_FOUND);
        }
    }

}
