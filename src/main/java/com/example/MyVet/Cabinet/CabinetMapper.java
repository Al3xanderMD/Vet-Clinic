package com.example.MyVet.Cabinet;

import com.example.MyVet.DrugStock.DrugStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class CabinetMapper {
    private static DrugStockService drugStockService;
    @Autowired
    private CabinetMapper(DrugStockService drugStockService){
        this.drugStockService = drugStockService;
    }

    public static Cabinet toCabinet(CabinetDTO cabinetDTO) {
        Cabinet cabinet = new Cabinet();
        cabinet.setName(cabinetDTO.getName());

        var DS = drugStockService.getById(cabinetDTO.getDrugStockId());
        DS.ifPresent(cabinet::setDrugStock);

        return cabinet;
    }
}
