package com.example.MyVet.MedicalStaff;

import com.example.MyVet.Cabinet.CabinetService;
import com.example.MyVet.Exceptions.EntityNotFoundException;
import com.example.MyVet.User.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MedicalStaffServiceTest {
    @Mock
    private MedicalStaffRepository medicalStaffRepository;

    @InjectMocks
    private MedicalStaffService medicalStaffService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getMedicalStaff_shouldReturnEmptyList() {
        // Arrange
        when(medicalStaffRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<MedicalStaff> result = medicalStaffService.getMedicalStaff();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
//        verify(medicalStaffRepository, times(1)).findAll();
    }

    @Test
    void add_shouldSaveMedicalStaff() {
        // Arrange
        MedicalStaff medicalStaff = new MedicalStaff();
        when(medicalStaffRepository.save(medicalStaff)).thenReturn(medicalStaff);

        // Act
        MedicalStaff result = medicalStaffService.add(medicalStaff);

        // Assert
        assertNotNull(result);
        assertEquals(medicalStaff, result);
        verify(medicalStaffRepository, times(1)).save(medicalStaff);
    }

    @Test
    void getAll_shouldReturnListOfMedicalStaff() {
        // Arrange
        List<MedicalStaff> medicalStaffList = new ArrayList<>();
        when(medicalStaffRepository.findAll()).thenReturn(medicalStaffList);

        // Act
        List<MedicalStaff> result = medicalStaffService.getAll();

        // Assert
        assertEquals(medicalStaffList, result);
        verify(medicalStaffRepository, times(1)).findAll();
    }

    @Test
    void getById_existingId_shouldReturnMedicalStaff() {
        // Arrange
        String id = "1";
        MedicalStaff medicalStaff = new MedicalStaff();
        when(medicalStaffRepository.findById(id)).thenReturn(Optional.of(medicalStaff));

        // Act
        Optional<MedicalStaff> result = medicalStaffService.getById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(medicalStaff, result.get());
        verify(medicalStaffRepository, times(1)).findById(id);
    }

    @Test
    void getById_nonExistingId_shouldReturnEmptyOptional() {
        // Arrange
        String id = "1";
        when(medicalStaffRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<MedicalStaff> result = medicalStaffService.getById(id);

        // Assert
        assertFalse(result.isPresent());
        verify(medicalStaffRepository, times(1)).findById(id);
    }

//    @Test
//    public void testUpdateWhenMedicalStaffExists() {
//        MockitoAnnotations.openMocks(this);
//
//        String id = "1";
//        MedicalStaffDTO medicalStaffDTO = new MedicalStaffDTO();
//
//        User user=new User();
//        MedicalStaff existingMedicalStaff = new MedicalStaff();
//        existingMedicalStaff.setId(id);
//        existingMedicalStaff.setUser(user);
//
//        MedicalStaff updatedMedicalStaff = new MedicalStaff();
//        updatedMedicalStaff.setId(id);
//        updatedMedicalStaff.setUser(existingMedicalStaff.getUser());
//        // set other fields based on the DTO values
//
//        when(medicalStaffRepository.findById(id)).thenReturn(Optional.of(existingMedicalStaff));
//        when(medicalStaffRepository.save(updatedMedicalStaff)).thenReturn(updatedMedicalStaff);
//
//        MedicalStaff result = medicalStaffService.update(id, medicalStaffDTO);
//
//        assertEquals(updatedMedicalStaff, result);
//
//        verify(medicalStaffRepository, times(1)).findById(id);
//        verify(medicalStaffRepository, times(1)).save(updatedMedicalStaff);
//    }

//    @Test
//    void update_existingId_shouldReturnUpdatedMedicalStaff() {
//        // Arrange
//        String id = "1";
//        MedicalStaffDTO medicalStaffDTO = new MedicalStaffDTO();
//        MedicalStaff existingMedicalStaff = new MedicalStaff();
//        when(medicalStaffRepository.findById(id)).thenReturn(Optional.of(existingMedicalStaff));
//        when(medicalStaffRepository.save(any(MedicalStaff.class))).thenReturn(existingMedicalStaff);
//        MedicalStaffMapper medicalStaffMapper = new MedicalStaffMapper();
//
//        // Act
//        MedicalStaff result = medicalStaffService.update(id, medicalStaffDTO);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(existingMedicalStaff, result);
//        verify(medicalStaffRepository, times(1)).findById(id);
//        verify(medicalStaffRepository, times(1)).save(existingMedicalStaff);
//    }

    @Test
    void update_nonExistingId_shouldThrowEntityNotFoundException() {
        // Arrange
        String id = "1";
        MedicalStaffDTO medicalStaffDTO = new MedicalStaffDTO();
        when(medicalStaffRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> medicalStaffService.update(id, medicalStaffDTO));
        verify(medicalStaffRepository, times(1)).findById(id);
        verify(medicalStaffRepository, times(0)).save(any(MedicalStaff.class));
    }
}
