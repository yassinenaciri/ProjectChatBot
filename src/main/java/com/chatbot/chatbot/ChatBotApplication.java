package com.chatbot.chatbot;

import com.chatbot.chatbot.service.UtilisateurService;
import net.sf.hfst.NoTokenizationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.io.IOException;

@SpringBootApplication
@EnableResourceServer
@EnableAuthorizationServer
public class ChatBotApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ChatBotApplication.class, args);
    }

}
