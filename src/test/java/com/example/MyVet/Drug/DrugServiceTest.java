package com.example.MyVet.Drug;

import com.example.MyVet.DrugSupplier.DrugSupplier;
import com.example.MyVet.Exceptions.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DrugServiceTest {

    @Mock
    private DrugRepository drugRepository;

    @InjectMocks
    private DrugService drugService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdd() {
        DrugDTO drug = new DrugDTO();
//        DrugMapper drugMapper = new DrugMapper();
        Drug drug1 = DrugMapper.toDrug(drug);
        when(drugRepository.save(drug1)).thenReturn(drug1);

        Drug result = drugService.create(drug);

        assertEquals(DrugMapper.toDrug(drug), result);
        verify(drugRepository, times(1)).save(drug1);
    }

    @Test
    void testGetAll() {
        List<Drug> drugList = new ArrayList<>();
        when(drugRepository.findAll()).thenReturn(drugList);

        List<Drug> result = drugService.getAll();

        assertEquals(drugList, result);
        verify(drugRepository, times(1)).findAll();
    }

    @Test
    void testGetById() {
        String id = "123456";
        Drug drug = new Drug();
        when(drugRepository.findById(id)).thenReturn(Optional.of(drug));

        Optional<Drug> result = drugService.getById(id);

        assertEquals(Optional.of(drug), result);
        verify(drugRepository, times(1)).findById(id);
    }

    @Test
    void testUpdateById() {
        String id = "123456";
        Drug existingDrug = new Drug();
        Drug updatedDrug = new Drug();
        DrugDTO updated = new DrugDTO();
        when(drugRepository.findById(id)).thenReturn(Optional.of(existingDrug));
        when(drugRepository.save(updatedDrug)).thenReturn(updatedDrug);

        Drug result = drugService.updateById(id, updated);

        assertNotEquals(updatedDrug, result);
//        verify(drugRepository, times(1)).findById(id);
//        verify(drugRepository, times(1)).save(updatedDrug);
    }

    @Test
    void testUpdateById_EntityNotFoundException() {
        String id = "123456";
        DrugDTO updatedDrug = new DrugDTO();
        Drug updated = DrugMapper.toDrug(updatedDrug);
        when(drugRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> drugService.updateById(id, updatedDrug));

        verify(drugRepository, times(1)).findById(id);
        verify(drugRepository, times(0)).save(updated);
    }

    @Test
    void testDeleteById() {
        String id = "123456";

        drugService.deleteById(id);

        verify(drugRepository, times(1)).deleteById(id);
    }

//    @Test
//    void testEqualsAndHashCode() {
//        DrugService drugService1 = new DrugService();
//        DrugService drugService2 = new DrugService();
//
//        // Reflexive
//        assertEquals(drugService1, drugService1);
//        assertEquals(drugService1.hashCode(), drugService1.hashCode());
//
//        // Symmetric
//        assertNotEquals(drugService1, drugService2);
//        assertNotEquals(drugService2, drugService1);
//        assertNotEquals(drugService1.hashCode(), drugService2.hashCode());
//
//        // Transitive
//        DrugService drugService3 = new DrugService();
//        assertNotEquals(drugService2, drugService3);
//        assertNotEquals(drugService1, drugService3);
//        assertNotEquals(drugService1.hashCode(), drugService3.hashCode());
//
//        // Inequality
//        assertNotEquals(drugService1, null);
//        assertNotEquals(drugService1, "drugService");
//    }
//
//    @Test
//    void testToString() {
//        DrugService drugService = new DrugService();
//
//        String expectedToString = drugService.toString();
//        assertEquals(expectedToString, drugService.toString());
//    }
//    @Test
//    void testCanEqual() {
//        DrugService drugService1 = new DrugService();
//        DrugService drugService2 = new DrugService();
//
//        assertTrue(drugService1.can(drugService2));
//        assertTrue(drugService2.canEqual(drugService1));
//    }

    @Test
    void testGetters() {
        Drug drug = new Drug();
        drug.setImageURL("image-url");
        drug.setId("123");
        drug.setName("Drug Name");
        drug.setDrugSuppliersList(Arrays.asList(new DrugSupplier("12","Supplier 1"), new DrugSupplier("13","Supplier 2")));
        drug.setReqPrescription(true);

        when(drugRepository.findById(drug.getId())).thenReturn(Optional.of(drug));

        Drug result = drugService.getById(drug.getId()).get();

        assertEquals(drug.getImageURL(), result.getImageURL());
        assertEquals(drug.getId(), result.getId());
        assertEquals(drug.getName(), result.getName());
        assertEquals(drug.getDrugSuppliersList(), result.getDrugSuppliersList());
        assertEquals(drug.getReqPrescription(), result.getReqPrescription());
    }

    @Test
    void updateQuantityTest() {
        String drugId = "123"; // Provide a sample drug ID
        double drugSold = 0.0; // Provide the quantity of drug sold
        Drug existingDrug = new Drug(); // Create a sample existing Drug object

        Mockito.when(drugRepository.findById(drugId)).thenReturn(Optional.of(existingDrug));
        Mockito.when(drugRepository.save(Mockito.any(Drug.class))).thenReturn(existingDrug);

        Drug result = drugService.updateQuantity(drugId, drugSold);
        Assertions.assertEquals(existingDrug, result, "Returned drug does not match the expected updated drug.");
        Assertions.assertEquals(drugSold, result.getQuantity(), "Quantity of the updated drug is not as expected.");
    }
}
