package com.example.MyVet.Consumption;

import com.example.MyVet.Appointment.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsumptionRepository extends MongoRepository<Consumption, String> {
    @Query("{'year': ?0, 'drugId': ?1}")
    List<Consumption> findByYear(int year, String drugId);
    @Query("{'drugId': ?0}")
    List<Consumption> findByDrug(String drugId);
}
