package com.example.MyVet.Appointment;
import com.example.MyVet.Pet.Pet;
import com.example.MyVet.PetOwner.PetOwner;
import com.example.MyVet.Vet.Vet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "appointment")
public class Appointment {
    @Id
    String id;
    @DBRef
    Pet pet;
    @DBRef
    PetOwner petOwner;
    @DBRef
    Vet vet;

    int year;
    int month;
    int day;
    int hour;
    int minute;
    //Boolean isDone;
}
