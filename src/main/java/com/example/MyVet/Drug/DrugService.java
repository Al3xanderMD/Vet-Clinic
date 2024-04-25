package com.example.MyVet.Drug;

import com.example.MyVet.Exceptions.EntityNotFoundException;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class DrugService {

    private final DrugMapper drugMapper = new DrugMapper();

    @Autowired
    private DrugRepository drugRepository;

    public Drug create(DrugDTO drugDTO) {
        return drugRepository.save(drugMapper.toDrug(drugDTO));
    }
    public List<Drug> getAll() {
        return  drugRepository.findAll();
    }

    public Optional<Drug> getById(String id) {
        return drugRepository.findById(id);
    }

    public Drug updateById(String id, DrugDTO drugDTO) {
        Optional<Drug> existingDrug = drugRepository.findById(id);

        if (existingDrug.isPresent()) {
            var newDrug  = DrugMapper.toDrug(drugDTO);
            newDrug.setId(existingDrug.get().getId());
            return drugRepository.save(newDrug);
        } else {
            throw new EntityNotFoundException(id);
        }
    }

    public void deleteById(String id) {
        drugRepository.deleteById(id);
    }

    public Drug updateQuantity(String id, double drugSold) {
        Optional<Drug> existingDrug = drugRepository.findById(id);

        if (existingDrug.isPresent()) {
            var newDrug  = new Drug();
            newDrug.setId(existingDrug.get().getId());
            newDrug.setImageURL(existingDrug.get().getImageURL());
            newDrug.setName(existingDrug.get().getName());
            newDrug.setDrugSuppliersList(existingDrug.get().getDrugSuppliersList());
            newDrug.setReqPrescription(existingDrug.get().getReqPrescription());
            newDrug.setQuantity(drugSold);
            newDrug.setPriceUnit(existingDrug.get().getPriceUnit());
            return drugRepository.save(newDrug);
        } else {
            throw new EntityNotFoundException(id);
        }
    }
}
