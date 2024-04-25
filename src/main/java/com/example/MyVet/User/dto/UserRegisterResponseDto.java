package com.example.MyVet.User.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
public class UserRegisterResponseDto {
    private String email;
    private String password;
    private String role;
    private String verificationCode;
    private String icn;
    private List<String> adresses;
}
