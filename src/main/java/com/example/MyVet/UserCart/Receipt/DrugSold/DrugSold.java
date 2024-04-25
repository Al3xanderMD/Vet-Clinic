package com.example.MyVet.UserCart.Receipt.DrugSold;

import com.example.MyVet.Drug.Drug;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection =  "drugs_sold")
public class DrugSold {
    @Id
    private String id;

    @DBRef
    private Drug drug;

    private double quantitySold;

    private double price;



    public DrugSold(Drug drug, double quantitySold) {
        this.drug = drug;
        this.quantitySold = quantitySold;
    }
}
