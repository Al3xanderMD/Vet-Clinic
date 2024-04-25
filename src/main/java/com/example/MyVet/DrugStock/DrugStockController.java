package com.example.MyVet.DrugStock;
import com.example.MyVet.Drug.Drug;
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
@RequestMapping(path = "/api/v1/DrugStocks")
public class DrugStockController {
    private final DrugStockService DrugStockService;
    @Autowired
    public DrugStockController(DrugStockService DrugStockService) {
        this.DrugStockService = DrugStockService;
    }

    @GetMapping
    public List<DrugStock> getAll(){
        return DrugStockService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DrugStock> create(@Valid @RequestBody DrugStockDTO drugStockDTO) {
        DrugStock drugStockNew = DrugStockService.create(drugStockDTO);
        return new ResponseEntity<>(drugStockNew, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrugStock> getById(@PathVariable("id") String id) {

        Optional<DrugStock>  ds = DrugStockService.getById(id);
        return ds.map(drugStock -> new ResponseEntity<>(drugStock, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<DrugStock> update(@PathVariable("id") String id,  @RequestBody @Valid DrugStockDTO drugStockDTO) {

        DrugStock  updatedDrugStock = null;
        try {
            updatedDrugStock = DrugStockService.update(id, drugStockDTO);
        } catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedDrugStock, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<DrugStock> deleteById(@PathVariable("id") String id) {

        DrugStockService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}