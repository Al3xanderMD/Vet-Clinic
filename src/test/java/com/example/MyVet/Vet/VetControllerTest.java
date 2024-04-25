package com.example.MyVet.Vet;

import com.example.MyVet.Exceptions.EntityNotFoundException;
import com.example.MyVet.MedicalStaff.MedicalStaff;
import com.example.MyVet.User.users.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class VetControllerTest {
    private VetController vetController;

    @Mock
    private VetService vetService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        vetController = new VetController(vetService);
    }

    @Test
    void getVet_ReturnsListOfVets() {
        // Arrange
        List<Vet> vetList = new ArrayList<>();
        MedicalStaff medicalStaff = new MedicalStaff(new User("name", "lname", "email", "1111111111","123345", "bau", "edfgh"));
        MedicalStaff medicalStaff1 = new MedicalStaff(new User("name1", "kname1", "email1", "123435456", "1234567", "email2", "2e3r4tghg"));
        vetList.add(new Vet(medicalStaff));
        vetList.add(new Vet(medicalStaff1));
        when(vetService.getAll()).thenReturn(vetList);

        // Act
        List<Vet> result = vetController.getVet();

        // Assert
        assertEquals(vetList, result);
        verify(vetService, times(1)).getAll();
    }

    @Test
    void getById_ExistingId_ReturnsVet() {
        // Arrange
        String vetId = "1";
        MedicalStaff medicalStaff = new MedicalStaff(new User("name", "lname", "email", "1111111111","123345", "bau", "edfgh"));
        Vet vet = new Vet(medicalStaff);
        when(vetService.getById(vetId)).thenReturn(Optional.of(vet));

        // Act
        ResponseEntity<Vet> response = vetController.getById(vetId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vet, response.getBody());
        verify(vetService, times(1)).getById(vetId);
    }

    @Test
    void getById_NonExistingId_ReturnsNotFoundResponse() {
        // Arrange
        String vetId = "1";
        when(vetService.getById(vetId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Vet> response = vetController.getById(vetId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(vetService, times(1)).getById(vetId);
    }

}
