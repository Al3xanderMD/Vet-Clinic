package com.example.MyVet.Drug;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrugDTO {
    @NotNull(message = "name cannot be null")
    String name;

    @NotNull(message = "DrugSupplierListId cannot be null")
    List<String> drugSuppliersList=new ArrayList<>();
    Boolean reqPrescription;
    String imageURL;

    @NotBlank
    double quantity;
    @NotBlank
    double priceUnit;
}
