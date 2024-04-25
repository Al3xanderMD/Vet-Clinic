package com.example.MyVet.Vet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.MyVet.MedicalStaff.MedicalStaff;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VetServiceTest {
    @Mock
    private VetRepository vetRepository;

    @InjectMocks
    private VetService vetService;

    private Vet vet;
    private VetDTO vetDTO;

    @BeforeEach
    public void setUp() {
        MedicalStaff medicalStaff = new MedicalStaff();
        vet = new Vet(medicalStaff);
        vet.setId("1");

        vetDTO = new VetDTO();
//      vetDTO.setMedicalStaff(medicalStaff);
    }

    @Test
    public void testGetVet() {
        List<Vet> expectedVets = new ArrayList<>();
//        when(vetRepository.findAll()).thenReturn(expectedVets);

        List<Vet> vets = vetService.getVet();

        assertEquals(expectedVets, vets);
    }

    @Test
    public void testAdd() {
        when(vetRepository.save(any(Vet.class))).thenReturn(vet);

        Vet addedVet = vetService.add(vet);

        assertEquals(vet, addedVet);
        verify(vetRepository, times(1)).save(vet);
    }

    @Test
    public void testGetAll() {
        List<Vet> expectedVets = new ArrayList<>();
        when(vetRepository.findAll()).thenReturn(expectedVets);

        List<Vet> vets = vetService.getAll();

        assertEquals(expectedVets, vets);
    }

    @Test
    public void testGetById() {
        when(vetRepository.findById("1")).thenReturn(Optional.of(vet));

        Optional<Vet> foundVet = vetService.getById("1");

        assertEquals(Optional.of(vet), foundVet);
    }

    @Test
    public void testGetByIdNotFound() {
        when(vetRepository.findById(anyString())).thenReturn(Optional.empty());

        Optional<Vet> foundVet = vetService.getById("1");

        assertEquals(Optional.empty(), foundVet);
    }

    @Test
    public void testUpdate() {
        when(vetRepository.findById("1")).thenReturn(Optional.of(vet));
        when(vetRepository.save(any(Vet.class))).thenReturn(vet);

        Vet updatedVet = vetService.update("1", vetDTO);

        assertEquals(vet, updatedVet);
        verify(vetRepository, times(1)).save(vet);
    }

    @Test
    public void testUpdateVetNotFound() {
        when(vetRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> vetService.update("1", vetDTO));
    }

}
