package com.example.MyVet.MedicalStaff;

import com.example.MyVet.Cabinet.Cabinet;
import com.example.MyVet.Cabinet.CabinetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MedicalStaffMapperTest {
    @Mock
    private CabinetService cabinetService;

    @InjectMocks
    private MedicalStaffMapper medicalStaffMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void toMedicalStaff_shouldMapMedicalStaffDTOToMedicalStaff() {
        // Arrange
        MedicalStaffDTO medicalStaffDTO = new MedicalStaffDTO();
        medicalStaffDTO.setExperience(5);
        medicalStaffDTO.setSchedule("Monday-Friday");
        medicalStaffDTO.setSalary(5000);
        medicalStaffDTO.setCabinetId("1");

        Cabinet cabinet = new Cabinet();
        when(cabinetService.getById(medicalStaffDTO.getCabinetId())).thenReturn(Optional.of(cabinet));

        // Act
        MedicalStaff result = MedicalStaffMapper.toMedicalStaff(medicalStaffDTO);

        // Assert
        assertEquals(medicalStaffDTO.getExperience(), result.getExperience());
        assertEquals(medicalStaffDTO.getSchedule(), result.getSchedule());
        assertEquals(medicalStaffDTO.getSalary(), result.getSalary());
        assertEquals(cabinet, result.getCabinet());

        verify(cabinetService, times(1)).getById(medicalStaffDTO.getCabinetId());
    }
}
