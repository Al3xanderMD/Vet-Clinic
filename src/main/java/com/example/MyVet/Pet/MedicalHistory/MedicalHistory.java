package com.example.MyVet.Pet.MedicalHistory;

import com.example.MyVet.Drug.Drug;
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
@Document(collection = "medical_history")
public class MedicalHistory {
    @Id
    private String id;

    private String diagnostics;

    @DBRef
    private List<Drug> drugList;
    private LocalDateTime date = LocalDateTime.now();

    public MedicalHistory(String diagnostics, LocalDateTime date) {
        this.diagnostics = diagnostics;
        this.date = date;
    }
}
