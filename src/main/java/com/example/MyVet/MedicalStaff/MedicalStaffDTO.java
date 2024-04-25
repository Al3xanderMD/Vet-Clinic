package com.example.MyVet.MedicalStaff;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Pair;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalStaffDTO {
    @NotBlank
    int salary;

    @NotBlank
    int experience;

    @NotBlank
    String cabinetId;
}
