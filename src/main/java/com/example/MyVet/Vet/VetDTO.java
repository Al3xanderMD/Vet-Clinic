package com.example.MyVet.Vet;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VetDTO {
    @NotBlank
    String cabinetId;
}
