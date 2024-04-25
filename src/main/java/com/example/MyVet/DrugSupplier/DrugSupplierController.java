package com.example.MyVet.DrugSupplier;

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
@RequestMapping(path = "/api/v1/drugSuppliers")
public class DrugSupplierController {
    private final DrugSupplierService drugSupplierService;

    @Autowired
    public DrugSupplierController(DrugSupplierService drugSupplierService) {
        this.drugSupplierService = drugSupplierService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DrugSupplier> create(@RequestBody DrugSupplierDTO drugSupplierDTO) {
        DrugSupplier savedDrugSupplier = drugSupplierService.create(drugSupplierDTO);
        return new ResponseEntity<>(savedDrugSupplier, HttpStatus.CREATED);
    }

    @GetMapping
    public List<DrugSupplier> getAllDrugSuppliers(){ return drugSupplierService.getAllDrugSuppliers();}

    @GetMapping("/{id}")
    public ResponseEntity<DrugSupplier> getDrugSuplierByID(@PathVariable("id") String id){
        Optional<DrugSupplier> ds = drugSupplierService.getDrugSupplierById(id);
        return ds.map(drugSupplier -> new ResponseEntity<>(drugSupplier, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<DrugSupplier> updateDrugSupplier(@PathVariable("id") String id, @RequestBody DrugSupplierDTO drugSupplier) {
        DrugSupplier ds = null;
        try {
            ds = drugSupplierService.updateDrugSupplier(id, drugSupplier);
        } catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(new DrugSupplier(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ds, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteDrugSupplierById(@PathVariable("id") String id) {
        drugSupplierService.deleteDrugSupplierById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}