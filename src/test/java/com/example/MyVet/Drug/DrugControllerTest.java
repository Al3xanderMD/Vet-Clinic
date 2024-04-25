package com.example.MyVet.Drug;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

class DrugControllerTest {

    @Mock
    private DrugService drugService;

    @InjectMocks
    private DrugController drugController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        DrugDTO drugDTO = new DrugDTO();
        Drug drug = new Drug();
        when(drugService.create(drugDTO)).thenReturn(drug);

        ResponseEntity<Drug> response = drugController.create(drugDTO);

        assertNotEquals(DrugMapper.toDrug(drugDTO), response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(drugService, times(1)).create(drugDTO);
    }

    @Test
    void testGetAll() {
        List<Drug> drugList = new ArrayList<>();
        when(drugService.getAll()).thenReturn(drugList);

        List<Drug> result = drugController.getAll();

        assertEquals(drugList, result);
        verify(drugService, times(1)).getAll();
    }

    @Test
    void testGetById_ExistingDrug() {
        String id = "123456";
        Drug drug = new Drug();
        when(drugService.getById(id)).thenReturn(Optional.of(drug));

        ResponseEntity<Drug> response = drugController.getById(id);

        assertEquals(drug, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(drugService, times(1)).getById(id);
    }

    @Test
    void testGetById_NonExistingDrug() {
        String id = "123456";
        when(drugService.getById(id)).thenReturn(Optional.empty());

        ResponseEntity<Drug> response = drugController.getById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(drugService, times(1)).getById(id);
    }

    @Test
    void testUpdateById_ExistingDrug() {
        String id = "123456";
        DrugDTO drug = new DrugDTO();
        Drug updatedDrug = new Drug();
        when(drugService.updateById(id, drug)).thenReturn(updatedDrug);

        ResponseEntity<Drug> response = drugController.updateById(id, drug);

        assertEquals(updatedDrug, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(drugService, times(1)).updateById(id, drug);
    }

    @Test
    void testUpdateById_NonExistingDrug() {
        String id = "123456";
        DrugDTO drug = new DrugDTO();
        when(drugService.updateById(id, drug)).thenThrow(EntityNotFoundException.class);

        ResponseEntity<Drug> response = drugController.updateById(id, drug);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(drugService, times(1)).updateById(id, drug);
    }

    @Test
    void testDeleteById() {
        String id = "123456";

        ResponseEntity<Void> response = drugController.deleteById(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(drugService, times(1)).deleteById(id);
    }



}
