package com.example.MyVet.PetOwner;

import com.example.MyVet.Exceptions.EntityNotFoundException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("api/v1/petOwners")
public class PetOwnerController {
    private final PetOwnerService petOwnerService;

    @Autowired
    public PetOwnerController(PetOwnerService petOwnerService) {
        this.petOwnerService = petOwnerService;
    }
    @GetMapping
    public List<PetOwner> getAll(){ return petOwnerService.getAll();}

    @GetMapping("/{id}")
    public ResponseEntity<PetOwner> getByID(@PathVariable("id") String id){
        Optional<PetOwner> pt = petOwnerService.getById(id);
        return pt.map(petOwner -> new ResponseEntity<>(petOwner, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<PetOwner> update(@PathVariable("id") String id, @RequestBody PetOwnerDTO petOwnerDTO) {
        PetOwner petOwners = null;
        try {
            petOwners = petOwnerService.update(id, petOwnerDTO);
        } catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(petOwners, HttpStatus.OK);
    }
}
