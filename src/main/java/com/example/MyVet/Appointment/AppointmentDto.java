package com.example.MyVet.Appointment;

import com.example.MyVet.Pet.Pet;
import com.example.MyVet.PetOwner.PetOwner;
import com.example.MyVet.Vet.Vet;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {
    @NotNull(message = "Pet id cannot be null")
    private String petId;

    @NotNull(message = "PetOwner cannot be null")
    private String ownerId;

    @NotNull(message = "Doctor cannot be null")
    private String VetId;

    @NotNull(message="Year cannot be null")
    private int year;
    @NotNull(message="Month cannot be null")
    private int month;
    @NotNull(message="Day cannot be null")
    private int day;
    @NotNull(message="Hour cannot be null")
    private int hour;
    @NotNull(message="Minute cannot be null")
    private int minute;
}