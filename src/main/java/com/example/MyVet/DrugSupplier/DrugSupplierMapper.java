package com.example.MyVet.DrugSupplier;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DrugSupplierMapper {
    public static DrugSupplier toDrugSupplier(DrugSupplierDTO drugSupplierDTO) {
        DrugSupplier drugSupplier = new DrugSupplier(drugSupplierDTO.getName());
        return drugSupplier;
    }
}
