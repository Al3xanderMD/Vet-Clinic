package com.example.MyVet.UserCart.Receipt.DrugSold;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugSoldRepository extends MongoRepository<DrugSold, String> {
}
