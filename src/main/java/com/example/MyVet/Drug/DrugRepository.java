package com.example.MyVet.Drug;

//import com.example.MyVet.Cabinet.Cabinet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugRepository extends MongoRepository<Drug, String> {
}
