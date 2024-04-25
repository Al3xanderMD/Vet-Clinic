package com.example.MyVet.Pet;

import com.example.MyVet.Pet.MedicalHistory.MedicalHistory;
import com.example.MyVet.Pet.MedicalHistory.MedicalHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class PetMapper {

    private static MedicalHistoryService medicalHistoryService;

    @Autowired
    public  PetMapper (MedicalHistoryService medicalHistoryService){
        this.medicalHistoryService = medicalHistoryService;
    }

    public static Pet toPet(PetDto petDto) {
        Pet pet = new Pet();

        pet.setName(petDto.getName());
        pet.setRace(petDto.getRace());
        pet.setSpecies(petDto.getSpecies());
        pet.setUIN(petDto.getUIN());
        pet.setImageURL(petDto.getImageURL());
        pet.setHasPassport(petDto.getHasPassport());
        pet.setHealthStatus(petDto.getHealthStatus());

        var lista = new ArrayList<MedicalHistory>();

        for(var id: petDto.getMedicalHistories()){
            var medHistList = medicalHistoryService.getById(id);
            medHistList.ifPresent(histList -> lista.add(histList));
        }
        pet.setMedicalHistoryList(lista);

        return pet;
    }
}
