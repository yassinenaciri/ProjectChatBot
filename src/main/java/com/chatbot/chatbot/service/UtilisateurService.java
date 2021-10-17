package com.chatbot.chatbot.service;


import com.chatbot.chatbot.model.Utilisateur;
import com.chatbot.chatbot.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {
    private final UtilisateurRepository userRepository;
    public UtilisateurService(UtilisateurRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser( ){
        userRepository.save(new Utilisateur());
    }
}
