package com.example.MyVet.DrugStock;

import com.example.MyVet.Drug.Drug;
import com.example.MyVet.Drug.DrugService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class DrugStockMapperTest {

    @Mock
    private DrugService drugService;

    @InjectMocks
    private DrugStockMapper drugStockMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testToDrugStock() {
        String drugId1 = "Drug1";
        String drugId2 = "Drug2";
        List<String> drugsId = new ArrayList<>();
        drugsId.add(drugId1);
        drugsId.add(drugId2);

        DrugStockDTO drugStockDTO = new DrugStockDTO(drugsId);

        Drug drug1 = new Drug();
        Drug drug2 = new Drug();
        List<Drug> drugList = new ArrayList<>();
        drugList.add(drug1);
        drugList.add(drug2);

        when(drugService.getById(drugId1)).thenReturn(Optional.of(drug1));
        when(drugService.getById(drugId2)).thenReturn(Optional.of(drug2));

        DrugStock drugStock = drugStockMapper.toDrugStock(drugStockDTO);

        verify(drugService, times(1)).getById(drugId1);
        verify(drugService, times(1)).getById(drugId2);
        assertEquals(drugList, drugStock.getDrugList());
    }

    @Test
    public void testToDrugStock_DrugServiceReturnsEmptyOptional() {
        String drugId = "Drug1";
        List<String> drugsId = new ArrayList<>();
        drugsId.add(drugId);

        DrugStockDTO drugStockDTO = new DrugStockDTO(drugsId);

        when(drugService.getById(drugId)).thenReturn(Optional.empty());

        DrugStock drugStock = drugStockMapper.toDrugStock(drugStockDTO);

        verify(drugService, times(1)).getById(drugId);
        assertTrue(drugStock.getDrugList().isEmpty());
    }
}
