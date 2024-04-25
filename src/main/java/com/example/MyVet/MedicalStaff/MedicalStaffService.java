package com.example.MyVet.MedicalStaff;

import com.example.MyVet.Exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MedicalStaffService {
    private final MedicalStaffMapper medicalStaffMapper = new MedicalStaffMapper();

    @Autowired
    private MedicalStaffRepository medicalStaffRepository;

    public List<MedicalStaff> getMedicalStaff() {
        return List.of();
    }
    public MedicalStaff add(MedicalStaff medicalStaff){
        return  medicalStaffRepository.save(medicalStaff);
    }

    public List<MedicalStaff> getAll(){
        return medicalStaffRepository.findAll();
    }

    public Optional<MedicalStaff> getById(String id)
    {
        return medicalStaffRepository.findById(id);
    }

    public MedicalStaff update(String id, MedicalStaffDTO medicalStaffDTO) {
        Optional<MedicalStaff> existingMedicalStaffs = medicalStaffRepository.findById(id);


        if (existingMedicalStaffs.isPresent()) {
            var newMS  = MedicalStaffMapper.toMedicalStaff(medicalStaffDTO);
            newMS.setUser(existingMedicalStaffs.get().getUser());
            Map<WeekDay, Pair<Integer, Integer>> schedule = new HashMap();
            for (WeekDay day: WeekDay.values()
            ) {
                if(day == WeekDay.Saturday || day == WeekDay.Sunday)
                    schedule.put(day, Pair.of(8, 12));
                else schedule.put(day, Pair.of(8, 16));
            }
            newMS.setSchedule(schedule);
            newMS.setId(existingMedicalStaffs.get().getId());
            return medicalStaffRepository.save(newMS);
        } else {
            throw new EntityNotFoundException(id);
        }
    }
}
