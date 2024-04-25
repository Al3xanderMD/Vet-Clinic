package com.example.MyVet.Appointment;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import com.example.MyVet.Appointment.Date.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(path = "/api/v1/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public  List<Appointment> getAll(){
        return appointmentService.getAll();
    }
    @GetMapping("/{id}")
    //@PreAuthorize("@appointmentService.canUserGetAppointment(principal, #id)")
    public Optional<Appointment> getById(@PathVariable @Valid @Min(0) String id) {
        return appointmentService.getById(id);
    }
    @GetMapping("/{petOwnerId}/{year}/{month}")
    public List<Status> getByMonth(@PathVariable @Valid String petOwnerId,
                                   @PathVariable @Valid int year, @PathVariable @Valid @Min(1) @Max(12) int month){
        List<Status> statusList = new ArrayList<>();
        Status status;
        for (int i = 1; i <= YearMonth.of(year, month).lengthOfMonth(); i++) {
            status = Status.available;
            List<Appointment> appointmentsByDay= appointmentService.getByDay(year, month, i);
            LocalDate givenDate = LocalDate.of(year, month, i);
            if(givenDate.isBefore(LocalDate.now()))
                status = Status.passed;
            else if(appointmentsByDay.size() == 16)
                status = Status.taken;
            for (Appointment appointment: appointmentsByDay)
                if(appointment.getPetOwner().getId().equals(petOwnerId)){
                    status = Status.mine;
                    break;
                }
            statusList.add(status);
        }
        return statusList;
    }
    @GetMapping("/{petOwnerId}/{year}/{month}/{day}")
    public List<Pair<Optional<Appointment>, Status>> getByDay(@PathVariable @Valid String petOwnerId,
                                                              @PathVariable @Valid int year,
                                                              @PathVariable @Valid  @Min(1) @Max(12) int month,
                                                              @PathVariable @Valid  @Min(1) @Max(31)int day){
        Status status;
        Optional<Appointment> optionalAppointment;
        Pair<Optional<Appointment>, Status> pair;
        List<Pair<Optional<Appointment>, Status>> pairList = new ArrayList<>();
        for (int i = 1; i <= 16; i++) {
            status = Status.available;
            optionalAppointment = appointmentService.getByMinute(year, month, day, 8+((i+1)/2), ((i+1)%2)*30);
            if(optionalAppointment.isPresent()){
                Appointment appointment = optionalAppointment.get();
                if(appointment.getPetOwner().getId().equals(petOwnerId))
                    status = Status.mine;
                else status = Status.taken;
            }
            LocalDateTime givenDateTime = LocalDateTime.of(year, month, day, 8+((i+1)/2), ((i+1)%2)*30);
            if (givenDateTime.isBefore(LocalDateTime.now()) &&
                    status != Status.mine)
                status = Status.passed;
            pair = Pair.of(optionalAppointment, status);
            pairList.add(pair);
        }
        return pairList;
    }
    @GetMapping("/{petOwnerId}/{year}/{month}/{day}/{hour}:{minute}")
    public Pair<Optional<Appointment>, Status> getByMinute(@PathVariable @Valid String petOwnerId,
                                                           @PathVariable @Valid int year,
                                                           @PathVariable @Valid @Min(1) @Max(12) int month,
                                                           @PathVariable @Valid @Min(1) @Max(31)int day,
                                                           @PathVariable @Valid @Min(9) @Max(16) int hour,
                                                           @PathVariable @Valid @Min(0) @Max(59) int minute){
        Status status = Status.available;
        Optional<Appointment> optionalAppointment = appointmentService.getByMinute(year, month, day, hour, minute);
        if(optionalAppointment.isPresent()){
            Appointment appointment = optionalAppointment.get();
            if(appointment.getPetOwner().getId().equals(petOwnerId))
                status = Status.mine;
            else status = Status.taken;
        }LocalDateTime givenDateTime = LocalDateTime.of(year, month, day, hour, minute);
        if (givenDateTime.isBefore(LocalDateTime.now()) &&
                status != Status.mine)
            status = Status.passed;
        return Pair.of(optionalAppointment, status);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //@PreAuthorize("@appointmentService.canUserCreateAppointment(principal, #appointmentDto)")
    public void create(@RequestBody @Valid AppointmentDto appointmentDto) {
        appointmentService.create(appointmentDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //@PreAuthorize("@appointmentService.canUserUpdateAppointment(principal, #id, #appointmentDto)")
    public void update(@PathVariable @Valid @Min(0) String id, @RequestBody @Valid AppointmentDto appointmentDto) {
        appointmentService.update(id, appointmentDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable @Valid @Min(0) String id) {
        appointmentService.deleteById(id);
    }
}