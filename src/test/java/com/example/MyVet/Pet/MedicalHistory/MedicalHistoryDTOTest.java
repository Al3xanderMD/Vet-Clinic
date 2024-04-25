package com.example.MyVet.Pet.MedicalHistory;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MedicalHistoryDTOTest {

    @Test
    public void testConstructorAndGetters() {
        String diagnostics = "Sample diagnostics";
        List<String> drugsList = Arrays.asList("Drug 1", "Drug 2");

        MedicalHistoryDTO medicalHistoryDTO = new MedicalHistoryDTO(diagnostics, drugsList);

        assertEquals(diagnostics, medicalHistoryDTO.getDiagnostics());
        assertEquals(drugsList, medicalHistoryDTO.getDrugsList());
    }

    @Test
    public void testSetters() {
        MedicalHistoryDTO medicalHistoryDTO = new MedicalHistoryDTO();

        String diagnostics = "Sample diagnostics";
        List<String> drugsList = Arrays.asList("Drug 1", "Drug 2");

        medicalHistoryDTO.setDiagnostics(diagnostics);
        medicalHistoryDTO.setDrugsList(drugsList);

        assertEquals(diagnostics, medicalHistoryDTO.getDiagnostics());
        assertEquals(drugsList, medicalHistoryDTO.getDrugsList());
    }

    @Test
    public void testEqualsAndHashCode() {
        String diagnostics = "Sample diagnostics";
        List<String> drugsList1 = Arrays.asList("Drug 1", "Drug 2");
        List<String> drugsList2 = Arrays.asList("Drug 1", "Drug 2");

        MedicalHistoryDTO medicalHistoryDTO1 = new MedicalHistoryDTO(diagnostics, drugsList1);
        MedicalHistoryDTO medicalHistoryDTO2 = new MedicalHistoryDTO(diagnostics, drugsList2);

        assertEquals(medicalHistoryDTO1, medicalHistoryDTO2);
        assertEquals(medicalHistoryDTO1.hashCode(), medicalHistoryDTO2.hashCode());
    }

    @Test
    public void testToString() {
        String diagnostics = "Sample diagnostics";
        List<String> drugsList = Arrays.asList("Drug 1", "Drug 2");

        MedicalHistoryDTO medicalHistoryDTO = new MedicalHistoryDTO(diagnostics, drugsList);

        String expectedToString = "MedicalHistoryDTO(diagnostics=" + diagnostics + ", drugsList=" + drugsList + ")";
        assertEquals(expectedToString, medicalHistoryDTO.toString());
    }
}
