package com.example.MyVet.Pet;

import com.example.MyVet.PetOwner.PetOwner;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetDto {
    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Species cannot be null")
    private String species;
    private String race;
    private int UIN;
    private String imageURL;
    private Boolean hasPassport;
    private String healthStatus;
    private List<String> medicalHistories;
}
