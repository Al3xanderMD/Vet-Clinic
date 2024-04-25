package com.example.MyVet.Pet;

import com.example.MyVet.Pet.MedicalHistory.MedicalHistory;
import com.example.MyVet.Pet.MedicalHistory.MedicalHistoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class PetMapperTest {

    @Mock
    private MedicalHistoryService medicalHistoryService;

    private PetMapper petMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        petMapper = new PetMapper(medicalHistoryService);
    }

    @Test
    public void testToPet() {
        // Arrange
        PetDto petDto = new PetDto();
        petDto.setName("Fluffy");
        petDto.setRace("Golden Retriever");
        petDto.setSpecies("Dog");
        petDto.setUIN(123456);
        petDto.setImageURL("https://example.com/image.png");
        petDto.setHasPassport(true);
        petDto.setHealthStatus("Healthy");
        petDto.setMedicalHistories(Arrays.asList("1", "2"));

        MedicalHistory mockMedicalHistory1 = new MedicalHistory();
        MedicalHistory mockMedicalHistory2 = new MedicalHistory();

        when(medicalHistoryService.getById("1")).thenReturn(Optional.of(mockMedicalHistory1));
        when(medicalHistoryService.getById("2")).thenReturn(Optional.of(mockMedicalHistory2));

        // Act
        Pet pet = PetMapper.toPet(petDto);

        // Assert
        Assertions.assertNotNull(pet);
        Assertions.assertEquals("Fluffy", pet.getName());
        Assertions.assertEquals("Golden Retriever", pet.getRace());
        Assertions.assertEquals("Dog", pet.getSpecies());
        Assertions.assertEquals(123456, pet.getUIN());
        Assertions.assertEquals("https://example.com/image.png", pet.getImageURL());
        Assertions.assertTrue(pet.getHasPassport());
        Assertions.assertEquals("Healthy", pet.getHealthStatus());

        List<MedicalHistory> expectedMedicalHistoryList = Arrays.asList(mockMedicalHistory1, mockMedicalHistory2);
        Assertions.assertEquals(expectedMedicalHistoryList, pet.getMedicalHistoryList());

        verify(medicalHistoryService, times(2)).getById(anyString());
    }
}

