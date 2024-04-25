package com.example.MyVet.Appointment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class AppointmentControllerTest {

    @Mock
    private AppointmentService appointmentService;

    @InjectMocks
    private AppointmentController appointmentController;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        appointmentController = new AppointmentController(appointmentService);
    }

    @Test
    public void testGetAllAppointments() {
        List<Appointment> appointmentList = new ArrayList<>();

        var app1 = new Appointment();
        var app2 = new Appointment();

        app1.setId("01");
        app2.setId("02");

        when(appointmentService.getAll()).thenReturn(appointmentList);

        List<Appointment> result = appointmentController.getAll();

        assertEquals(appointmentList, result);
    }

    @Test
    public void testGetAppointmentById() {
        String appointmentId = "1";
        Appointment appointment = new Appointment();
        appointment.setId(appointmentId);
        Optional<Appointment> optionalAppointment = Optional.of(appointment);

        when(appointmentService.getById(appointmentId)).thenReturn(optionalAppointment);

//        Optional<Appointment> response = appointmentController.getById(appointmentId);
        Optional<Appointment> response = appointmentController.getById(appointmentId);
        assertEquals(response.get().getId(), appointmentId);
//        assertEquals(optionalAppointment, response.getBody());
    }

    @Test
    public void testCreateAppointment() {
        AppointmentDto appointmentDto = new AppointmentDto();

        appointmentDto.setDay(1);
        appointmentDto.setHour(2);
        appointmentDto.setMinute(3);
        appointmentDto.setYear(4);
        appointmentDto.setMonth(5);
        appointmentDto.setOwnerId("01");
        appointmentDto.setPetId("02");
        appointmentDto.setVetId("03");


        appointmentController.create(appointmentDto);

        Mockito.verify(appointmentService).create(appointmentDto);
    }

    @Test
    public void testUpdateAppointment() {
        String appointmentId = "1";
        AppointmentDto appointmentDto = new AppointmentDto();

        appointmentDto.setDay(1);
        appointmentDto.setHour(2);
        appointmentDto.setMinute(3);
        appointmentDto.setYear(4);
        appointmentDto.setMonth(5);
        appointmentDto.setOwnerId("01");
        appointmentDto.setPetId("02");
        appointmentDto.setVetId("03");

        appointmentController.update(appointmentId, appointmentDto);

        // Verify that the update method was called with the correct appointmentId and appointmentDto
        Mockito.verify(appointmentService).update(appointmentId, appointmentDto);
    }

    @Test
    public void testDeleteAppointment() {
        String appointmentId = "1";

        appointmentController.deleteById(appointmentId);

        // Verify that the deleteById method was called with the correct appointmentId
        Mockito.verify(appointmentService).deleteById(appointmentId);
    }
}
