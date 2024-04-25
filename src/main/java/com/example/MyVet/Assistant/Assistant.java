package com.example.MyVet.Assistant;

import com.example.MyVet.MedicalStaff.MedicalStaff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "assistant")
public class Assistant  {
    @Id
    private String id;

    @DBRef
    private MedicalStaff medicalStaff;

    public Assistant(MedicalStaff medicalStaff) {
        this.medicalStaff = medicalStaff;
    }
}
