package com.example.MyVet.Pet;

import com.example.MyVet.Exceptions.EntityNotFoundException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("api/v1/pets")
public class PetController {
    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping()
    public List<Pet> getAll(){  return petService.getAll();}

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getByID(@PathVariable("id") String id){
        Optional<Pet> pet1 = petService.getById(id);
        return pet1.map(pet -> new ResponseEntity<>(pet, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pet> create(@RequestBody @Valid PetDto petDto) {
        Pet savedPet = petService.create(petDto);
        return new ResponseEntity<>(savedPet, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Pet> update(@PathVariable("id") String id, @RequestBody @Valid PetDto petDto) {
        Pet updatedPet = null;
        try {
            updatedPet = petService.update(id, petDto);
        } catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedPet, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        petService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}