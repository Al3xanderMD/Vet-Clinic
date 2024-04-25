package com.example.MyVet.Pet.MedicalHistory;

import com.example.MyVet.Drug.Drug;
import com.example.MyVet.Drug.DrugService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class MedicalHistoryMapper {
    private static DrugService drugService;

    @Autowired
    public  MedicalHistoryMapper(DrugService drugService){this.drugService = drugService;}

    public static MedicalHistory toMedicalHistory(MedicalHistoryDTO medicalHistoryDTO){
        MedicalHistory medicalHistory = new MedicalHistory();

        medicalHistory.setDiagnostics(medicalHistoryDTO.getDiagnostics());

        var lista = new ArrayList<Drug>();

        for(var id: medicalHistoryDTO.getDrugsList())
        {
            var drugs = drugService.getById(id);
            drugs.ifPresent(drug1 -> lista.add(drug1));
        }
        medicalHistory.setDrugList(lista);

        return medicalHistory;
    }
}
