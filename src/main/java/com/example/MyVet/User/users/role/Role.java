package com.example.MyVet.User.users.role;

import jakarta.persistence.*;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Document(collection = "roles")
public class Role implements Serializable {
    public enum ERole {
        ROLE_PetOwner,
        ROLE_VET,
        ROLE_ADMIN,
        ROLE_USER,
        ROLE_Assistant
    }

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private ERole name;

    public Role(ERole name) {
        this.name = name;
    }
}