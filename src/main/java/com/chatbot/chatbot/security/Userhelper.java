package com.chatbot.chatbot.security;

import com.chatbot.chatbot.model.Utilisateur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class Userhelper extends User {
    private static long serialVersionUID =1L;
    public Userhelper(Utilisateur utilisateur) {
        super(utilisateur.getEmail(), utilisateur.getPassword(), utilisateur.getAuthorities());
    }
}
