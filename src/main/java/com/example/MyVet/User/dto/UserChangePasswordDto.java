package com.example.MyVet.User.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserChangePasswordDto {
    @NotBlank
    @Size(min = 6, max = 40)
    private String newPassword;
}
