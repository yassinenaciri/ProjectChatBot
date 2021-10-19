package com.chatbot.chatbot.service;


import com.chatbot.chatbot.model.Utilisateur;
import com.chatbot.chatbot.repository.UtilisateurRepository;
import com.chatbot.chatbot.security.Userhelper;
import jdk.jshell.execution.Util;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService implements UserDetailsService {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Utilisateur user = null;
        try {
            user = utilisateurRepository.findUtilisateurByEmail(s);
            Userhelper userDetail = new Userhelper(user);
            return userDetail;
        } catch (Exception e) {
            throw new UsernameNotFoundException(" User not found : "+s);
        }
    }
}
