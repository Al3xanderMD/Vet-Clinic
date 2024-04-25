package com.example.MyVet.Consumption;
import com.example.MyVet.Cabinet.Cabinet;
import com.example.MyVet.Drug.Drug;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document (collection = "consumption")
@NoArgsConstructor
@AllArgsConstructor
public class Consumption {
    @Id
    private String id;

    @DBRef
    private Drug drugId;

    private int year;

    private int month;

    private double quantitySold;

    public boolean matchesYear(String drugId, int year) {
        return this.drugId.getId().equals(drugId)
                && this.year == year;
    }

    public boolean matchesMonth(String drugId, int year, int month) {
        return this.drugId.getId().equals(drugId)
                && this.year == year
                && this.month == month;
    }
}
