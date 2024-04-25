package com.example.MyVet.UserCart.Receipt;

import com.example.MyVet.UserCart.Receipt.DrugSold.DrugSold;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection =  "receipt")
public class Receipt {
    @Id
    private String id;

    @DBRef
    private List<DrugSold> drugSoldList;

    private double priceReceipt;

    private LocalDateTime date = LocalDateTime.now();
}
