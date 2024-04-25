package com.example.MyVet.UserCart.Receipt.DrugSold;

import com.example.MyVet.Exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/DrugsSold")
public class DrugSoldController {
    private final DrugSoldService drugSoldService;

    @Autowired
    public DrugSoldController(DrugSoldService drugSoldService) {
        this.drugSoldService = drugSoldService;
    }

    @GetMapping
    public List<DrugSold> getAll(){
        return drugSoldService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DrugSold> create(@RequestBody DrugSoldDTO drugSoldDTO) throws Exception {
        DrugSold savedDrugSold = drugSoldService.create(drugSoldDTO);
        return new ResponseEntity<>(savedDrugSold, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrugSold> getById(@PathVariable("id") String id) {
        Optional<DrugSold> ds = drugSoldService.getById(id);
        return ds.map(drugSold -> new ResponseEntity<>(drugSold, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<DrugSold> update(@PathVariable("id") String id, @RequestBody DrugSoldDTO drugSoldDTO) {
        DrugSold drugSold = null;
        try {
            drugSold = drugSoldService.update(id, drugSoldDTO);
        } catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(drugSold, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        drugSoldService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
