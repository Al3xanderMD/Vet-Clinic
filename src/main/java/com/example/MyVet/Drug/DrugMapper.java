package com.example.MyVet.Drug;

import com.example.MyVet.DrugSupplier.DrugSupplier;
import com.example.MyVet.DrugSupplier.DrugSupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class DrugMapper {
    private static DrugSupplierService drugSupplierService;

    @Autowired
    public DrugMapper(DrugSupplierService drugSupplierService) {
        this.drugSupplierService = drugSupplierService;
    }

    public static Drug toDrug(DrugDTO drugDTO) {
        Drug drug = new Drug();
        drug.setReqPrescription(drugDTO.getReqPrescription());
        drug.setImageURL(drugDTO.getImageURL());
        drug.setName(drugDTO.getName());

        var lista = new ArrayList<DrugSupplier>();

        for(var id: drugDTO.getDrugSuppliersList())
        {
            var supplier = drugSupplierService.getDrugSupplierById(id);
            supplier.ifPresent(drugSupplier -> lista.add(drugSupplier));
        }

        drug.setDrugSuppliersList(lista);
        drug.setQuantity(drugDTO.getQuantity());
        drug.setPriceUnit(drugDTO.getPriceUnit());

        return drug;
    }
}
