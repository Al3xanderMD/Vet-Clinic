package com.example.MyVet.Appointment;

import com.example.MyVet.Pet.Pet;
import com.example.MyVet.Pet.PetService;
import com.example.MyVet.PetOwner.PetOwner;
import com.example.MyVet.PetOwner.PetOwnerService;
import com.example.MyVet.Vet.Vet;
import com.example.MyVet.Vet.VetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class AppointmentMapperTest {

    @Mock
    private PetService petService;

    @Mock
    private VetService vetService;

    @Mock
    private PetOwnerService petOwnerService;

    private AppointmentMapper appointmentMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        appointmentMapper = new AppointmentMapper(petService, petOwnerService, vetService);
    }

    @Test
    public void testToAppointment() {
        AppointmentDto appointmentDto = new AppointmentDto("petId", "ownerId", "vetId", 2023, 5, 25, 10, 30);

        Pet pet = new Pet();
        when(petService.getById(appointmentDto.getPetId())).thenReturn(Optional.of(pet));

        PetOwner petOwner = new PetOwner();
        when(petOwnerService.getById(appointmentDto.getOwnerId())).thenReturn(Optional.of(petOwner));

        Vet vet = new Vet();
        when(vetService.getById(appointmentDto.getVetId())).thenReturn(Optional.of(vet));

        Appointment appointment = appointmentMapper.toAppointment(appointmentDto);

        assertEquals(pet, appointment.getPet());
        assertEquals(petOwner, appointment.getPetOwner());
        assertEquals(vet, appointment.getVet());
        assertEquals(2023, appointment.getYear());
        assertEquals(5, appointment.getMonth());
        assertEquals(25, appointment.getDay());
        assertEquals(10, appointment.getHour());
        assertEquals(30, appointment.getMinute());

        verify(petService, times(1)).getById(appointmentDto.getPetId());
        verify(petOwnerService, times(1)).getById(appointmentDto.getOwnerId());
        verify(vetService, times(1)).getById(appointmentDto.getVetId());
    }
}

