package com.example.MyVet.DrugSupplier;

import com.example.MyVet.DrugStock.DrugStock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugSupplierRepository extends MongoRepository<DrugSupplier, String> {
}
