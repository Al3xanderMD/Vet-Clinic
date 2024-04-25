package com.example.MyVet.Pet.MedicalHistory;

import com.example.MyVet.Drug.Drug;
import com.example.MyVet.Drug.DrugService;
import com.example.MyVet.DrugSupplier.DrugSupplier;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class MedicalHistoryMapperTest {

    @Mock
    private DrugService drugService;

    @Test
    public void testToMedicalHistory() {
        // Creating a MedicalHistoryDTO
        String diagnostics = "Sample diagnostics";
        List<String> drugsList = Arrays.asList("drugId1", "drugId2");
        MedicalHistoryDTO medicalHistoryDTO = new MedicalHistoryDTO(diagnostics, drugsList);

        // Mocking the getById method of the DrugService
        Drug drug1 = new Drug("http://example.com/drug1.jpg", "Drug 1", new DrugSupplier(),true, 10, 100);
        Drug drug2 = new Drug("http://example.com/drug2.jpg", "Drug 2", new DrugSupplier(),true, 10, 100);
        Mockito.when(drugService.getById("drugId1")).thenReturn(Optional.of(drug1));
        Mockito.when(drugService.getById("drugId2")).thenReturn(Optional.of(drug2));

        // Creating the MedicalHistoryMapper instance and injecting the drugService
        MedicalHistoryMapper medicalHistoryMapper = new MedicalHistoryMapper(drugService);

        // Calling the toMedicalHistory method
        MedicalHistory medicalHistory = medicalHistoryMapper.toMedicalHistory(medicalHistoryDTO);

        // Asserting the MedicalHistory object
        assertNull(medicalHistory.getId());
        assertEquals(diagnostics, medicalHistory.getDiagnostics());
        assertEquals(Arrays.asList(drug1, drug2), medicalHistory.getDrugList());
        assertNotNull(medicalHistory.getDate());
    }
}

