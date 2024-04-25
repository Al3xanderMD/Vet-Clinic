package com.example.MyVet.DrugStock;

import com.example.MyVet.Exceptions.EntityNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class DrugStockService {
    private final DrugStockMapper drugStockMapper = new DrugStockMapper();

    @Autowired
    private DrugStockRepository drugStockRepository;

    public DrugStock create(DrugStockDTO drugStockDTO) {
        return drugStockRepository.save(drugStockMapper.toDrugStock(drugStockDTO));
    }

    public List<DrugStock> getAll() {
        return drugStockRepository.findAll();
    }

    public Optional<DrugStock> getById(String id) {
        return drugStockRepository.findById(id);
    }
    public DrugStock update(String id, DrugStockDTO drugStockDTO) {
        Optional<DrugStock> existingDS = drugStockRepository.findById(id);

        if(existingDS.isPresent())
        {
            var newDS = DrugStockMapper.toDrugStock(drugStockDTO);
            newDS.setId(existingDS.get().getId());
            return drugStockRepository.save(newDS);
        } else {
            throw new EntityNotFoundException(id);
        }
    }
    public void deleteById(String id) {
        drugStockRepository.deleteById(id);
    }
}