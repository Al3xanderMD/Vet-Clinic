package com.example.MyVet.DrugStock;

import com.example.MyVet.Drug.Drug;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrugStockDTO {
    @NotNull(message = "DrugsIdList cannot be null")
    List<String> drugsId=new ArrayList<>();
}