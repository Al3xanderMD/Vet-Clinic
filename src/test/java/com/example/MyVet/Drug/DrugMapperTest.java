package com.example.MyVet.Drug;

import com.example.MyVet.DrugSupplier.DrugSupplier;
import com.example.MyVet.DrugSupplier.DrugSupplierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DrugMapperTest {

    @Mock
    private DrugSupplierService drugSupplierService;

    private DrugMapper drugMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        drugMapper = new DrugMapper(drugSupplierService);
    }

    @Test
    void toDrug_WithValidDrugDTO_ReturnsDrugWithSuppliers() {
        // Arrange
        DrugDTO drugDTO = new DrugDTO();
        drugDTO.setReqPrescription(true);
        drugDTO.setImageURL("https://example.com/drug-image.jpg");
        drugDTO.setName("Example Drug");
        drugDTO.setDrugSuppliersList(Arrays.asList("1", "2", "3"));

        DrugSupplier supplier1 = new DrugSupplier();
        supplier1.setId("1");
        DrugSupplier supplier2 = new DrugSupplier();
        supplier2.setId("2");
        DrugSupplier supplier3 = new DrugSupplier();
        supplier3.setId("3");

        when(drugSupplierService.getDrugSupplierById("1")).thenReturn(Optional.of(supplier1));
        when(drugSupplierService.getDrugSupplierById("2")).thenReturn(Optional.of(supplier2));
        when(drugSupplierService.getDrugSupplierById("3")).thenReturn(Optional.of(supplier3));

        // Act
        Drug drug = DrugMapper.toDrug(drugDTO);

        // Assert
        assertTrue(drug.getReqPrescription());
        assertEquals("https://example.com/drug-image.jpg", drug.getImageURL());
        assertEquals("Example Drug", drug.getName());

        List<DrugSupplier> supplierList = drug.getDrugSuppliersList();
        assertEquals(3, supplierList.size());
        assertTrue(supplierList.contains(supplier1));
        assertTrue(supplierList.contains(supplier2));
        assertTrue(supplierList.contains(supplier3));

        verify(drugSupplierService, times(1)).getDrugSupplierById("1");
        verify(drugSupplierService, times(1)).getDrugSupplierById("2");
        verify(drugSupplierService, times(1)).getDrugSupplierById("3");
    }

    @Test
    void toDrug_WithEmptyDrugSuppliersId_ReturnsDrugWithEmptySupplierList() {
        // Arrange
        DrugDTO drugDTO = new DrugDTO();
        drugDTO.setReqPrescription(false);
        drugDTO.setImageURL("https://example.com/default-drug-image.jpg");
        drugDTO.setName("Empty Suppliers Drug");
        drugDTO.setDrugSuppliersList(Collections.emptyList());

        // Act
        Drug drug = DrugMapper.toDrug(drugDTO);

        // Assert
        assertFalse(drug.getReqPrescription());
        assertEquals("https://example.com/default-drug-image.jpg", drug.getImageURL());
        assertEquals("Empty Suppliers Drug", drug.getName());
        assertTrue(drug.getDrugSuppliersList().isEmpty());

        verifyNoInteractions(drugSupplierService);
    }
}
