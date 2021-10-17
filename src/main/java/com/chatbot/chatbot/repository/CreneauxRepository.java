package com.chatbot.chatbot.repository;

import com.chatbot.chatbot.model.Creneaux;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreneauxRepository extends MongoRepository<Creneaux, String> {}