package com.example.MyVet.UserCart.Receipt;

import com.example.MyVet.UserCart.Receipt.DrugSold.DrugSold;
import com.example.MyVet.UserCart.Receipt.DrugSold.DrugSoldService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReceiptMapperTest {

    @Mock
    private DrugSoldService drugSoldService;

    @InjectMocks
    private ReceiptMapper receiptMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testToReceipt() {
        // Prepare test data
        ReceiptDTO receiptDTO = new ReceiptDTO();
        receiptDTO.setDrugsSoldId(Arrays.asList("1", "2", "3"));

        DrugSold drugSold1 = new DrugSold();
        drugSold1.setPrice(10.0);

        DrugSold drugSold2 = new DrugSold();
        drugSold2.setPrice(15.0);

        DrugSold drugSold3 = new DrugSold();
        drugSold3.setPrice(20.0);

        List<DrugSold> drugSoldList = Arrays.asList(drugSold1, drugSold2, drugSold3);

        // Set up mock behavior
        when(drugSoldService.getById("1")).thenReturn(Optional.of(drugSold1));
        when(drugSoldService.getById("2")).thenReturn(Optional.of(drugSold2));
        when(drugSoldService.getById("3")).thenReturn(Optional.of(drugSold3));

        // Perform the mapping
        Receipt receipt = ReceiptMapper.toReceipt(receiptDTO);

        // Verify the mapping
        assertEquals(drugSoldList, receipt.getDrugSoldList());
        assertEquals(45.0, receipt.getPriceReceipt(), 0.0);

        // Verify that the DrugSoldService's getById method was called for each drug sold ID
        verify(drugSoldService, times(2)).getById("1");
        verify(drugSoldService, times(2)).getById("2");
        verify(drugSoldService, times(2)).getById("3");
    }
}
