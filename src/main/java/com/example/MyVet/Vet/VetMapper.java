package com.example.MyVet.Vet;

import com.example.MyVet.Cabinet.CabinetController;
import com.example.MyVet.Cabinet.CabinetService;
import com.example.MyVet.MedicalStaff.MedicalStaffController;
import com.example.MyVet.MedicalStaff.MedicalStaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VetMapper {

    public Vet toVet(VetDTO vetDTO) {
        Vet vet = new Vet();
        return vet;
    }
}
