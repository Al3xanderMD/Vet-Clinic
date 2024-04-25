package com.example.MyVet.UserCart.Receipt.DrugSold;

import com.example.MyVet.Drug.DrugService;
import com.example.MyVet.Exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DrugSoldMapper {
    private static DrugService drugService;


    @Autowired
    public DrugSoldMapper(DrugService drugService){
        this.drugService = drugService;
    }

    public static DrugSold toDrugSold(DrugSoldDTO drugSoldDTO) throws Exception {
        DrugSold drugSold = new DrugSold();

        var DS = drugService.getById(drugSoldDTO.getDrugId());
        DS.ifPresent(drugSold::setDrug);

        drugSold.setQuantitySold(drugSoldDTO.getQuantity());

        if(drugSold.getQuantitySold() <= drugService.getById(drugSoldDTO.getDrugId()).get().getQuantity())
        {
            drugSold.setPrice(drugSoldDTO.getQuantity() * drugService.getById(drugSoldDTO.getDrugId()).get().getPriceUnit());

            var diff = drugService.getById(drugSoldDTO.getDrugId()).get().getQuantity() - drugSoldDTO.getQuantity();

            drugService.updateQuantity(drugSoldDTO.getDrugId(), diff);

        } else if (drugService.getById(drugSoldDTO.getDrugId()).get().getQuantity() != 0 ){
            throw new Exception("Low Quntity in Stock");
        } else throw new Exception("Out of stock");

        return drugSold;
    }

}
