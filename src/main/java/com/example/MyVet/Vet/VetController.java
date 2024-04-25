package com.example.MyVet.Vet;

import com.example.MyVet.Assistant.Assistant;
import com.example.MyVet.Exceptions.EntityNotFoundException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(path = "/api/v1/vet")
public class VetController {
    private final VetService vetService;

    @Autowired
    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping
    public List<Vet> getVet() {
        return vetService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vet> getById(@PathVariable String id) {
        return vetService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Vet> update(@PathVariable String id, @Valid @RequestBody VetDTO vetDTO) {
//        Vet vets = vetService.update(id, vetDTO);
//        return ResponseEntity.ok(vets);
//    }
}