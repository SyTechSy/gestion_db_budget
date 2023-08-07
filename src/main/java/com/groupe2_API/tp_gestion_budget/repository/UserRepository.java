package com.groupe2_API.tp_gestion_budget.repository;

import com.groupe2_API.tp_gestion_budget.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,  Integer> {

    public User findByEmail(String user);

    public User findByEmailAndMotDePasse(String email, String motDePasse);

    public  User findByIdUser(int id);
}
