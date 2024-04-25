package com.example.MyVet.User.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDto {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
