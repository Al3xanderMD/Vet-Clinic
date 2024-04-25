package com.example.MyVet.Vet;

import com.example.MyVet.Pet.Pet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VetRepository extends MongoRepository<Vet, String> {

}
