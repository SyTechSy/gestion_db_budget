package com.groupe2_API.tp_gestion_budget.controller;
import com.groupe2_API.tp_gestion_budget.model.User;
import com.groupe2_API.tp_gestion_budget.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/ajouter")
    public ResponseEntity<Object> ajouterUser(@RequestBody User user) {
        User verificationUser = userService.creerUser(user);
        if (verificationUser != null){
            return new ResponseEntity<>("Inscription fait avec Succès", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Compte existe déja", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/modifier")
    public ResponseEntity<Object> modificationUser(@RequestBody User user) {
        User verificationUser = userService.modifierUser(user);
        if (verificationUser != null) {
            return new ResponseEntity<>("Modification fait avec Succès", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Modification echouer", HttpStatus.NOT_FOUND);
        }
    }

}
