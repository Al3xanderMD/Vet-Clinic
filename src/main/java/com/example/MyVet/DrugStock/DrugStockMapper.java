package com.example.MyVet.DrugStock;

import com.example.MyVet.Drug.Drug;
import com.example.MyVet.Drug.DrugService;
import lombok.RequiredArgsConstructor;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DrugStockMapper {

    private static DrugService drugService;

    @Autowired
    public DrugStockMapper(DrugService drugService){
        this.drugService = drugService;
    }

    public static DrugStock toDrugStock(DrugStockDTO drugStockDTO) {
        DrugStock drugStock = new DrugStock();

        var lista = new ArrayList<Drug>();

        for (var id : drugStockDTO.getDrugsId()) {
            var drugs = drugService.getById(id);
            drugs.ifPresent(drug -> lista.add(drug));
        }

        drugStock.setDrugList(lista);
        return drugStock;
    }
}