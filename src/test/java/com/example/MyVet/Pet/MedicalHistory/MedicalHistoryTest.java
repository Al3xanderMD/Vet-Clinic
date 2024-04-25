package com.example.MyVet.Pet.MedicalHistory;

import com.example.MyVet.Drug.Drug;
import com.example.MyVet.DrugSupplier.DrugSupplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MedicalHistoryTest {

    @Test
    public void testConstructorAndGetters() {
        String diagnostics = "Sample diagnostics";
        LocalDateTime date = LocalDateTime.of(2022, 1, 1, 12, 0);

        MedicalHistory medicalHistory = new MedicalHistory(diagnostics, date);

        assertNull(medicalHistory.getId());
        assertEquals(diagnostics, medicalHistory.getDiagnostics());
        assertNull(medicalHistory.getDrugList());
        assertEquals(date, medicalHistory.getDate());
    }

    @Test
    public void testSetters() {
        MedicalHistory medicalHistory = new MedicalHistory();

        String diagnostics = "Sample diagnostics";
        LocalDateTime date = LocalDateTime.of(2022, 1, 1, 12, 0);
        List<Drug> drugList = Arrays.asList(new Drug("http://example.com/drug1.jpg", "Drug 1", new DrugSupplier(),true, 10, 100), new Drug("http://example.com/drug2.jpg", "Drug 2", new DrugSupplier(),true, 10, 100));

        medicalHistory.setDiagnostics(diagnostics);
        medicalHistory.setDate(date);
        medicalHistory.setDrugList(drugList);

        assertNull(medicalHistory.getId());
        assertEquals(diagnostics, medicalHistory.getDiagnostics());
        assertEquals(drugList, medicalHistory.getDrugList());
        assertEquals(date, medicalHistory.getDate());
    }

    @Test
    public void testEqualsAndHashCode() {
        String diagnostics = "Sample diagnostics";
        LocalDateTime date = LocalDateTime.of(2022, 1, 1, 12, 0);
        List<Drug> drugList1 = Arrays.asList(new Drug("http://example.com/drug1.jpg", "Drug 1", new DrugSupplier(),true, 10, 100), new Drug("http://example.com/drug2.jpg", "Drug 2", new DrugSupplier(),true, 10, 100));
        List<Drug> drugList2 = Arrays.asList(new Drug("http://example.com/drug1.jpg", "Drug 1", new DrugSupplier(),true, 10, 100), new Drug("http://example.com/drug2.jpg", "Drug 2", new DrugSupplier(),true, 10, 100));

        MedicalHistory medicalHistory1 = new MedicalHistory(diagnostics, date);
        medicalHistory1.setDrugList(drugList1);
        MedicalHistory medicalHistory2 = new MedicalHistory(diagnostics, date);
        medicalHistory2.setDrugList(drugList2);

        assertEquals(medicalHistory1, medicalHistory2);
        assertEquals(medicalHistory1.hashCode(), medicalHistory2.hashCode());
    }

    @Test
    public void testToString() {
        String diagnostics = "Sample diagnostics";
        LocalDateTime date = LocalDateTime.of(2022, 1, 1, 12, 0);
        List<Drug> drugList = Arrays.asList(new Drug("http://example.com/drug1.jpg", "Drug 1", new DrugSupplier(),true, 10, 100), new Drug("http://example.com/drug2.jpg", "Drug 2", new DrugSupplier(),true, 10, 100));

        MedicalHistory medicalHistory = new MedicalHistory(diagnostics, date);
        medicalHistory.setDrugList(drugList);

        String expectedToString = "MedicalHistory(id=null, diagnostics=" + diagnostics +
                ", drugList=" + drugList + ", date=" + date + ")";
        assertEquals(expectedToString, medicalHistory.toString());
    }
    @Test
    public void testAllArgsConstructor() {
        // Arrange
        String id = "123456";
        String diagnostics = "Test diagnostics";
        List<Drug> drugList = new ArrayList<>();
        LocalDateTime date = LocalDateTime.of(2022, 1, 1, 10, 30);

        // Act
        MedicalHistory medicalHistory = new MedicalHistory(id, diagnostics, drugList, date);

        // Assert
        Assertions.assertEquals(id, medicalHistory.getId());
        Assertions.assertEquals(diagnostics, medicalHistory.getDiagnostics());
        Assertions.assertEquals(drugList, medicalHistory.getDrugList());
        Assertions.assertEquals(date, medicalHistory.getDate());
    }
}
