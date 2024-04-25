package com.example.MyVet.Consumption;

import com.example.MyVet.Appointment.Appointment;
import com.example.MyVet.Exceptions.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.NoArgsConstructor;
import lombok.Data;
import java.util.List;
import java.util.Optional;

@Data
@Service
@NoArgsConstructor
public class ConsumptionService {
    private final ConsumptionMapper consumptionMapper = new ConsumptionMapper();

    @Autowired
    private ConsumptionRepository consumptionRepository;

    public Consumption create(ConsumptionDTO consumptionDTO)
    {
        return consumptionRepository.save(consumptionMapper.toConsumption(consumptionDTO));
    }

    public List<Consumption> getAll()
    {
        return consumptionRepository.findAll();
    }

    public Optional<Consumption> getById(String id)
    {
        return consumptionRepository.findById(id);
    }

    public List<Consumption> getByYear(int year, String drugId){
        return consumptionRepository.findByYear(year, drugId);
    }

    public List<Consumption> getByDrug(String drugId){
        return consumptionRepository.findByDrug(drugId);
    }

    public Consumption update(String id, ConsumptionDTO consumptionDTO)
    {
        Optional<Consumption> existingConsumption = consumptionRepository.findById(id);

        if(existingConsumption.isPresent())
        {
            var newCons = ConsumptionMapper.toConsumption(consumptionDTO);
            newCons.setDrugId(existingConsumption.get().getDrugId());

            return consumptionRepository.save(newCons);
        } else {
            throw new EntityNotFoundException(id);
        }
    }

    public void deleteById(String id)
    {
        consumptionRepository.deleteById(id);
    }
}