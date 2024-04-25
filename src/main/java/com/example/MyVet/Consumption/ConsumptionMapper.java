package com.example.MyVet.Consumption;

import com.example.MyVet.Cabinet.CabinetService;
import com.example.MyVet.Drug.Drug;
import com.example.MyVet.Drug.DrugService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class ConsumptionMapper {

    private static DrugService drugService;

    @Autowired
    private ConsumptionMapper(DrugService drugService){
        this.drugService = drugService;
    }

    public static Consumption toConsumption(ConsumptionDTO consumptionDTO){
        Consumption consumption = new Consumption();

        var consD = drugService.getById(consumptionDTO.getDrugId());
        consD.ifPresent(consumption::setDrugId);

        consumption.setQuantitySold(consumptionDTO.getQuantitySold());
        consumption.setYear(consumptionDTO.getYear());
        consumption.setMonth(consumptionDTO.getMonth());
        return consumption;
    }
}
