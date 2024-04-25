package com.example.MyVet.UserCart.Receipt;

import com.example.MyVet.Exceptions.EntityNotFoundException;
import com.example.MyVet.UserCart.Receipt.DrugSold.DrugSold;
import com.example.MyVet.UserCart.Receipt.DrugSold.DrugSoldService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReceiptServiceTest {

    @Mock
    private ReceiptRepository receiptRepository;

    @Mock
    private DrugSoldService drugSoldService;

    @InjectMocks
    private ReceiptService receiptService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_withValidReceiptDTO_shouldReturnCreatedReceipt() {
        // Arrange
        ReceiptDTO receiptDTO = new ReceiptDTO();
        Receipt expectedReceipt = new Receipt();
        when(receiptRepository.save(any())).thenReturn(expectedReceipt);

        // Act
        Receipt createdReceipt = receiptService.create(receiptDTO);

        // Assert
        assertNotNull(createdReceipt);
        assertEquals(expectedReceipt, createdReceipt);
        verify(receiptRepository, times(1)).save(any());
    }

    @Test
    void getAll_shouldReturnListOfReceipts() {
        // Arrange
        List<Receipt> receipts = new ArrayList<>();
        when(receiptRepository.findAll()).thenReturn(receipts);

        // Act
        List<Receipt> result = receiptService.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(receipts, result);
        verify(receiptRepository, times(1)).findAll();
    }

    @Test
    void getById_withExistingId_shouldReturnReceipt() {
        // Arrange
        String id = "1";
        Receipt receipt = new Receipt();
        when(receiptRepository.findById(id)).thenReturn(Optional.of(receipt));

        // Act
        Optional<Receipt> result = receiptService.getById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(receipt, result.get());
        verify(receiptRepository, times(1)).findById(id);
    }

    @Test
    void getById_withNonExistingId_shouldReturnEmptyOptional() {
        // Arrange
        String id = "1";
        when(receiptRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<Receipt> result = receiptService.getById(id);

        // Assert
        assertFalse(result.isPresent());
        verify(receiptRepository, times(1)).findById(id);
    }

    @Test
    void update_withExistingIdAndValidReceiptDTO_shouldReturnUpdatedReceipt() {
        // Arrange
        String id = "1";
        ReceiptDTO receiptDTO = new ReceiptDTO();
        Receipt existingReceipt = new Receipt();
        Receipt updatedReceipt = new Receipt();
        when(receiptRepository.findById(id)).thenReturn(Optional.of(existingReceipt));
        when(receiptRepository.save(any())).thenReturn(updatedReceipt);

        // Act
        Receipt result = receiptService.update(id, receiptDTO);

        // Assert
        assertNotNull(result);
        assertEquals(updatedReceipt, result);
        verify(receiptRepository, times(1)).findById(id);
        verify(receiptRepository, times(1)).save(any());
    }

    @Test
    void update_withNonExistingId_shouldThrowEntityNotFoundException() {
        // Arrange
        String id = "1";
        ReceiptDTO receiptDTO = new ReceiptDTO();
        when(receiptRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> receiptService.update(id, receiptDTO));
        verify(receiptRepository, times(1)).findById(id);
        verify(receiptRepository, never()).save(any());
    }

    @Test
    void deleteById_withExistingId_shouldDeleteReceipt() {
        // Arrange
        String id = "1";

        // Act
        receiptService.deleteById(id);

        // Assert
        verify(receiptRepository, times(1)).deleteById(id);
    }
}
