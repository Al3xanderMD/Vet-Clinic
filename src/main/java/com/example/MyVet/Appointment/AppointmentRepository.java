package com.example.MyVet.Appointment;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;
@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {
    @Query("{ 'year': ?0, 'month': ?1, 'day': ?2, 'hour': ?3, 'minute': ?4}")
    Optional<Appointment> findByMinute(int year, int month, int day, int hour, int minute);
    @Query("{ 'year': ?0, 'month': ?1, 'day': ?2}")
    List<Appointment> findByDay(int year, int month, int day);
}
