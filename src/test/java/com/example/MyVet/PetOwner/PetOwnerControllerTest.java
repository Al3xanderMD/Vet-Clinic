package com.example.MyVet.PetOwner;

import com.example.MyVet.Exceptions.EntityNotFoundException;
import com.example.MyVet.User.users.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PetOwnerControllerTest {
    private PetOwnerService petOwnerService = mock(PetOwnerService.class);
    private PetOwnerController petOwnerController = new PetOwnerController(petOwnerService);
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdate_Success() {
        // Arrange
        String petOwnerId = "123456";
        PetOwnerDTO petOwnerDTO = new PetOwnerDTO();
        PetOwner updatedPetOwner = new PetOwner();
        updatedPetOwner.setId(petOwnerId);

        when(petOwnerService.update(anyString(), any(PetOwnerDTO.class))).thenReturn(updatedPetOwner);

        // Act
        ResponseEntity<PetOwner> response = petOwnerController.update(petOwnerId, petOwnerDTO);

        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(updatedPetOwner, response.getBody());
        verify(petOwnerService, times(1)).update(petOwnerId, petOwnerDTO);
    }

    @Test
    public void testUpdate_EntityNotFound() {
        // Arrange
        String petOwnerId = "123456";
        PetOwnerDTO petOwnerDTO = new PetOwnerDTO();

        doThrow(new EntityNotFoundException("ajjaj","1")).when(petOwnerService).update(anyString(), any(PetOwnerDTO.class));

        // Act
        ResponseEntity<PetOwner> response = petOwnerController.update(petOwnerId, petOwnerDTO);

        // Assert
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNull(response.getBody());
        verify(petOwnerService, times(1)).update(petOwnerId, petOwnerDTO);
    }



    @Test
    void testGetAllPetOwners() {
        // Create a list of PetOwners
        PetOwner petOwner1 = new PetOwner();
        User user = new User("fname1", "lname1", "email1", "2345678", "2345678", "bau1", "wedrfgthy");
        petOwner1.setUser(user);
        User user2 = new User("fname2", "lname2", "email2", "2345678", "2345678", "bau2", "wedrfgthy");
        PetOwner petOwner2 = new PetOwner();
        petOwner2.setUser(user2);

        List<PetOwner> petOwners = Arrays.asList(petOwner1, petOwner2);

        // Configure the PetOwnerService mock to return the list of PetOwners
        when(petOwnerService.getAll()).thenReturn(petOwners);

        // Call the getAll method of PetOwnerController
        List<PetOwner> result = petOwnerController.getAll();

        // Verify the returned list of PetOwners
        assertEquals(petOwners, result);
    }

    @Test
    void testGetPetOwnerByIdExisting() {
        // Create a PetOwner object
        PetOwner petOwner = new PetOwner();
//        petOwner.setId("1");
//        petOwner.setName("John Doe");
        // Configure the PetOwnerService mock to return an Optional containing the PetOwner
        when(petOwnerService.getById("1")).thenReturn(Optional.of(petOwner));

        // Call the getById method of PetOwnerController
        ResponseEntity<PetOwner> response = petOwnerController.getByID("1");

        // Verify the response status and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(petOwner, response.getBody());
    }

    @Test
    void testGetPetOwnerByIdNonExisting() {
        // Configure the PetOwnerService mock to return an empty Optional
        when(petOwnerService.getById("1")).thenReturn(Optional.empty());

        // Call the getById method of PetOwnerController
        ResponseEntity<PetOwner> response = petOwnerController.getByID("1");

        // Verify the response status
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}
