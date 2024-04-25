package com.example.MyVet.Pet.MedicalHistory;

import com.example.MyVet.DrugSupplier.DrugSupplier;
import com.example.MyVet.DrugSupplier.DrugSupplierDTO;
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
@RequestMapping(path = "/api/v1/medicalHistories")
public class MedicalHistoryController {
    private final MedicalHistoryService medicalHistoryService;

    @Autowired
    public MedicalHistoryController(MedicalHistoryService medicalHistoryService) {
        this.medicalHistoryService = medicalHistoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MedicalHistory> create(@RequestBody MedicalHistoryDTO medicalHistoryDTO) {
        MedicalHistory savedMedicalHistory = medicalHistoryService.create(medicalHistoryDTO);
        return new ResponseEntity<>(savedMedicalHistory, HttpStatus.CREATED);
    }

    @GetMapping
    public List<MedicalHistory> getAll(){ return medicalHistoryService.getAll();}

    @GetMapping("/{id}")
    public ResponseEntity<MedicalHistory> getById(@PathVariable("id") String id) {
        Optional<MedicalHistory> medicalHistory = medicalHistoryService.getById(id);
        return medicalHistory.map(medicalHistory1 -> new ResponseEntity<>(medicalHistory1, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<MedicalHistory> update(@PathVariable("id") String id, @RequestBody MedicalHistoryDTO medicalHistoryDTO) {
        MedicalHistory medicalHistory = null;
        try {
            medicalHistory = medicalHistoryService.update(id, medicalHistoryDTO);
        } catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(new MedicalHistory(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(medicalHistory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        medicalHistoryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
