package com.example.MyVet.DrugStock;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugStockRepository extends MongoRepository<DrugStock, String> {
}
