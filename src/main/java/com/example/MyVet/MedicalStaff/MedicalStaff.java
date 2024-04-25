package com.example.MyVet.MedicalStaff;

import com.example.MyVet.Cabinet.Cabinet;
import com.example.MyVet.User.users.User;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.util.Pair;
import spock.util.mop.Use;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document (collection = "medical_staff")
public class MedicalStaff {
    @Id
    private String id;
    //private String ; de facut un map de map
    private Map<WeekDay, Pair<Integer, Integer>> schedule;
    private int salary;
    private int experience;
    @DBRef
    private User user;

    @DBRef
    private Cabinet cabinet;

    public MedicalStaff(User user) {
        this.user = user;
    }

}
