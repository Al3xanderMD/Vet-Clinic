package com.example.MyVet.Pet;

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

import static org.mockito.Mockito.*;

public class PetControllerTest {

    @Mock
    private PetService petService;

    @InjectMocks
    private PetController petController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePet() {
        // Create a sample PetDto
        PetDto petDto = new PetDto();
        petDto.setName("Max");
        petDto.setSpecies("Dog");

        // Create a sample saved Pet
        Pet savedPet = new Pet();
        savedPet.setId("1");
        savedPet.setName("Max");
        savedPet.setSpecies("Dog");

        // Mock the behavior of the pet service
        when(petService.create(petDto)).thenReturn(savedPet);

        // Call the method under test
        ResponseEntity<Pet> response = petController.create(petDto);

        // Verify the interactions and assertions
        verify(petService, times(1)).create(petDto);
        Assertions.assertEquals(savedPet, response.getBody());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testGetAllPets() {
        // Create a sample list of pets
        List<Pet> pets = new ArrayList<>();
        pets.add(new Pet());
        pets.add(new Pet());

        // Mock the behavior of the pet service
        when(petService.getAll()).thenReturn(pets);

        // Call the method under test
        List<Pet> response = petController.getAll();

        // Verify the interactions and assertions
        verify(petService, times(1)).getAll();
        Assertions.assertEquals(pets, response);
    }

    @Test
    public void testGetPetByIdExisting() {
        // Create a sample pet
        Pet pet = new Pet();

        // Mock the behavior of the pet service
        when(petService.getById("1")).thenReturn(Optional.of(pet));

        // Call the method under test
        ResponseEntity<Pet> response = petController.getByID("1");

        // Verify the interactions and assertions
        verify(petService, times(1)).getById("1");
        Assertions.assertEquals(pet, response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetPetByIdNonExisting() {
        // Mock the behavior of the pet service
        when(petService.getById("1")).thenReturn(Optional.empty());

        // Call the method under test
        ResponseEntity<Pet> response = petController.getByID("1");

        // Verify the interactions and assertions
        verify(petService, times(1)).getById("1");
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testUpdatePetExisting() {
        // Create a sample PetDto
        PetDto petDto = new PetDto();
        petDto.setName("Max");
        petDto.setSpecies("Dog");

        // Create a sample updated Pet
        Pet updatedPet = new Pet();
        updatedPet.setId("1");
        updatedPet.setName("Max");
        updatedPet.setSpecies("Dog");

        // Mock the behavior of the pet service
        when(petService.update("1", petDto)).thenReturn(updatedPet);

        // Call the method under test
        ResponseEntity<Pet> response = petController.update("1", petDto);

        // Verify the interactions and assertions
        verify(petService, times(1)).update("1", petDto);
        Assertions.assertEquals(updatedPet, response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUpdatePetNonExisting() {
        // Create a sample PetDto
        PetDto petDto = new PetDto();
        petDto.setName("Max");
        petDto.setSpecies("Dog");

        // Mock the behavior of the pet service
        when(petService.update("1", petDto)).thenThrow(new EntityNotFoundException("Pet not found"));

        // Call the method under test
        ResponseEntity<Pet> response = petController.update("1", petDto);

        // Verify the interactions and assertions
        verify(petService, times(1)).update("1", petDto);
        Assertions.assertNull(response.getBody());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeletePet() {
        // Call the method under test
        ResponseEntity<Void> response = petController.deleteById("1");

        // Verify the interactions and assertions
        verify(petService, times(1)).deleteById("1");
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
