package com.example.MyVet.Pet.MedicalHistory;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalHistoryDTO {
    @NotBlank
    String diagnostics;

    List<String> drugsList = new ArrayList<>();
}
