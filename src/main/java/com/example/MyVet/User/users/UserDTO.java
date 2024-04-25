package com.example.MyVet.User.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotBlank
    String firstName;
    @NotBlank
    String lastName;
    @NotBlank
    String phone;
    @NotBlank
    List<String> adresses = new ArrayList<>();
}
