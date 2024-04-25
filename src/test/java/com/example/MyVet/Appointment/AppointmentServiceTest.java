package com.example.MyVet.Appointment;

import com.example.MyVet.Pet.PetService;
import com.example.MyVet.PetOwner.PetOwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.example.MyVet.Vet.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;
    @Mock
    private VetService vetService;
    @Mock
    private PetOwnerService petOwnerService;
    @Mock
    private PetService petService;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
//
//    @Test
//    public void testCreateAppointment() {
//        AppointmentDto appointmentDto = new AppointmentDto();
//
//        appointmentDto.setDay(1);
//        appointmentDto.setHour(2);
//        appointmentDto.setMinute(3);
//        appointmentDto.setYear(4);
//        appointmentDto.setMonth(5);
//        appointmentDto.setOwnerId("01");
//        appointmentDto.setPetId("02");
//        appointmentDto.setVetId("03");
//
//        Appointment appointment = new Appointment();
//
//        appointment.setDay(1);
//        appointment.setHour(2);
//        appointment.setMinute(3);
//        appointment.setYear(4);
//        appointment.setMonth(5);
//
//        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);
//
//        Appointment result = appointmentService.create(appointmentDto);
//
//        assertEquals(appointment, result);
//    }

    @Test
    public void testGetAllAppointments() {
        List<Appointment> appointmentList = new ArrayList<>();

        var app1 = new Appointment();
        var app2 = new Appointment();

        app1.setId("1");
        app2.setId("2");
        appointmentList.addAll(List.of(app1, app2));

        when(appointmentRepository.findAll()).thenReturn(appointmentList);

        List<Appointment> result = appointmentService.getAll();

        assertEquals(appointmentList, result);
    }

    @Test
    public void testGetAppointmentById() {
        String appointmentId = "1";
        Appointment appointment = new Appointment();
        appointment.setId(appointmentId);
        Optional<Appointment> optionalAppointment = Optional.of(appointment);

        when(appointmentRepository.findById(appointmentId)).thenReturn(optionalAppointment);

        Optional<Appointment> result = appointmentService.getById(appointmentId);

        assertEquals(optionalAppointment, result);
    }

//    @Test
//    public void testUpdateAppointment() {
//        String appointmentId = "1";
//        AppointmentDto appointmentDto = new AppointmentDto();
//
//
//        Appointment existingAppointment = new Appointment();
//        existingAppointment.setId(appointmentId);
//
//        existingAppointment.setId("1");
//        existingAppointment.setDay(1);
//        existingAppointment.setHour(2);
//        existingAppointment.setMinute(3);
//        existingAppointment.setYear(4);
//        existingAppointment.setMonth(5);
//
//        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(existingAppointment));
//        when(appointmentRepository.save(any(Appointment.class))).thenReturn(existingAppointment);
//
//        Appointment result = appointmentService.update(appointmentId, appointmentDto);
//
//        assertEquals(existingAppointment, result);
//    }

    @Test
    public void testDeleteAppointment() {
        String appointmentId = "1";

        appointmentService.deleteById(appointmentId);

        Mockito.verify(appointmentRepository).deleteById(appointmentId);
    }
}
