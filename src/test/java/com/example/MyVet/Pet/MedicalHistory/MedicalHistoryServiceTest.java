package com.example.MyVet.Pet.MedicalHistory;

import com.example.MyVet.Exceptions.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MedicalHistoryServiceTest {

    @Mock
    private MedicalHistoryRepository medicalHistoryRepository;

    @InjectMocks
    private MedicalHistoryService medicalHistoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_ValidMedicalHistoryDTO_ReturnsCreatedMedicalHistory() {
        // Arrange
        MedicalHistoryDTO medicalHistoryDTO = new MedicalHistoryDTO();
        MedicalHistory savedMedicalHistory = new MedicalHistory();

        when(medicalHistoryRepository.save(any(MedicalHistory.class))).thenReturn(savedMedicalHistory);

        // Act
        MedicalHistory result = medicalHistoryService.create(medicalHistoryDTO);

        // Assert
        assertNotNull(result);
        assertEquals(savedMedicalHistory, result);
        verify(medicalHistoryRepository, times(1)).save(any(MedicalHistory.class));
    }

    @Test
    void getAll_ReturnsListOfMedicalHistories() {
        // Arrange
        List<MedicalHistory> medicalHistories = Arrays.asList(new MedicalHistory(), new MedicalHistory());

        when(medicalHistoryRepository.findAll()).thenReturn(medicalHistories);

        // Act
        List<MedicalHistory> result = medicalHistoryService.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(medicalHistories, result);
        verify(medicalHistoryRepository, times(1)).findAll();
    }

    @Test
    void getById_ExistingId_ReturnsMedicalHistory() {
        // Arrange
        String id = "123";
        MedicalHistory medicalHistory = new MedicalHistory();

        when(medicalHistoryRepository.findById(id)).thenReturn(Optional.of(medicalHistory));

        // Act
        Optional<MedicalHistory> result = medicalHistoryService.getById(id);

        // Assert
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(medicalHistory, result.get());
        verify(medicalHistoryRepository, times(1)).findById(id);
    }

    @Test
    void getById_NonexistentId_ReturnsEmptyOptional() {
        // Arrange
        String id = "123";

        when(medicalHistoryRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<MedicalHistory> result = medicalHistoryService.getById(id);

        // Assert
        assertNotNull(result);
        assertFalse(result.isPresent());
        verify(medicalHistoryRepository, times(1)).findById(id);
    }

    @Test
    void update_ExistingIdAndValidMedicalHistoryDTO_ReturnsUpdatedMedicalHistory() {
        // Arrange
        String id = "123";
        MedicalHistoryDTO medicalHistoryDTO = new MedicalHistoryDTO();
        MedicalHistory existingMedicalHistory = new MedicalHistory();
        MedicalHistory updatedMedicalHistory = new MedicalHistory();

        when(medicalHistoryRepository.findById(id)).thenReturn(Optional.of(existingMedicalHistory));
        when(medicalHistoryRepository.save(any(MedicalHistory.class))).thenReturn(updatedMedicalHistory);

        // Act
        MedicalHistory result = medicalHistoryService.update(id, medicalHistoryDTO);

        // Assert
        assertNotNull(result);
        assertEquals(updatedMedicalHistory, result);
        verify(medicalHistoryRepository, times(1)).findById(id);
        verify(medicalHistoryRepository, times(1)).save(any(MedicalHistory.class));
    }

    @Test
    void update_NonexistentId_ThrowsEntityNotFoundException() {
        // Arrange
        String id = "123";
        MedicalHistoryDTO medicalHistoryDTO = new MedicalHistoryDTO();

        when(medicalHistoryRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> medicalHistoryService.update(id, medicalHistoryDTO));
        verify(medicalHistoryRepository, times(1)).findById(id);
        verify(medicalHistoryRepository, never()).save(any(MedicalHistory.class));
    }

    @Test
    void deleteById_ExistingId_DeletesMedicalHistory() {
        // Arrange
        String id = "123";

        // Act
        medicalHistoryService.deleteById(id);

        // Assert
        verify(medicalHistoryRepository, times(1)).deleteById(id);
    }
}
