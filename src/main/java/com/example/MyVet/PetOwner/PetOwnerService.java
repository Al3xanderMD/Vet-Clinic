package com.example.MyVet.PetOwner;

import com.example.MyVet.Exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetOwnerService {

    private final PetOwnerMapper petOwnerMapper = new PetOwnerMapper();
    @Autowired
    private PetOwnerRepository petOwnerRepository;

    public PetOwner add(PetOwner petOwner){
        return  petOwnerRepository.save(petOwner);
    }

    public List<PetOwner> getAll(){
        return petOwnerRepository.findAll();
    }

    public Optional<PetOwner> getById(String id)
    {
        return petOwnerRepository.findById(id);
    }

    public PetOwner update(String id, PetOwnerDTO petOwnerDTO) {
        Optional<PetOwner> existingPetOwner = petOwnerRepository.findById(id);

        if (existingPetOwner.isPresent()) {
            var newPetOwner = PetOwnerMapper.toPetOwner(petOwnerDTO);
            newPetOwner.setUser(existingPetOwner.get().getUser());
            newPetOwner.setId(existingPetOwner.get().getId());

            return petOwnerRepository.save(newPetOwner);
        } else {
            throw new EntityNotFoundException(id);
        }
    }
}
