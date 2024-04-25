package com.example.MyVet.Consumption;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumptionDTO {
    @NotBlank
    String  drugId;

    @NotBlank
    int year;

    @NotBlank
    int month;

    @NotBlank
    double quantitySold;
}
