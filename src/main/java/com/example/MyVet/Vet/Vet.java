package com.example.MyVet.Vet;

import com.example.MyVet.Cabinet.Cabinet;
import com.example.MyVet.MedicalStaff.MedicalStaff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "vet")
public class Vet {
    @Id
    String id;

    @DBRef
    private MedicalStaff medicalStaff;
    public Vet(MedicalStaff medicalStaff) {
        this.medicalStaff = medicalStaff;
    }
}
