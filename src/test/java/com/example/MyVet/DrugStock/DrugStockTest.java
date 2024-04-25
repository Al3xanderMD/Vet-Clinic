package com.example.MyVet.DrugStock;

import com.example.MyVet.Drug.Drug;
import com.example.MyVet.DrugSupplier.DrugSupplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DrugStockTest {

    private DrugStock drugStock;
    private Drug drug1;
    private Drug drug2;

    @BeforeEach
    public void setUp() {
        drugStock = new DrugStock();
        drug1 = new Drug(null, "Drug 1", new DrugSupplier(),true, 10, 100);
        drug2 = new Drug(null, "Drug 2", new DrugSupplier(),true, 2, 1);
    }

    @Test
    public void testEmptyDrugList() {
        ArrayList<Drug> expectedList = new ArrayList<>();
        ArrayList<Drug> actualList = drugStock.getDrugList();
        assertEquals(expectedList, actualList);
    }

    @Test
    public void testAddDrugToList1() {
        Drug drug = new Drug(); // Create a dummy drug object
        drugStock.getDrugList().add(drug);
        assertEquals(1, drugStock.getDrugList().size());
        Assertions.assertTrue(drugStock.getDrugList().contains(drug));
    }
    @Test
    public void testEmptyDrugList1() {
        ArrayList<Drug> expectedList = new ArrayList<>();
        ArrayList<Drug> actualList = drugStock.getDrugList();
        assertEquals(expectedList, actualList);
    }
//
    @Test
    public void testAddDrugToList() {
        drugStock.getDrugList().add(drug1);
        assertEquals(1, drugStock.getDrugList().size());
        Assertions.assertTrue(drugStock.getDrugList().contains(drug1));
    }

    @Test
    public void testRemoveDrugFromList() {
        drugStock.getDrugList().add(drug1);
        drugStock.getDrugList().remove(drug1);
        assertEquals(0, drugStock.getDrugList().size());
        Assertions.assertFalse(drugStock.getDrugList().contains(drug1));
    }

    @Test
    public void testEquals() {
        drugStock.getDrugList().add(drug1);
        DrugStock otherDrugStock = new DrugStock();
        otherDrugStock.getDrugList().add(drug1);

        assertEquals(drugStock, drugStock); // Reflexive
        assertEquals(drugStock, otherDrugStock);
        assertEquals(otherDrugStock, drugStock);
        assertEquals(drugStock.hashCode(), otherDrugStock.hashCode());
    }

    @Test
    public void testNotEquals() {
        drugStock.getDrugList().add(drug1);
        DrugStock otherDrugStock = new DrugStock();
        otherDrugStock.getDrugList().add(drug2);

        Assertions.assertNotEquals(drugStock, null); // Not equal to null
        Assertions.assertNotEquals(drugStock, new Object()); // Not equal to different class
        Assertions.assertNotEquals(drugStock, otherDrugStock); // Different drug in the stock
        Assertions.assertNotEquals(drugStock.hashCode(), otherDrugStock.hashCode());
    }

    @Test
    public void testCanEqual() {
        drugStock.getDrugList().add(drug1);
        DrugStock otherDrugStock = new DrugStock();
        otherDrugStock.getDrugList().add(drug1);

        Assertions.assertTrue(drugStock.canEqual(drugStock));
        Assertions.assertTrue(drugStock.canEqual(otherDrugStock));
    }

    @Test
    public void testHashCode() {
        drugStock.getDrugList().add(drug1);
        DrugStock otherDrugStock = new DrugStock();
        otherDrugStock.getDrugList().add(drug1);

        assertEquals(drugStock.hashCode(), otherDrugStock.hashCode());
    }

    @Test
    public void testToString() {
        drugStock.getDrugList().add(drug1);
        drugStock.getDrugList().add(drug2);

        String expectedToString = "DrugStock(id=null, drugList=[Drug(imageURL=null, id=null, name=Drug 1, " +
                "drugSuppliersList=[DrugSupplier(id=null, name=null)], reqPrescription=true, quantity=10.0, priceUnit=100.0)," +
                " Drug(imageURL=null, id=null, name=Drug 2, drugSuppliersList=[DrugSupplier(id=null, name=null)], reqPrescription=true, quantity=2.0, priceUnit=1.0)])";
        assertEquals(expectedToString, drugStock.toString());
    }

    // Test default constructor
    @Test
    public void testDefaultConstructor() {
        Assertions.assertNotNull(drugStock);
        assertEquals(0, drugStock.getDrugList().size());
    }

    // Test constructor with drug list
    @Test
    public void testConstructorWithDrugList() {
        ArrayList<Drug> drugs = new ArrayList<>(Arrays.asList(drug1, drug2));
        drugStock = new DrugStock("1",drugs);

        assertEquals(2, drugStock.getDrugList().size());
        Assertions.assertTrue(drugStock.getDrugList().contains(drug1));
        Assertions.assertTrue(drugStock.getDrugList().contains(drug2));
    }

    // Test setDrugList method
    @Test
    public void testSetDrugList() {
        ArrayList<Drug> drugs = new ArrayList<>(Arrays.asList(drug1, drug2));
        drugStock.setDrugList(drugs);

        assertEquals(2, drugStock.getDrugList().size());
        Assertions.assertTrue(drugStock.getDrugList().contains(drug1));
        Assertions.assertTrue(drugStock.getDrugList().contains(drug2));
    }
    @Test
    public void testSetId() {
        // Create a DrugStock object
        DrugStock drugStock = new DrugStock();

        // Set the id using the setId() method
        drugStock.setId("12345");

        // Verify that the id is set correctly
        assertEquals("12345", drugStock.getId());
    }
}
