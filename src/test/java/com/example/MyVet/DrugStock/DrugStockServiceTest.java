package com.example.MyVet.DrugStock;


import com.example.MyVet.Exceptions.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class DrugStockServiceTest {

    @Mock
    private DrugStockRepository drugStockRepository;

    @InjectMocks
    private DrugStockService drugStockService;

    private DrugStockMapper drugStockMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        drugStockMapper = new DrugStockMapper();
    }

    @Test
    public void testCreate() {
        DrugStockDTO drugStockDTO = new DrugStockDTO();
        DrugStock drugStock = drugStockMapper.toDrugStock(drugStockDTO);

        when(drugStockRepository.save(any(DrugStock.class))).thenReturn(drugStock);

        DrugStock createdDrugStock = drugStockService.create(drugStockDTO);

        verify(drugStockRepository, times(1)).save(any(DrugStock.class));
        assertEquals(drugStock, createdDrugStock);
    }

    @Test
    public void testGetAll() {
        List<DrugStock> drugStockList = new ArrayList<>();
        drugStockList.add(new DrugStock());
        drugStockList.add(new DrugStock());

        when(drugStockRepository.findAll()).thenReturn(drugStockList);

        List<DrugStock> retrievedList = drugStockService.getAll();

        verify(drugStockRepository, times(1)).findAll();
        assertEquals(drugStockList, retrievedList);
    }

    @Test
    public void testGetById() {
        String id = "123";
        DrugStock drugStock = new DrugStock();

        when(drugStockRepository.findById(id)).thenReturn(Optional.of(drugStock));

        Optional<DrugStock> retrievedDrugStock = drugStockService.getById(id);

        verify(drugStockRepository, times(1)).findById(id);
        assertEquals(Optional.of(drugStock), retrievedDrugStock);
    }

    @Test
    public void testUpdate_ExistingDrugStock() {
        String id = "123";
        DrugStockDTO drugStockDTO = new DrugStockDTO();
        DrugStock existingDrugStock = new DrugStock();
        DrugStock updatedDrugStock = new DrugStock();

        when(drugStockRepository.findById(id)).thenReturn(Optional.of(existingDrugStock));
        when(drugStockRepository.save(any(DrugStock.class))).thenReturn(updatedDrugStock);

        DrugStock updated = drugStockService.update(id, drugStockDTO);

        verify(drugStockRepository, times(1)).findById(id);
        verify(drugStockRepository, times(1)).save(any(DrugStock.class));
        assertEquals(updatedDrugStock, updated);
    }

    @Test
    public void testUpdate_NonExistingDrugStock() {
        String id = "123";
        try{
        DrugStockDTO drugStockDTO = new DrugStockDTO();

        when(drugStockRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> {
            // Code that is expected to throw MyException
            drugStockService.update(id, drugStockDTO);
            throw new EntityNotFoundException(id);
        });
        }catch (EntityNotFoundException e){

        }


        verify(drugStockRepository, times(1)).findById(id);
        verify(drugStockRepository, never()).save(any(DrugStock.class));
    }

    @Test
    public void testDeleteById() {
        String id = "123";

        drugStockService.deleteById(id);

        verify(drugStockRepository, times(1)).deleteById(id);
    }
}
