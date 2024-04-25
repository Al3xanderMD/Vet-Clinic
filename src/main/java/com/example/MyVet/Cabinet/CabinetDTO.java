package com.example.MyVet.Cabinet;

import com.example.MyVet.Appointment.Appointment;
import com.example.MyVet.DrugStock.DrugStock;
import com.example.MyVet.MedicalStaff.MedicalStaff;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CabinetDTO {
    @NotNull
    String name;
    @NotNull
    String drugStockId;
}
