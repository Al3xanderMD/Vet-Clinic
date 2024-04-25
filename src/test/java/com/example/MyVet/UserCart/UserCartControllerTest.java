package com.example.MyVet.UserCart;

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

class UserCartControllerTest {

    @Mock
    private UserCartService userCartService;

    @InjectMocks
    private UserCartController userCartController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll_ReturnsListOfUserCarts() {
        // Arrange
        List<UserCart> userCarts = new ArrayList<>();
        when(userCartService.getAll()).thenReturn(userCarts);

        // Act
        List<UserCart> result = userCartController.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(userCarts, result);
    }

    @Test
    void create_ValidUserCartDTO_ReturnsCreatedUserCart() {
        // Arrange
        UserCartDTO userCartDTO = new UserCartDTO();
        UserCart savedUserCart = new UserCart();
        when(userCartService.create(userCartDTO)).thenReturn(savedUserCart);

        // Act
        ResponseEntity<UserCart> response = userCartController.create(userCartDTO);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedUserCart, response.getBody());
    }

    @Test
    void getById_ExistingUserCartId_ReturnsUserCart() {
        // Arrange
        String userCartId = "userCartId";
        UserCart userCart = new UserCart();
        when(userCartService.getById(userCartId)).thenReturn(Optional.of(userCart));

        // Act
        ResponseEntity<UserCart> response = userCartController.getById(userCartId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userCart, response.getBody());
    }

    @Test
    void getById_NonexistentUserCartId_ReturnsNotFound() {
        // Arrange
        String userCartId = "nonexistentId";
        when(userCartService.getById(userCartId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<UserCart> response = userCartController.getById(userCartId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void update_ExistingUserCart_ReturnsUpdatedUserCart() {
        // Arrange
        String userCartId = "userCartId";
        UserCartDTO userCartDTO = new UserCartDTO();
        UserCart updatedUserCart = new UserCart();
        when(userCartService.update(userCartId, userCartDTO)).thenReturn(updatedUserCart);

        // Act
        ResponseEntity<UserCart> response = userCartController.update(userCartId, userCartDTO);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUserCart, response.getBody());
    }

    @Test
    void update_NonexistentUserCart_ThrowsEntityNotFoundException() {
        // Arrange
        String userCartId = "nonexistentId";
        UserCartDTO userCartDTO = new UserCartDTO();
        when(userCartService.update(userCartId, userCartDTO)).thenThrow(EntityNotFoundException.class);

        // Act
        ResponseEntity<UserCart> response = userCartController.update(userCartId, userCartDTO);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deleteById_ExistingUserCartId_ReturnsNoContent() {
        // Arrange
        String userCartId = "userCartId";

        // Act
        ResponseEntity<Void> response = userCartController.deleteById(userCartId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    // Add more test cases as needed
}
