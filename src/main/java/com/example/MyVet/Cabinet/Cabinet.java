package com.example.MyVet.Cabinet;

import com.example.MyVet.DrugStock.DrugStock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document (collection = "cabinet")
public class Cabinet {
    @Id
    private String id;
    private String name;

    @DBRef
    private DrugStock drugStock;
}

