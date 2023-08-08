package com.groupe2_API.tp_gestion_budget.controller;

import com.groupe2_API.tp_gestion_budget.model.Budget;
import com.groupe2_API.tp_gestion_budget.model.User;
import com.groupe2_API.tp_gestion_budget.repository.UserRepository;
import com.groupe2_API.tp_gestion_budget.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired

    UserService userService;
    @Autowired
    UserRepository userRepository;


    @PostMapping("/ajouter")
    public ResponseEntity<Object> ajouterUser(@RequestBody User user) {
        User verificationUser = userService.creerUser(user);
        if (verificationUser != null){
            return new ResponseEntity<>("Inscription fait avec Succès", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Compte existe déja", HttpStatus.NOT_FOUND);
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
    public ResponseEntity<Object> modificationUser(@RequestBody User user) {
        User verificationUser = userService.modifierUser(user);
        if (verificationUser != null) {
            return new ResponseEntity<>("Modification fait avec Succès", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Modification echouer", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/supprimer")
    public ResponseEntity<String> supprimer(@RequestBody User user) {
        String message = userService.suppressionUser(user);
        if (message.equals("Succès")) {
            return new ResponseEntity<>("Suppression avec Succès", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User n'existe pas", HttpStatus.NOT_FOUND);
        }
    }

    //Ajout du budget
  /*  @PostMapping("/{idUser}/addBudget")
    public ResponseEntity<Object> ajouterBudgets(@PathVariable int idUser, @RequestBody  Budget budget){
       Optional<User> optionalUser ;
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            User updateUser = userService.ajouterBudget(user, budget);

            return new  ResponseEntity<>("Budget ajouté avec succèss",HttpStatus.OK);
        } else{
            return new ResponseEntity<>("Utilisateur non trouvé", HttpStatus.NOT_FOUND);
        }
    }*/

}
