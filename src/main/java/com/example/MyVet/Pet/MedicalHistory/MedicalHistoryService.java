package com.example.MyVet.Pet.MedicalHistory;

import com.example.MyVet.Exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalHistoryService {
    private final MedicalHistoryMapper medicalHistoryMapper = new MedicalHistoryMapper();

    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;

    public MedicalHistory create(MedicalHistoryDTO medicalHistoryDTO) {
        return medicalHistoryRepository.save(medicalHistoryMapper.toMedicalHistory(medicalHistoryDTO));
    }

    public List<MedicalHistory> getAll() {
        return medicalHistoryRepository.findAll();
    }

    public Optional<MedicalHistory> getById(String id) {
        return medicalHistoryRepository.findById(id);
    }

    public MedicalHistory update(String id, MedicalHistoryDTO medicalHistoryDTO) {
        Optional<MedicalHistory> existingMedicalHistory = medicalHistoryRepository.findById(id);

        if(existingMedicalHistory.isPresent()){
            var newMedicalHistory = medicalHistoryMapper.toMedicalHistory(medicalHistoryDTO);
            newMedicalHistory.setId(existingMedicalHistory.get().getId());
            return medicalHistoryRepository.save(newMedicalHistory);
        }else{
            throw new EntityNotFoundException(id);
        }
    }

    public void deleteById(String id) {
        medicalHistoryRepository.deleteById(id);
    }
}
