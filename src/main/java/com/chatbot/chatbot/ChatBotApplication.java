package com.chatbot.chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ChatBotApplication {

    public static void main(String[] args) throws IOException {
        Tokenizer token=new Tokenizer();
        token.partsOfSpeech("I want to kill you");
        token.categoriser();

        SpringApplication.run(ChatBotApplication.class, args);
    }

}
