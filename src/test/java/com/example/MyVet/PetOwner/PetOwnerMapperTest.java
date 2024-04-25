package com.example.MyVet.PetOwner;

import com.example.MyVet.Pet.Pet;
import com.example.MyVet.Pet.PetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class PetOwnerMapperTest {


    @Mock
    private PetService petService;

    @InjectMocks
    private PetOwnerMapper petOwnerMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testToPetOwner() {
        // Arrange
        PetOwnerDTO petOwnerDTO = new PetOwnerDTO();
        petOwnerDTO.setPetsId(Arrays.asList("1L", "2L", "3L"));

        // Mock the behavior of the PetService
        Pet pet1 = new Pet();
        Pet pet2 = new Pet();
        Pet pet3 = new Pet();
        when(petService.getById("1L")).thenReturn(Optional.of(pet1));
        when(petService.getById("2L")).thenReturn(Optional.of(pet2));
        when(petService.getById("3L")).thenReturn(Optional.of(pet3));

        // Act
        PetOwner petOwner = petOwnerMapper.toPetOwner(petOwnerDTO);

        // Assert
        List<Pet> expectedPets = Arrays.asList(pet1, pet2, pet3);
        assertEquals(expectedPets, petOwner.getPetList());
    }
    @Test
    public void testDefaultConstructor() {
        // Arrange
        List<Pet> mockPetList = new ArrayList<>();
        PetOwnerMapper petOwnerMapper = new PetOwnerMapper();

        // Act
        petOwnerMapper.toPetOwner(new PetOwnerDTO());

        // Assert
        verify(petService, times(0)).getById(anyString());
        Assertions.assertEquals(mockPetList, petOwnerMapper.toPetOwner(new PetOwnerDTO()).getPetList());
    }

}
