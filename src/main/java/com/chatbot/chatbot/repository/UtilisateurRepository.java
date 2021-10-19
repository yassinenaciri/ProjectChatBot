package com.chatbot.chatbot.repository;

import com.chatbot.chatbot.model.Utilisateur;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends MongoRepository<Utilisateur, String> {
    Utilisateur findUtilisateurByEmail(String email);

}