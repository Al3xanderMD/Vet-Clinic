package com.example.MyVet.Pet.MedicalHistory;

import com.example.MyVet.Exceptions.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MedicalHistoryControllerTest {

    @Mock
    private MedicalHistoryService medicalHistoryService;

    @InjectMocks
    private MedicalHistoryController medicalHistoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_ValidMedicalHistoryDTO_ReturnsCreatedMedicalHistory() {
        // Arrange
        MedicalHistoryDTO medicalHistoryDTO = new MedicalHistoryDTO();
        MedicalHistory savedMedicalHistory = new MedicalHistory();

        when(medicalHistoryService.create(medicalHistoryDTO)).thenReturn(savedMedicalHistory);

        // Act
        ResponseEntity<MedicalHistory> response = medicalHistoryController.create(medicalHistoryDTO);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedMedicalHistory, response.getBody());
        verify(medicalHistoryService, times(1)).create(medicalHistoryDTO);
    }

    @Test
    void getAll_ReturnsListOfMedicalHistories() {
        // Arrange
        List<MedicalHistory> medicalHistories = Arrays.asList(new MedicalHistory(), new MedicalHistory());

        when(medicalHistoryService.getAll()).thenReturn(medicalHistories);

        // Act
        List<MedicalHistory> result = medicalHistoryController.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(medicalHistories, result);
        verify(medicalHistoryService, times(1)).getAll();
    }

    @Test
    void getById_ExistingId_ReturnsMedicalHistory() {
        // Arrange
        String id = "123";
        MedicalHistory medicalHistory = new MedicalHistory();

        when(medicalHistoryService.getById(id)).thenReturn(Optional.of(medicalHistory));

        // Act
        ResponseEntity<MedicalHistory> response = medicalHistoryController.getById(id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(medicalHistory, response.getBody());
        verify(medicalHistoryService, times(1)).getById(id);
    }

    @Test
    void getById_NonexistentId_ReturnsNotFound() {
        // Arrange
        String id = "123";

        when(medicalHistoryService.getById(id)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<MedicalHistory> response = medicalHistoryController.getById(id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(medicalHistoryService, times(1)).getById(id);
    }

    @Test
    void update_ExistingIdAndValidMedicalHistoryDTO_ReturnsUpdatedMedicalHistory() {
        // Arrange
        String id = "123";
        MedicalHistoryDTO medicalHistoryDTO = new MedicalHistoryDTO();
        MedicalHistory existingMedicalHistory = new MedicalHistory();
        MedicalHistory updatedMedicalHistory = new MedicalHistory();

        when(medicalHistoryService.update(id, medicalHistoryDTO)).thenReturn(updatedMedicalHistory);

        // Act
        ResponseEntity<MedicalHistory> response = medicalHistoryController.update(id, medicalHistoryDTO);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedMedicalHistory, response.getBody());
        verify(medicalHistoryService, times(1)).update(id, medicalHistoryDTO);
    }

//    @Test
//    void update_NonexistentId_ThrowsEntityNotFoundException() {
//        // Arrange
//        String id = "123";
//        MedicalHistoryDTO medicalHistoryDTO = new MedicalHistoryDTO();
//
//        when(medicalHistoryService.update(id, medicalHistoryDTO)).thenThrow(EntityNotFoundException.class);
//
////         Act & Assert
//        assertThrows(EntityNotFoundException.class, () -> medicalHistoryController.update(id, medicalHistoryDTO));
//        verify(medicalHistoryService, times(1)).update(id, medicalHistoryDTO);
//    }

    @Test
    void deleteById_ExistingId_ReturnsNoContent() {
        // Arrange
        String id = "123";

        // Act
        ResponseEntity<Void> response = medicalHistoryController.deleteById(id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(medicalHistoryService, times(1)).deleteById(id);
    }
}
