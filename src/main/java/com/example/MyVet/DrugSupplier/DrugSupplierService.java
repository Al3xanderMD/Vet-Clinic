package com.example.MyVet.DrugSupplier;

import com.example.MyVet.Exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DrugSupplierService {

    private final DrugSupplierMapper drugSupplierMapper = new DrugSupplierMapper();

    @Autowired
    private DrugSupplierRepository drugSupplierRepository;

    public DrugSupplier create(DrugSupplierDTO drugSupplierDTO){
        return  drugSupplierRepository.save(drugSupplierMapper.toDrugSupplier(drugSupplierDTO));
    }

    public List<DrugSupplier> getAllDrugSuppliers(){
        return drugSupplierRepository.findAll();
    }

    public Optional<DrugSupplier> getDrugSupplierById(String id)
    {
        return drugSupplierRepository.findById(id);
    }

    public DrugSupplier updateDrugSupplier(String id, DrugSupplierDTO drugSupplierDTO)
    {
        Optional<DrugSupplier> existingDS = drugSupplierRepository.findById(id);

        if(existingDS.isPresent())
        {
            var newDs = DrugSupplierMapper.toDrugSupplier(drugSupplierDTO);
            newDs.setId(existingDS.get().getId());
            return drugSupplierRepository.save(newDs);
        } else {
            throw new EntityNotFoundException(id);
        }
    }

    public void deleteDrugSupplierById(String id){
        drugSupplierRepository.deleteById(id);
    }
}
