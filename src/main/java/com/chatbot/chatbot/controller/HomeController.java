package com.chatbot.chatbot.controller;


import com.chatbot.chatbot.model.Utilisateur;
import com.chatbot.chatbot.repository.UtilisateurRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {
    private final UtilisateurRepository utilisateurRepository;

    public HomeController(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @PostMapping("/users")
    public void createUser() {
        utilisateurRepository.save(new Utilisateur());

    }
}
