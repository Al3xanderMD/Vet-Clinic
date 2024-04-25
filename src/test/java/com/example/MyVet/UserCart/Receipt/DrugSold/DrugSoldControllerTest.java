package com.example.MyVet.UserCart.Receipt.DrugSold;

import com.example.MyVet.Exceptions.EntityNotFoundException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DrugSoldControllerTest {

    @Mock
    private DrugSoldService drugSoldService;

    @InjectMocks
    private DrugSoldController drugSoldController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll_shouldReturnListOfDrugSolds() {
        // Arrange
        List<DrugSold> drugSolds = new ArrayList<>();
        when(drugSoldService.getAll()).thenReturn(drugSolds);

        // Act
        List<DrugSold> result = drugSoldController.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(drugSolds, result);
        verify(drugSoldService, times(1)).getAll();
    }

    @Test
    void create_withValidDrugSoldDTO_shouldReturnCreatedResponse() throws Exception {
        // Arrange
        DrugSoldDTO drugSoldDTO = new DrugSoldDTO();
        DrugSold savedDrugSold = new DrugSold();
        when(drugSoldService.create(drugSoldDTO)).thenReturn(savedDrugSold);

        // Act
        ResponseEntity<DrugSold> response = drugSoldController.create(drugSoldDTO);

        // Assert
        assertNotNull(response);
        assertEquals(savedDrugSold, response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(drugSoldService, times(1)).create(drugSoldDTO);
    }

    @Test
    void getById_withExistingId_shouldReturnDrugSold() {
        // Arrange
        String id = "1";
        DrugSold drugSold = new DrugSold();
        when(drugSoldService.getById(id)).thenReturn(Optional.of(drugSold));

        // Act
        ResponseEntity<DrugSold> response = drugSoldController.getById(id);

        // Assert
        assertNotNull(response);
        assertEquals(drugSold, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(drugSoldService, times(1)).getById(id);
    }

    @Test
    void getById_withNonExistingId_shouldReturnNotFoundResponse() {
        // Arrange
        String id = "1";
        when(drugSoldService.getById(id)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<DrugSold> response = drugSoldController.getById(id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(drugSoldService, times(1)).getById(id);
    }

    @Test
    void update_withExistingId_shouldReturnUpdatedDrugSold() {
        // Arrange
        String id = "1";
        DrugSoldDTO drugSoldDTO = new DrugSoldDTO();
        DrugSold updatedDrugSold = new DrugSold();
        try {
            when(drugSoldService.update(id, drugSoldDTO)).thenReturn(updatedDrugSold);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Act
        ResponseEntity<DrugSold> response = drugSoldController.update(id, drugSoldDTO);

        // Assert
        assertNotNull(response);
        assertEquals(updatedDrugSold, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        try {
            verify(drugSoldService, times(1)).update(id, drugSoldDTO);
        }catch (Exception e){
            throw new RuntimeException(e);

        }
    }

    @Test
    void update_withNonExistingId_shouldReturnNotFoundResponse() {
        // Arrange
        String id = "1";
        DrugSoldDTO drugSoldDTO = new DrugSoldDTO();
        try {
            when(drugSoldService.update(id, drugSoldDTO)).thenThrow(EntityNotFoundException.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Act
        ResponseEntity<DrugSold> response = drugSoldController.update(id, drugSoldDTO);

        // Assert
        assertNotNull(response);
        assertNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        try {
            verify(drugSoldService, times(1)).update(id, drugSoldDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void deleteById_withExistingId_shouldReturnNoContentResponse() {
        // Arrange
        String id = "1";

        // Act
        ResponseEntity<Void> response = drugSoldController.deleteById(id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(drugSoldService, times(1)).deleteById(id);
    }
}
