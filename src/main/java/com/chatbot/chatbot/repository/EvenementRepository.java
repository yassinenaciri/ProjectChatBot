package com.chatbot.chatbot.repository;

import com.chatbot.chatbot.model.Evenement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementRepository extends MongoRepository<Evenement, String> {}
