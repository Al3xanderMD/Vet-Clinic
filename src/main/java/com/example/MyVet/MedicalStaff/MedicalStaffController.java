package com.example.MyVet.MedicalStaff;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(path = "/api/v1/medicalStaff")
public class MedicalStaffController {
    private final MedicalStaffService medicalStaffService;

    @Autowired
    public MedicalStaffController(MedicalStaffService medicalStaffService) {
        this.medicalStaffService = medicalStaffService;
    }

    @GetMapping
    public List<MedicalStaff> getMedicalStaff() {
        return medicalStaffService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MedicalStaff getById(@PathVariable String id) {
        return medicalStaffService.getById(id).get();
    }

    @GetMapping("/schedule/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<WeekDay, Pair<Integer, Integer>> getSchedule(@PathVariable String id) {
        return medicalStaffService.getById(id).get().getSchedule();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<MedicalStaff> update(@PathVariable String id, @Valid @RequestBody MedicalStaffDTO medicalStaffDTO) {
        MedicalStaff medicalStaffs = medicalStaffService.update(id , medicalStaffDTO);
        return ResponseEntity.ok(medicalStaffs);
    }
}