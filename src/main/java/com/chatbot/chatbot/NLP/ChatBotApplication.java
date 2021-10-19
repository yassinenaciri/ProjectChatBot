package com.chatbot.chatbot.NLP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@EnableAuthorizationServer
public class ChatBotApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ChatBotApplication.class, args);
    }

}
