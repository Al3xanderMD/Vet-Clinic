package com.example.MyVet.MedicalStaff;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MedicalStaffControllerTest {
    @Mock
    private MedicalStaffService medicalStaffService;

    @InjectMocks
    private MedicalStaffController medicalStaffController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getMedicalStaff_shouldReturnListOfMedicalStaff() {
        // Arrange
        List<MedicalStaff> medicalStaffList = new ArrayList<>();
        when(medicalStaffService.getAll()).thenReturn(medicalStaffList);

        // Act
        List<MedicalStaff> result = medicalStaffController.getMedicalStaff();

        // Assert
        assertEquals(medicalStaffList, result);
        verify(medicalStaffService, times(1)).getAll();
    }

    @Test
    void getById_existingId_shouldReturnMedicalStaff() {
        // Arrange
        String id = "1";
        MedicalStaff medicalStaff = new MedicalStaff();
        when(medicalStaffService.getById(id)).thenReturn(Optional.of(medicalStaff));

        // Act
        ResponseEntity<MedicalStaff> response = medicalStaffController.getById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(medicalStaff, response.getBody());
        verify(medicalStaffService, times(1)).getById(id);
    }

    @Test
    void getById_nonExistingId_shouldReturnNotFound() {
        // Arrange
        String id = "1";
        when(medicalStaffService.getById(id)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<MedicalStaff> response = medicalStaffController.getById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(medicalStaffService, times(1)).getById(id);
    }

    @Test
    void update_existingId_shouldReturnUpdatedMedicalStaff() {
        // Arrange
        String id = "1";
        MedicalStaffDTO medicalStaffDTO = new MedicalStaffDTO();
        MedicalStaff updatedMedicalStaff = new MedicalStaff();
        when(medicalStaffService.update(id, medicalStaffDTO)).thenReturn(updatedMedicalStaff);

        // Act
        ResponseEntity<MedicalStaff> response = medicalStaffController.update(id, medicalStaffDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedMedicalStaff, response.getBody());
        verify(medicalStaffService, times(1)).update(id, medicalStaffDTO);
    }

}
