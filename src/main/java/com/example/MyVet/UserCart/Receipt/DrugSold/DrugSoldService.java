package com.example.MyVet.UserCart.Receipt.DrugSold;

import com.example.MyVet.Exceptions.EntityNotFoundException;
import com.example.MyVet.UserCart.Receipt.Receipt;
import com.example.MyVet.UserCart.Receipt.ReceiptDTO;
import com.example.MyVet.UserCart.Receipt.ReceiptMapper;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class DrugSoldService {
    private final DrugSoldMapper drugSoldMapper = new DrugSoldMapper();

    @Autowired
    private DrugSoldRepository drugSoldRepository;

    public DrugSold create(DrugSoldDTO drugSoldDTO) throws Exception {return drugSoldRepository.save(drugSoldMapper.toDrugSold(drugSoldDTO));}

    public List<DrugSold> getAll(){return drugSoldRepository.findAll();}

    public Optional<DrugSold> getById(String id){return drugSoldRepository.findById(id);}

    public DrugSold update(String id, DrugSoldDTO drugSoldDTO) throws Exception {
        Optional<DrugSold> existingDrugSold = drugSoldRepository.findById(id);

        if(existingDrugSold.isPresent()){
            var newDS = DrugSoldMapper.toDrugSold(drugSoldDTO);
            newDS.setId(existingDrugSold.get().getId());

            return drugSoldRepository.save(newDS);
        } else {
            throw new EntityNotFoundException(id);
        }
    }

    public void deleteById(String id){drugSoldRepository.deleteById(id);}

}
