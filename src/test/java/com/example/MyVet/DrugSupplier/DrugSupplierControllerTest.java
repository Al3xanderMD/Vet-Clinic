package com.example.MyVet.DrugSupplier;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DrugSupplierControllerTest {
    @Mock
    private DrugSupplierService drugSupplierService;

    @InjectMocks
    private DrugSupplierController drugSupplierController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
//    DrugSupplier drugSupplier = new DrugSupplier();
    DrugSupplierMapper drugSupplierMapper = new DrugSupplierMapper();
    @Test
    public void testCreate() {
        // Arrange
        DrugSupplierDTO drugSupplierDTO = new DrugSupplierDTO();
        when(drugSupplierService.create(drugSupplierDTO)).thenReturn(drugSupplierMapper.toDrugSupplier(drugSupplierDTO));

        // Act
        ResponseEntity<DrugSupplier> response = drugSupplierController.create(drugSupplierDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(drugSupplierMapper.toDrugSupplier(drugSupplierDTO), response.getBody());
    }

    @Test
    public void testGetAllDrugSuppliers() {
        // Arrange
        List<DrugSupplier> drugSuppliers = Arrays.asList(
                new DrugSupplier("123", "Supplier 1"),
                new DrugSupplier("456", "Supplier 2")
        );
        when(drugSupplierService.getAllDrugSuppliers()).thenReturn(drugSuppliers);

        // Act
        List<DrugSupplier> response = drugSupplierController.getAllDrugSuppliers();

        // Assert
        assertEquals(drugSuppliers, response);
    }

    @Test
    public void testGetDrugSupplierById_ExistingId() {
        // Arrange
        String supplierId = "123";
        DrugSupplier drugSupplier = new DrugSupplier(supplierId, "Supplier Name");
        when(drugSupplierService.getDrugSupplierById(supplierId)).thenReturn(Optional.of(drugSupplier));

        // Act
        ResponseEntity<DrugSupplier> response = drugSupplierController.getDrugSuplierByID(supplierId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(drugSupplier, response.getBody());
    }

    @Test
    public void testGetDrugSupplierById_NonexistentId() {
        // Arrange
        String supplierId = "123";
        when(drugSupplierService.getDrugSupplierById(supplierId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<DrugSupplier> response = drugSupplierController.getDrugSuplierByID(supplierId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testUpdateDrugSupplier_ExistingId() {
        // Arrange
        String supplierId = "123";
        DrugSupplier updatedSupplier = new DrugSupplier();
        DrugSupplierDTO updatedSupplierDTO = new DrugSupplierDTO();
        when(drugSupplierService.updateDrugSupplier(supplierId, updatedSupplierDTO)).thenReturn(updatedSupplier);

        // Act
        ResponseEntity<DrugSupplier> response = drugSupplierController.updateDrugSupplier(supplierId, updatedSupplierDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedSupplier, response.getBody());
    }

    @Test
    public void testUpdateDrugSupplier_NonexistentId() {
        // Arrange
        String supplierId = "123";
        DrugSupplierDTO updatedSupplierDTO = new DrugSupplierDTO();
        doThrow(new EntityNotFoundException(supplierId)).when(drugSupplierService).updateDrugSupplier(supplierId, updatedSupplierDTO);

        // Act
        ResponseEntity<DrugSupplier> response = drugSupplierController.updateDrugSupplier(supplierId, updatedSupplierDTO);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(new DrugSupplier(), response.getBody());
    }

    @Test
    public void testDeleteDrugSupplierById() {
        // Arrange
        String supplierId = "123";

        // Act
        ResponseEntity<Void> response = drugSupplierController.deleteDrugSupplierById(supplierId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(drugSupplierService, times(1)).deleteDrugSupplierById(supplierId);
    }
}
