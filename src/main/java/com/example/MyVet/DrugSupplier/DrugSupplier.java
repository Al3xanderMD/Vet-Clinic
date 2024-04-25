package com.example.MyVet.DrugSupplier;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document (collection = "drug_supplier")
public class DrugSupplier {
    @Id
    private String id;
    private String name;

    public DrugSupplier(String name) {
         this.name = name;
    }
}
