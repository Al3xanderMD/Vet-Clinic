package com.example.MyVet.UserCart.Receipt;

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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReceiptControllerTest {

    @Mock
    private ReceiptService receiptService;

    @InjectMocks
    private ReceiptController receiptController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll_shouldReturnListOfReceipts() {
        // Arrange
        List<Receipt> receipts = new ArrayList<>();
        when(receiptService.getAll()).thenReturn(receipts);

        // Act
        List<Receipt> result = receiptController.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(receipts, result);
        verify(receiptService, times(1)).getAll();
    }

    @Test
    void create_withValidReceiptDTO_shouldReturnCreatedResponse() {
        // Arrange
        ReceiptDTO receiptDTO = new ReceiptDTO();
        Receipt savedReceipt = new Receipt();
        when(receiptService.create(receiptDTO)).thenReturn(savedReceipt);

        // Act
        ResponseEntity<Receipt> response = receiptController.create(receiptDTO);

        // Assert
        assertNotNull(response);
        assertEquals(savedReceipt, response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(receiptService, times(1)).create(receiptDTO);
    }

    @Test
    void getById_withExistingId_shouldReturnReceipt() {
        // Arrange
        String id = "1";
        Receipt receipt = new Receipt();
        when(receiptService.getById(id)).thenReturn(Optional.of(receipt));

        // Act
        ResponseEntity<Receipt> response = receiptController.getById(id);

        // Assert
        assertNotNull(response);
        assertEquals(receipt, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(receiptService, times(1)).getById(id);
    }

    @Test
    void getById_withNonExistingId_shouldReturnNotFoundResponse() {
        // Arrange
        String id = "1";
        when(receiptService.getById(id)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Receipt> response = receiptController.getById(id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(receiptService, times(1)).getById(id);
    }

    @Test
    void update_withExistingId_shouldReturnUpdatedReceipt() {
        // Arrange
        String id = "1";
        ReceiptDTO receiptDTO = new ReceiptDTO();
        Receipt updatedReceipt = new Receipt();
        when(receiptService.update(id, receiptDTO)).thenReturn(updatedReceipt);

        // Act
        ResponseEntity<Receipt> response = receiptController.update(id, receiptDTO);

        // Assert
        assertNotNull(response);
        assertEquals(updatedReceipt, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(receiptService, times(1)).update(id, receiptDTO);
    }

    @Test
    void update_withNonExistingId_shouldReturnNotFoundResponse() {
        // Arrange
        String id = "1";
        ReceiptDTO receiptDTO = new ReceiptDTO();
        when(receiptService.update(id, receiptDTO)).thenThrow(EntityNotFoundException.class);

        // Act
        ResponseEntity<Receipt> response = receiptController.update(id, receiptDTO);

        // Assert
        assertNotNull(response);
        assertNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(receiptService, times(1)).update(id, receiptDTO);
    }

    @Test
    void deleteById_withExistingId_shouldReturnNoContentResponse() {
        // Arrange
        String id = "1";

        // Act
        ResponseEntity<Void> response = receiptController.deleteById(id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(receiptService, times(1)).deleteById(id);
    }
}
