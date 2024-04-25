package com.example.MyVet.Assistant;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssistantRepository extends MongoRepository<Assistant, String> {
}