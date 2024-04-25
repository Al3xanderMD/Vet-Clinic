package com.example.MyVet.Drug;

import com.example.MyVet.DrugSupplier.DrugSupplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "drug")
public class Drug {
    private String imageURL;
    @Id
    private String id;
    private String name;

    @DBRef
    private List<DrugSupplier> drugSuppliersList;

    private Boolean reqPrescription;

    private double quantity;
    private double priceUnit;

    public Drug(String imageURL, String name, DrugSupplier drugSupplier, Boolean reqPrescription, double quantity,double priceUnit)  {
        this.imageURL = imageURL;
        this.name = name;
        this.drugSuppliersList = new ArrayList<>();
        this.drugSuppliersList.add(drugSupplier);
        this.reqPrescription = reqPrescription;
        this.quantity = quantity;
        this.priceUnit = priceUnit;
    }
}