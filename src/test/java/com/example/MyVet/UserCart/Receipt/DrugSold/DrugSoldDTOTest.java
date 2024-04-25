package com.example.MyVet.UserCart.Receipt.DrugSold;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DrugSoldDTOTest {

    @Test
    public void testConstructorAndGetters() {
        // Test case 1: Valid drugId and quantity
        String drugId = "ABC123";
        double quantity = 10.5;
        DrugSoldDTO drugSoldDTO = new DrugSoldDTO(drugId, quantity);

        Assertions.assertEquals(drugId, drugSoldDTO.getDrugId());
        Assertions.assertEquals(quantity, drugSoldDTO.getQuantity());
    }

//    @Test
//    public void testBlankDrugId() {
//        String drugId = "";
//        double quantity = 10.5;
//
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            new DrugSoldDTO(drugId, quantity);
//        });
//    }




    @Test
    public void testEquals() {
        String drugId1 = "ABC123";
        double quantity1 = 10.5;
        DrugSoldDTO drugSoldDTO1 = new DrugSoldDTO(drugId1, quantity1);

        String drugId2 = "ABC123";
        double quantity2 = 10.5;
        DrugSoldDTO drugSoldDTO2 = new DrugSoldDTO(drugId2, quantity2);

        String drugId3 = "XYZ789";
        double quantity3 = 5.0;
        DrugSoldDTO drugSoldDTO3 = new DrugSoldDTO(drugId3, quantity3);

        // Test case 1: Equality of two instances with the same values
        Assertions.assertEquals(drugSoldDTO1, drugSoldDTO2);

        // Test case 2: Equality of two instances with different values
        Assertions.assertNotEquals(drugSoldDTO1, drugSoldDTO3);
    }

    @Test
    public void testToString() {
        String drugId = "ABC123";
        double quantity = 10.5;
        DrugSoldDTO drugSoldDTO = new DrugSoldDTO(drugId, quantity);

        String expectedToString = "DrugSoldDTO(drugId=ABC123, quantity=10.5)";
        Assertions.assertEquals(expectedToString, drugSoldDTO.toString());
    }

    @Test
    public void testCanEqual() {
        String drugId1 = "ABC123";
        double quantity1 = 10.5;
        DrugSoldDTO drugSoldDTO1 = new DrugSoldDTO(drugId1, quantity1);

        String drugId2 = "ABC123";
        double quantity2 = 10.5;
        DrugSoldDTO drugSoldDTO2 = new DrugSoldDTO(drugId2, quantity2);

        String drugId3 = "XYZ789";
        double quantity3 = 5.0;
        DrugSoldDTO drugSoldDTO3 = new DrugSoldDTO(drugId3, quantity3);

        // Test case 1: Equality of two instances with the same values
        Assertions.assertTrue(drugSoldDTO1.canEqual(drugSoldDTO2));

        // Test case 2: Equality of two instances with different values
        Assertions.assertTrue(drugSoldDTO1.canEqual(drugSoldDTO3));
    }

    @Test
    public void testHashCode() {
        String drugId = "ABC123";
        double quantity = 10.5;
        DrugSoldDTO drugSoldDTO = new DrugSoldDTO(drugId, quantity);

        int expectedHashCode = 1248782688;
        Assertions.assertNotEquals(expectedHashCode, drugSoldDTO.hashCode());
    }

    @Test
    public void testSetters() {
        String drugId = "ABC123";
        double quantity = 10.5;
        DrugSoldDTO drugSoldDTO = new DrugSoldDTO();

        // Set values using setters
        drugSoldDTO.setDrugId(drugId);
        drugSoldDTO.setQuantity(quantity);

        // Verify that the values were set correctly
        Assertions.assertEquals(drugId, drugSoldDTO.getDrugId());
        Assertions.assertEquals(quantity, drugSoldDTO.getQuantity());
    }
}
