package com.example.MyVet.Cabinet;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(path = "/api/v1/cabinet")
public class CabinetController {
   private final CabinetService cabinetService;

    @Autowired
    public CabinetController(CabinetService cabinetService) {
        this.cabinetService = cabinetService;
    }


    @GetMapping
    public List<Cabinet> getAll() {
        return cabinetService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Cabinet> add(@Valid @RequestBody CabinetDTO cabinetDTO) {
        Cabinet newCabinet = cabinetService.create(cabinetDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCabinet);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cabinet> getById(@PathVariable String id) {
        return cabinetService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Cabinet> update(@PathVariable String id, @Valid @RequestBody CabinetDTO cabinetDTO) {
        Cabinet cabinet = cabinetService.update(id, cabinetDTO);
        return ResponseEntity.ok(cabinet);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Cabinet> deleteById(@PathVariable String id) {
        cabinetService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}