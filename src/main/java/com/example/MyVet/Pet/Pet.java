package com.example.MyVet.Pet;

import com.example.MyVet.Pet.MedicalHistory.MedicalHistory;
import com.example.MyVet.PetOwner.PetOwner;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Document (collection = "pet")
public class Pet {
    @Id
    private String id;
    private String imageURL;
    private Boolean hasPassport;
    private String name;
    private String species;
    private String race;
    private int UIN;
    private String healthStatus;

    @DBRef
    private List<MedicalHistory> medicalHistoryList;

    private boolean enabled = true;

    public Pet(String imageURL, Boolean hasPassport, String name, String species, String race, int UIN, String healthStatus) {
        this.imageURL = imageURL;
        this.hasPassport = hasPassport;
        this.name = name;
        this.species = species;
        this.race = race;
        this.UIN = UIN;
        this.healthStatus = healthStatus;
    }


}
