package com.example.MyVet.User.users;

import com.example.MyVet.Exceptions.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userController = new UserController(userService);
    }

    @Test
    void getAll_ReturnsListOfUsers() {
        // Arrange
        User user1 = new User();
        User user2 = new User();
        when(userService.getAll()).thenReturn(Arrays.asList(user1, user2));

        // Act
        List<User> result = userController.getAll();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(user1));
        assertTrue(result.contains(user2));

        verify(userService, times(1)).getAll();
    }

    @Test
    void getById_WithExistingId_ReturnsUser() {
        // Arrange
        String userId = "1";
        User user = new User();
        when(userService.getById(userId)).thenReturn(Optional.of(user));

        // Act
        ResponseEntity<User> response = userController.getById(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());

        verify(userService, times(1)).getById(userId);
    }

    @Test
    void getById_WithNonexistentId_ReturnsNotFound() {
        // Arrange
        String userId = "999";
        when(userService.getById(userId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<User> response = userController.getById(userId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(userService, times(1)).getById(userId);
    }

    @Test
    void deleteById_WithExistingId_ReturnsNoContent() {
        // Arrange
        String userId = "1";

        // Act
        ResponseEntity<Void> response = userController.deleteById(userId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(userService, times(1)).deleteById(userId);
    }

    @Test
    void deleteById_WithNonexistentId_ReturnsNoContent() {
        // Arrange
        String userId = "999";
//        doThrow(EntityNotFoundException.class).when(userService).deleteById(userId);

        // Act
        ResponseEntity<Void> response = userController.deleteById(userId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(userService, times(1)).deleteById(userId);
    }
    @Test
    public void testUpdateById_Success() throws EntityNotFoundException {
        // Prepare test data
        String id = "1";
        UserDTO userDTO = new UserDTO();
        User updatedUser = new User();

        // Set up mock behavior
        when(userService.updateById(eq(id), any(UserDTO.class))).thenReturn(updatedUser);

        // Perform the update
        ResponseEntity<User> responseEntity = userController.updateById(id, userDTO);

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedUser, responseEntity.getBody());

        // Verify that the UserService's updateById method was called
        verify(userService, times(1)).updateById(eq(id), any(UserDTO.class));
    }

    @Test
    public void testUpdateById_EntityNotFound() throws EntityNotFoundException {
        // Prepare test data
        String id = "1";
        UserDTO userDTO = new UserDTO();

        // Set up mock behavior
        when(userService.updateById(eq(id), any(UserDTO.class))).thenThrow(EntityNotFoundException.class);

        // Perform the update
        ResponseEntity<User> responseEntity = userController.updateById(id, userDTO);

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());

        // Verify that the UserService's updateById method was called
        verify(userService, times(1)).updateById(eq(id), any(UserDTO.class));
    }
}
