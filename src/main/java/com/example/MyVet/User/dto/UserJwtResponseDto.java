package com.example.MyVet.User.dto;

import lombok.*;

@Data
public class UserJwtResponseDto {
    private String token;
    private String type = "Bearer";
    private String id;
    private String email;
    private String role;

    public UserJwtResponseDto(String accessToken, String id, String email, String role) {
        this.token = accessToken;
        this.id = id;
        this.email = email;
        this.role = role;
    }
}
