package com.example.MyVet.DrugSupplier;

import com.example.MyVet.Exceptions.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;

public class DrugSupplierServiceTest {

    @Mock
    private DrugSupplierRepository drugSupplierRepository;

    @InjectMocks
    private DrugSupplierService drugSupplierService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    DrugSupplierMapper drugSupplierMapper = new DrugSupplierMapper();

    @Test
    public void testAddDrugSupplier() {
        // Arrange
        DrugSupplierDTO drugSupplier = new DrugSupplierDTO();

        when(drugSupplierRepository.save(drugSupplierMapper.toDrugSupplier(drugSupplier))).thenReturn(drugSupplierMapper.toDrugSupplier(drugSupplier));

        // Act
        DrugSupplier result = drugSupplierService.create(drugSupplier);

        // Assert
        assertNotNull(result);
        assertEquals(drugSupplierMapper.toDrugSupplier(drugSupplier), result);
        verify(drugSupplierRepository, times(1)).save(drugSupplierMapper.toDrugSupplier(drugSupplier));
    }

    @Test
    public void testGetAllDrugSuppliers() {
        // Arrange
        List<DrugSupplier> expectedSuppliers = new ArrayList<>();
        when(drugSupplierRepository.findAll()).thenReturn(expectedSuppliers);

        // Act
        List<DrugSupplier> result = drugSupplierService.getAllDrugSuppliers();

        // Assert
        assertNotNull(result);
        assertEquals(expectedSuppliers, result);
        verify(drugSupplierRepository, times(1)).findAll();
    }

    @Test
    public void testGetDrugSupplierById() {
        // Arrange
        String supplierId = "123";
        DrugSupplier expectedSupplier = new DrugSupplier();
        when(drugSupplierRepository.findById(supplierId)).thenReturn(Optional.of(expectedSupplier));

        // Act
        Optional<DrugSupplier> result = drugSupplierService.getDrugSupplierById(supplierId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedSupplier, result.get());
        verify(drugSupplierRepository, times(1)).findById(supplierId);
    }

    @Test
    public void testUpdateDrugSupplier() {
        // Arrange
        String supplierId = "123";
        DrugSupplier existingSupplier = new DrugSupplier();
        existingSupplier.setId(supplierId);
        existingSupplier.setName("Supplier Name");

        DrugSupplier updatedSupplier = new DrugSupplier();
        updatedSupplier.setId(supplierId);
        updatedSupplier.setName("Updated Supplier Name");

        DrugSupplierDTO drugSupplierDTO = new DrugSupplierDTO();
        drugSupplierDTO.setName("Updated Supplier Name");
        when(drugSupplierRepository.findById(supplierId)).thenReturn(Optional.of(existingSupplier));
        when(drugSupplierRepository.save(any(DrugSupplier.class))).thenReturn(updatedSupplier);

        // ActF
        DrugSupplier result = drugSupplierService.updateDrugSupplier(supplierId, drugSupplierDTO);

        // Assert
        assertNotNull(result);
        assertEquals(updatedSupplier, result);
        verify(drugSupplierRepository, times(1)).findById(supplierId);
        verify(drugSupplierRepository, times(1)).save(any(DrugSupplier.class));
    }

    @Test
    public void testUpdateDrugSupplier_EntityNotFoundException() {
        // Arrange
        String supplierId = "123";
        DrugSupplier updatedSupplier = new DrugSupplier();
        updatedSupplier.setId(supplierId);
        updatedSupplier.setName("Updated Supplier Name");
        DrugSupplierDTO drugSupplierDTO = new DrugSupplierDTO();
        drugSupplierDTO.setName("Updated Supplier Name");

        when(drugSupplierRepository.findById(supplierId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> drugSupplierService.updateDrugSupplier(supplierId, drugSupplierDTO));
        verify(drugSupplierRepository, times(1)).findById(supplierId);
        verify(drugSupplierRepository, times(0)).save(any(DrugSupplier.class));
    }

    @Test
    public void testDeleteDrugSupplierById() {
        // Arrange
        String supplierId = "123";

        // Act
        drugSupplierService.deleteDrugSupplierById(supplierId);

        // Assert
        verify(drugSupplierRepository, times(1)).deleteById(supplierId);
    }
}
