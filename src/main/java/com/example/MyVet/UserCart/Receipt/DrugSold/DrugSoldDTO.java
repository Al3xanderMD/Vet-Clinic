package com.example.MyVet.UserCart.Receipt.DrugSold;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrugSoldDTO {
    @NotBlank
    String drugId;
    @NotBlank
    double quantity;
}
