package com.example.MyVet.UserCart.Receipt;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDTO {
    @NotBlank
    List<String> drugsSoldId = new ArrayList<>();
}
