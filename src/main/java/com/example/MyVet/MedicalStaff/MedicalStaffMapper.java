package com.example.MyVet.MedicalStaff;

import com.example.MyVet.Cabinet.Cabinet;
import com.example.MyVet.Cabinet.CabinetController;
import com.example.MyVet.Cabinet.CabinetService;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MedicalStaffMapper {
    private static CabinetService cabinetService;

    @Autowired
    private MedicalStaffMapper(CabinetService cabinetService){this.cabinetService = cabinetService;}

    public static MedicalStaff toMedicalStaff(MedicalStaffDTO medicalStaffDTO) {
        MedicalStaff medicalStaff = new MedicalStaff();

        medicalStaff.setExperience(medicalStaffDTO.getExperience());
        medicalStaff.setSalary(medicalStaffDTO.getSalary());

        var cabMS = cabinetService.getById(medicalStaffDTO.getCabinetId());
        cabMS.ifPresent(medicalStaff::setCabinet);
        return medicalStaff;
    }
}
