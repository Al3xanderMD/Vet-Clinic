package com.example.MyVet.DrugStock;

import com.example.MyVet.Drug.Drug;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document (collection = "drug_stock")
public class DrugStock {
    @Id
    private String id;

    @DocumentReference
    ArrayList<Drug> drugList=new ArrayList<>();
}