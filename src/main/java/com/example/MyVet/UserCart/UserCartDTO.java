package com.example.MyVet.UserCart;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCartDTO {
    @NotBlank
    String userId;
    @NotBlank
    List<String> receiptsId = new ArrayList<>();
}
