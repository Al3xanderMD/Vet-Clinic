package com.example.MyVet.Pet;

import com.example.MyVet.Exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;
    private PetMapper petMapper = new PetMapper();

    public Pet create(PetDto petDto) {
        return petRepository.save(petMapper.toPet(petDto));
    }

    public List<Pet> getAll() {
        return petRepository.findAll();
    }

    public Optional<Pet> getById(String id) {
        return petRepository.findById(id);
    }

    public Pet update(String id, PetDto petDto) {
        Optional<Pet> existingPet = petRepository.findById(id);

        if (existingPet.isPresent()) {
            var newPet = PetMapper.toPet(petDto);
            newPet.setId(existingPet.get().getId());
            return petRepository.save(newPet);
        } else {
            throw new EntityNotFoundException(id);
        }
    }

    public Pet deleteById(String id) {
        Optional<Pet> existingPet = petRepository.findById(id);
        if (existingPet.isPresent()) {
            Pet updatedPet = existingPet.get();
            updatedPet.setEnabled(false);

            return petRepository.save(updatedPet);
        } else {
            throw new EntityNotFoundException(id);
        }
    }
}