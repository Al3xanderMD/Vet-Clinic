package com.example.MyVet.PetOwner;

import com.example.MyVet.Pet.Pet;
import com.example.MyVet.Pet.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class PetOwnerMapper {
    private static PetService petService;

    @Autowired
    private PetOwnerMapper(PetService petService){this.petService = petService;}

    public static PetOwner toPetOwner(PetOwnerDTO petOwnerDTO){
        PetOwner petOwner = new PetOwner();


        var lista = new ArrayList<Pet>();

        for (var id: petOwnerDTO.getPetsId())
        {
            var pets = petService.getById(id);
            pets.ifPresent(pet -> lista.add(pet));
        }
        petOwner.setPetList(lista);

        return petOwner;
    }
}
