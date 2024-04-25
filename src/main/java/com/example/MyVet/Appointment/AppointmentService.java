package com.example.MyVet.Appointment;

import com.example.MyVet.Exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    private final AppointmentMapper appointmentMapper = new AppointmentMapper();

    public Appointment create(AppointmentDto appointmentdto)
    {
        Appointment appointment = appointmentMapper.toAppointment(appointmentdto);
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAll()
    {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getById(String id)
    {
        return appointmentRepository.findById(id);
    }

    public Optional<Appointment> getByMinute(int year, int month, int day, int hour, int minute){
        return appointmentRepository.findByMinute(year, month, day, hour, minute);
    }
    public List<Appointment> getByDay(int year, int month, int day){
        return appointmentRepository.findByDay(year, month, day);
    }
    public Appointment update(String id, AppointmentDto appointmentDto)
    {
        Optional<Appointment> existingAppointment = appointmentRepository.findById(id);
        if(existingAppointment.isPresent())
        {
            var newApp = AppointmentMapper.toAppointment(appointmentDto);
            newApp.setId(existingAppointment.get().getId());
            newApp.setPetOwner(existingAppointment.get().getPetOwner());

            System.out.println(newApp);
            return appointmentRepository.save(newApp);
        } else {
            throw new EntityNotFoundException(id);
        }
    }
    public void deleteById(String id)
    {
        appointmentRepository.deleteById(id);
    }
}

