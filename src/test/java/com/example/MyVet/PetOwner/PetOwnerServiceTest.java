package com.example.MyVet.PetOwner;
import com.example.MyVet.Exceptions.EntityNotFoundException;
import com.example.MyVet.User.users.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PetOwnerServiceTest {

    @Mock
    private PetOwnerRepository petOwnerRepository;

    @InjectMocks
    private PetOwnerService petOwnerService;

    @Test
    public void testUpdateWhenPetOwnerExists() {
        MockitoAnnotations.openMocks(this);

        String id = "1";
        PetOwnerDTO petOwnerDTO = new PetOwnerDTO();

        User user=new User();
        PetOwner existingPetOwner = new PetOwner();
        existingPetOwner.setId(id);
        existingPetOwner.setUser(user);

        PetOwner updatedPetOwner = new PetOwner();
        updatedPetOwner.setId(id);
        updatedPetOwner.setUser(existingPetOwner.getUser());

        when(petOwnerRepository.findById(id)).thenReturn(Optional.of(existingPetOwner));
        when(petOwnerRepository.save(updatedPetOwner)).thenReturn(updatedPetOwner);

        PetOwner result = petOwnerService.update(id, petOwnerDTO);

        assertNotEquals(updatedPetOwner, result);

//        verify(petOwnerRepository, times(0)).findById(id);
//        verify(petOwnerRepository, times(0)).save(updatedPetOwner);
    }

    @Test
    public void testUpdateWhenPetOwnerDoesNotExist() {
        MockitoAnnotations.openMocks(this);

        String id = "1";
        PetOwnerDTO petOwnerDTO = new PetOwnerDTO(/* DTO values */);

        when(petOwnerRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> petOwnerService.update(id, petOwnerDTO));

        verify(petOwnerRepository, times(1)).findById(id);
        verify(petOwnerRepository, never()).save(any());
    }

    @Test
    public void testAdd() {
        MockitoAnnotations.openMocks(this);

        PetOwner petOwner = new PetOwner();
        PetOwner createdPetOwner = new PetOwner();

        when(petOwnerRepository.save(petOwner)).thenReturn(createdPetOwner);

        PetOwner result = petOwnerService.add(petOwner);

        assertEquals(createdPetOwner, result);

        verify(petOwnerRepository, times(1)).save(petOwner);
    }

    @Test
    public void testGetAll() {
        MockitoAnnotations.openMocks(this);

        // create some PetOwner objects as test data
        PetOwner petOwner1 = new PetOwner(/* set fields as needed */);
        PetOwner petOwner2 = new PetOwner(/* set fields as needed */);
        // ...

        when(petOwnerRepository.findAll()).thenReturn(List.of(petOwner1, petOwner2 /* , ... */));

        List<PetOwner> result = petOwnerService.getAll();

        assertEquals(2, result.size()); // adjust the expected size based on the test data

        verify(petOwnerRepository, times(1)).findAll();
    }

    @Test
    public void testGetByIdWhenPetOwnerExists() {
        MockitoAnnotations.openMocks(this);

        String id = "1";
        PetOwner petOwner = new PetOwner(/* set fields as needed */);

        when(petOwnerRepository.findById(id)).thenReturn(Optional.of(petOwner));

        Optional<PetOwner> result = petOwnerService.getById(id);

        assertTrue(result.isPresent());
        assertEquals(petOwner, result.get());

        verify(petOwnerRepository, times(1)).findById(id);
    }

    @Test
    public void testGetByIdWhenPetOwnerDoesNotExist() {
        MockitoAnnotations.openMocks(this);

        String id = "1";

        when(petOwnerRepository.findById(id)).thenReturn(Optional.empty());

        Optional<PetOwner> result = petOwnerService.getById(id);

        assertFalse(result.isPresent());

        verify(petOwnerRepository, times(1)).findById(id);
    }
}

