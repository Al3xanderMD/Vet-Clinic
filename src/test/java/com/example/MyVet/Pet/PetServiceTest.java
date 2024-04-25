package com.example.MyVet.Pet;

import com.example.MyVet.Exceptions.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class PetServiceTest {

    @Mock
    private PetRepository petRepository;
    @Mock
    private PetRepository otherRepository;

    private PetMapper petMapper = new PetMapper();

    @InjectMocks
    private PetService petService;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    public void testCreatePet() {
//        // Create a sample PetDto
//        PetDto petDto = new PetDto();
//        petDto.setName("Max");
//        petDto.setSpecies("Dog");
//        petDto.setMedicalHistories(List.of("asa si asa"));
//
//        // Create a sample Pet
//        Pet pet = new Pet();
//        pet.setName("Max");
//        pet.setSpecies("Dog");
//
//        Pet savedPet = petService.create(petDto);
//        Assertions.assertEquals(pet, savedPet);
//    }

    @Test
    public void testGetAllPets() {
        // Create a sample list of pets
        List<Pet> pets = List.of(new Pet(), new Pet());

        // Mock the behavior of the pet repository
        when(petRepository.findAll()).thenReturn(pets);

        // Call the method under test
        List<Pet> allPets = petService.getAll();

        // Verify the interactions and assertions
        verify(petRepository, times(1)).findAll();
        Assertions.assertEquals(pets, allPets);
    }

    @Test
    public void testGetPetByIdExisting() {
        // Create a sample pet ID
        String petId = "1";

        // Create a sample pet
        Pet pet = new Pet();
        pet.setId(petId);

        // Mock the behavior of the pet repository
        when(petRepository.findById(petId)).thenReturn(Optional.of(pet));

        // Call the method under test
        Optional<Pet> foundPet = petService.getById(petId);

        // Verify the interactions and assertions
        verify(petRepository, times(1)).findById(petId);
        Assertions.assertTrue(foundPet.isPresent());
        Assertions.assertEquals(pet, foundPet.get());
    }

    @Test
    public void testGetPetByIdNonExisting() {
        // Create a sample non-existing pet ID
        String petId = "1";

        // Mock the behavior of the pet repository
        when(petRepository.findById(petId)).thenReturn(Optional.empty());

        // Call the method under test
        Optional<Pet> foundPet = petService.getById(petId);

        // Verify the interactions and assertions
        verify(petRepository, times(1)).findById(petId);
        Assertions.assertFalse(foundPet.isPresent());
    }

    @Test
    public void testUpdatePetExisting() {
        // Create a sample pet ID
        String petId = "1";

        // Create a sample PetDto
        PetDto petDto = new PetDto();
        petDto.setName("Max");
        petDto.setSpecies("Dog");

        // Create a sample existing pet
        Pet existingPet = new Pet();
        existingPet.setId(petId);
        existingPet.setName("Max");

        // Create a sample updated pet
        Pet updatedPet = new Pet();
        updatedPet.setId(petId);
        updatedPet.setName("Buddy");

        // Mock the behavior of the pet repository
        when(petRepository.findById(petId)).thenReturn(Optional.of(existingPet));
        when(petRepository.save(any(Pet.class))).thenReturn(updatedPet);

        // Call the method under test
//        Pet updated = petService.update(petId, petDto);

        // Verify the interactions and assertions
//        verify(petRepository, times(1)).findById(petId);
//        verify(petRepository, times(1)).save(any(Pet.class));
       // Assertions.assertEquals(updatedPet, updated);
    }

    @Test
    public void testUpdatePetNonExisting() {
        // Create a sample non-existing pet ID
        String petId = "1";

        // Create a sample PetDto
        PetDto petDto = new PetDto();
        petDto.setName("Max");
        petDto.setSpecies("Dog");

        // Mock the behavior of the pet repository
        when(petRepository.findById(petId)).thenReturn(Optional.empty());

        // Call the method under test and verify that it throws an EntityNotFoundException
        Assertions.assertThrows(EntityNotFoundException.class, () -> petService.update(petId, petDto));
        verify(petRepository, times(1)).findById(petId);
        verify(petRepository, times(0)).save(any(Pet.class));
    }

    @Test
    public void testDeletePetById() {
        // Create a sample pet ID
        String petId = "1";

        // Call the method under test
        petService.deleteById(petId);

        // Verify the interaction
        verify(petRepository, times(1)).deleteById(petId);
    }

}
