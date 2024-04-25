package com.example.MyVet.Drug;

import com.example.MyVet.DrugStock.DrugStock;
import com.example.MyVet.DrugStock.DrugStockDTO;
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
@RequestMapping(path = "/api/v1/drugs")
public class DrugController {

    private final DrugService drugService;

    @Autowired
    public DrugController(DrugService drugService) {
        this.drugService = drugService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Drug> create(@Valid @RequestBody DrugDTO drugDTO) {
        Drug drugNew =  drugService.create(drugDTO);
        return new ResponseEntity<>(drugNew, HttpStatus.CREATED);
    }
    @GetMapping
    public List<Drug> getAll() {return  drugService.getAll();}

    @GetMapping("/{id}")
    public ResponseEntity<Drug> getById(@PathVariable("id") String id) {

        Optional<Drug>  ds =  drugService.getById(id);
        return ds.map(drug-> new ResponseEntity<>(drug, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Drug> updateById(@PathVariable("id") String id, @RequestBody @Valid DrugDTO drugDTO) {
        Drug updatedDrug = null;
        try {
            updatedDrug = drugService.updateById(id, drugDTO);
        } catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedDrug, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        drugService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
