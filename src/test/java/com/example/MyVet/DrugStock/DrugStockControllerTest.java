package com.example.MyVet.DrugStock;
import com.example.MyVet.Exceptions.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
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

public class DrugStockControllerTest {

    @Mock
    private DrugStockService drugStockService;

    @InjectMocks
    private DrugStockController drugStockController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetDrugStocks() {
        // Create a sample drug stock list
        DrugStock drugStock1 = new DrugStock();
        drugStock1.setDrugList(new ArrayList<>());
        DrugStock drugStock2 = new DrugStock();
        drugStock2.setDrugList(new ArrayList<>());
        List<DrugStock> expectedDrugStockList = List.of(drugStock1, drugStock2);

        // Mock the behavior of the drug stock service
        when(drugStockService.getAll()).thenReturn(expectedDrugStockList);

        // Call the method under test
        List<DrugStock> actualDrugStockList = drugStockController.getAll();

        // Verify the interactions and assertions
        verify(drugStockService, times(1)).getAll();
        assertEquals(expectedDrugStockList, actualDrugStockList);
    }

    @Test
    public void testCreate() {
        // Create a DrugStockDTO object
        DrugStockDTO drugStockDTO = new DrugStockDTO();

        // Create a DrugStock object
        DrugStock drugStock = new DrugStock();

        // Mock the create method in the drugStockService
        when(drugStockService.create(drugStockDTO)).thenReturn(drugStock);

        // Call the create method in the drugStockController
        ResponseEntity<DrugStock> responseEntity = drugStockController.create(drugStockDTO);

        // Verify that the drugStockService.create method was called once
        verify(drugStockService, times(1)).create(drugStockDTO);

        // Verify that the response entity contains the drugStock and has a CREATED status
        assertEquals(drugStock, responseEntity.getBody());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testGetByIdWithExistingId() {
        // Create a DrugStock object
        DrugStock drugStock = new DrugStock();

        // Mock the getById method in the drugStockService
        when(drugStockService.getById("id")).thenReturn(Optional.of(drugStock));

        // Call the getById method in the drugStockController
        ResponseEntity<DrugStock> responseEntity = drugStockController.getById("id");

        // Verify that the drugStockService.getById method was called once with the correct id
        verify(drugStockService, times(1)).getById("id");

        // Verify that the response entity contains the drugStock and has an OK status
        assertEquals(drugStock, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetByIdWithNonExistingId() {
        // Mock the getById method in the drugStockService to return an empty optional
        when(drugStockService.getById("id")).thenReturn(Optional.empty());

        // Call the getById method in the drugStockController
        ResponseEntity<DrugStock> responseEntity = drugStockController.getById("id");

        // Verify that the drugStockService.getById method was called once with the correct id
        verify(drugStockService, times(1)).getById("id");

        // Verify that the response entity has a NOT_FOUND status
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateWithExistingId() {
        // Create a DrugStockDTO object
        DrugStockDTO drugStockDTO = new DrugStockDTO();

        // Create a DrugStock object
        DrugStock updatedDrugStock = new DrugStock();

        // Mock the update method in the drugStockService
        when(drugStockService.update("id", drugStockDTO)).thenReturn(updatedDrugStock);

        // Call the update method in the drugStockController
        ResponseEntity<DrugStock> responseEntity = drugStockController.update("id", drugStockDTO);

        // Verify that the drugStockService.update method was called once with the correct id and DTO
        verify(drugStockService, times(1)).update("id", drugStockDTO);

        // Verify that the response entity contains the updatedDrugStock and has an OK status
        assertEquals(updatedDrugStock, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateWithNonExistingId() {
        // Create a DrugStockDTO object
        DrugStockDTO drugStockDTO = new DrugStockDTO();

        // Mock the update method in the drugStockService to throw an EntityNotFoundException
        when(drugStockService.update("id", drugStockDTO)).thenThrow(new EntityNotFoundException("DrugStock not found"));

        // Call the update method in the drugStockController
        ResponseEntity<DrugStock> responseEntity = drugStockController.update("id", drugStockDTO);

        // Verify that the drugStockService.update method was called once with the correct id and DTO
        verify(drugStockService, times(1)).update("id", drugStockDTO);

        // Verify that the response entity has a NOT_FOUND status
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteById() {
        // Call the deleteById method in the drugStockController
        ResponseEntity<DrugStock> responseEntity = drugStockController.deleteById("id");

        // Verify that the drugStockService.deleteById method was called once with the correct id
        verify(drugStockService, times(1)).deleteById("id");

        // Verify that the response entity has a NO_CONTENT status
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}
