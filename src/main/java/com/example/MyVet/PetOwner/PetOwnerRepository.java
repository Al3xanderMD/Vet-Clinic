package com.example.MyVet.PetOwner;

import com.example.MyVet.Pet.Pet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetOwnerRepository extends MongoRepository<PetOwner, String> {
}
