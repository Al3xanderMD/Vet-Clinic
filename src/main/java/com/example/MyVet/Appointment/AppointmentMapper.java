package com.example.MyVet.Appointment;

import com.example.MyVet.Pet.*;
import com.example.MyVet.PetOwner.PetOwner;
import com.example.MyVet.PetOwner.PetOwnerMapper;
import com.example.MyVet.PetOwner.PetOwnerRepository;
import com.example.MyVet.PetOwner.PetOwnerService;
import com.example.MyVet.Vet.Vet;
import com.example.MyVet.Vet.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AppointmentMapper {

    static PetService petService;

    static VetService vetService;

    static PetOwnerService petOwnerService;

    @Autowired
    public AppointmentMapper(PetService petService,PetOwnerService petOwnerService, VetService vetService){
        this.petService = petService;
        this.petOwnerService = petOwnerService;
        this.vetService = vetService;
    }

    public static Appointment toAppointment(AppointmentDto appointmentDto){
        Appointment appointment = new Appointment();

        var petApp = petService.getById(appointmentDto.getPetId());
        petApp.ifPresent(appointment::setPet);

        //      if(petOwnerService.getById(appointmentDto.getOwnerId()).get().getPetList().contains(appointmentDto.getPetId()))
        //       {
        var ownerApp = petOwnerService.getById(appointmentDto.getOwnerId());
        ownerApp.ifPresent(appointment::setPetOwner);
        //       }

        var vetApp = vetService.getById(appointmentDto.getVetId());
        vetApp.ifPresent(appointment::setVet);

        appointment.setYear(appointmentDto.getYear());
        appointment.setMonth(appointmentDto.getMonth());
        appointment.setDay(appointmentDto.getDay());
        appointment.setHour(appointmentDto.getHour());
        appointment.setMinute(appointmentDto.getMinute());

        return appointment;
    }
}
