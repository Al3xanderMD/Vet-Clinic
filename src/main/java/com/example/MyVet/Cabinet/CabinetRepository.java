package com.example.MyVet.Cabinet;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabinetRepository  extends MongoRepository<Cabinet, String>{
}
