package com.example.MyVet.PetOwner;

import com.example.MyVet.Pet.Pet;

import com.example.MyVet.User.users.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document (collection =  "pet_owner")
public class PetOwner{
    @Id
    private String id;

    @DBRef
    private List<Pet> petList;
    @DBRef
    private User user;

    public PetOwner(User user) {
        this.user = user;
    }
}
