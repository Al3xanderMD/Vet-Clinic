package com.example.MyVet.Drug;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DrugDTOTest {

    private DrugDTO drugDTO1;
    private DrugDTO drugDTO2;
    private DrugDTO drugDTO3;
    private DrugDTO drugDTO4;

    @BeforeEach
    public void setup() {
        drugDTO1 = new DrugDTO("Drug1", new ArrayList<>(), true, "image1.jpg", 2, 34);
        drugDTO2 = new DrugDTO("Drug1", new ArrayList<>(), true, "image1.jpg", 2, 34);
        drugDTO3 = new DrugDTO("Drug2", new ArrayList<>(), false, "image2.jpg", 4 ,90);
        drugDTO4 = new DrugDTO("Drug1", new ArrayList<>(), false, "image1.jpg", 8, 11);
    }

    @Test
    public void testEquals_SameObject() {
        assertEquals(drugDTO1, drugDTO1);
    }

    @Test
    public void testEquals_NullObject() {
        assertNotEquals(drugDTO1, null);
    }

    @Test
    public void testEquals_DifferentClass() {
        assertNotEquals(drugDTO1, "Drug1");
    }

    @Test
    public void testEquals_EqualObjects() {
        assertEquals(drugDTO1, drugDTO2);
    }

    @Test
    public void testEquals_DifferentName() {
        assertNotEquals(drugDTO1, drugDTO3);
    }

    @Test
    public void testEquals_DifferentReqPrescription() {
        assertNotEquals(drugDTO1, drugDTO4);
    }

    @Test
    public void testHashCode_EqualObjects() {
        assertEquals(drugDTO1.hashCode(), drugDTO2.hashCode());
    }

    @Test
    public void testHashCode_DifferentObjects() {
        assertNotEquals(drugDTO1.hashCode(), drugDTO3.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "DrugDTO(name=Drug1, drugSuppliersList=[], reqPrescription=true, imageURL=image1.jpg, quantity=2.0, priceUnit=34.0)";
        assertEquals(expected, drugDTO1.toString());
    }

    @Test
    public void testSetDrugSupplierList() {
        List<String> drugSuppliersId = new ArrayList<>();
        drugSuppliersId.add("Supplier1");
        drugSuppliersId.add("Supplier2");

        drugDTO1.setDrugSuppliersList(drugSuppliersId);
        assertEquals(drugSuppliersId, drugDTO1.getDrugSuppliersList());
    }

    @Test
    public void testCanEqual_SameObject() {
        assertTrue(drugDTO1.canEqual(drugDTO1));
    }

    @Test
    public void testCanEqual_DifferentClass() {
        assertFalse(drugDTO1.canEqual("Drug1"));
    }
    @Test
    public void testEquals_SameObject1() {
        assertEquals(drugDTO1, drugDTO1);
    }

    @Test
    public void testEquals_NullObject1() {
        assertNotEquals(drugDTO1, null);
    }

    @Test
    public void testEquals_DifferentClass1() {
        assertNotEquals(drugDTO1, "Drug1");
    }

    @Test
    public void testEquals_EqualObjects1() {
        assertEquals(drugDTO1, drugDTO2);
    }

    @Test
    public void testEquals_DifferentName1() {
        assertNotEquals(drugDTO1, drugDTO3);
    }

    @Test
    public void testHashCode_EqualObjects1() {
        assertEquals(drugDTO1.hashCode(), drugDTO2.hashCode());
    }

    @Test
    public void testHashCode_DifferentObjects1() {
        assertNotEquals(drugDTO1.hashCode(), drugDTO3.hashCode());
    }

    @Test
    public void testCanEqual_SameObject1() {
        assertTrue(drugDTO1.canEqual(drugDTO1));
    }

    @Test
    public void testCanEqual_DifferentClass1() {
        assertFalse(drugDTO1.canEqual("Drug1"));
    }

    @Test
    public void testHashCode() {
        // Create DrugDTO objects with identical values
        DrugDTO drugDTO1 = new DrugDTO("Drug", Arrays.asList("Supplier1", "Supplier2"), true, "image.jpg", 10.0, 5.0);
        DrugDTO drugDTO2 = new DrugDTO("Drug", Arrays.asList("Supplier1", "Supplier2"), true, "image.jpg", 10.0, 5.0);

        assertEquals(drugDTO1.hashCode(), drugDTO2.hashCode());

        // Modify one field in drugDTO1
        drugDTO1.setName("New Drug");

        assertNotEquals(drugDTO1.hashCode(), drugDTO2.hashCode());
    }

    @Test
    public void testAllArgsConstructor() {
        // Create a DrugDTO object using the AllArgsConstructor
        DrugDTO drugDTO = new DrugDTO("Drug", Arrays.asList("Supplier1", "Supplier2"), true, "image.jpg", 10.0, 5.0);

        // Verify that all fields are set correctly
        assertEquals("Drug", drugDTO.getName());
        assertEquals(Arrays.asList("Supplier1", "Supplier2"), drugDTO.getDrugSuppliersList());
        assertTrue(drugDTO.getReqPrescription());
        assertEquals("image.jpg", drugDTO.getImageURL());
        assertEquals(10.0, drugDTO.getQuantity());
        assertEquals(5.0, drugDTO.getPriceUnit());
    }

    @Test
    public void testToString2() {
        // Create a DrugDTO object
        DrugDTO drugDTO = new DrugDTO("Drug", Arrays.asList("Supplier1", "Supplier2"), true, "image.jpg", 10.0, 5.0);

        String expected = "DrugDTO(name=Drug, drugSuppliersList=[Supplier1, Supplier2], reqPrescription=true, imageURL=image.jpg, quantity=10.0, priceUnit=5.0)";
        assertEquals(expected, drugDTO.toString());
    }

    @Test
    public void testSetQuantity() {
        // Create a DrugDTO object
        DrugDTO drugDTO = new DrugDTO();

        // Set the quantity using the setter
        drugDTO.setQuantity(15.0);

        // Verify that the quantity is set correctly
        assertEquals(15.0, drugDTO.getQuantity());
    }

    @Test
    public void testSetPriceUnit() {
        // Create a DrugDTO object
        DrugDTO drugDTO = new DrugDTO();

        // Set the priceUnit using the setter
        drugDTO.setPriceUnit(8.0);

        // Verify that the priceUnit is set correctly
        assertEquals(8.0, drugDTO.getPriceUnit());
    }
}
