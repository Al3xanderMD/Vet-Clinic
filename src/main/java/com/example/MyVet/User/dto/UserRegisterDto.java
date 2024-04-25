package com.example.MyVet.User.dto;


import jakarta.validation.constraints.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {

    @NotBlank
    @Pattern(regexp="^[A-Za-zÀ-ÖØ-öø-ÿ]+([ '-][A-Za-zÀ-ÖØ-öø-ÿ]+)$",
            message="Invalid Firstname")
    @Size(min = 2, max = 40, message = "Firstname should have maximum 40 characters")
    private String firstName;

    @NotBlank
    @Pattern(regexp="^[A-Za-zÀ-ÖØ-öø-ÿ]+([ '-][A-Za-zÀ-ÖØ-öø-ÿ]+)$",
            message="Invalid Lastname")
    @Size(min = 2, max = 40, message = "Lastname should have maximum 40 characters")
    private String lastName;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 10, max = 10)
    private String phone;

    @NotBlank
    @Size(min = 13, max = 13)
    private String icn;

    @NotBlank
    @Size(max = 50)
    private String adress;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @Size(max = 50)
    private String role;
}
