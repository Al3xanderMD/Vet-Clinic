package com.example.MyVet.DrugStock;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.ArrayList;
import java.util.List;

public class DrugStockDTOTest {

    private DrugStockDTO drugStockDTO1;
    private DrugStockDTO drugStockDTO2;
    private DrugStockDTO drugStockDTO3;

    @BeforeEach
    public void setup() {
        List<String> drugsId1 = new ArrayList<>();
        drugsId1.add("Drug1");
        drugsId1.add("Drug2");

        List<String> drugsId2 = new ArrayList<>();
        drugsId2.add("Drug1");
        drugsId2.add("Drug2");

        List<String> drugsId3 = new ArrayList<>();
        drugsId3.add("Drug3");

        drugStockDTO1 = new DrugStockDTO(drugsId1);
        drugStockDTO2 = new DrugStockDTO(drugsId2);
        drugStockDTO3 = new DrugStockDTO(drugsId3);
    }

    @Test
    public void testCanEqual_SameObject() {
        assertTrue(drugStockDTO1.canEqual(drugStockDTO1));
    }

    @Test
    public void testCanEqual_DifferentClass() {
        assertFalse(drugStockDTO1.canEqual("DrugStockDTO"));
    }

    @Test
    public void testCanEqual_EqualObjects() {
        assertTrue(drugStockDTO1.canEqual(drugStockDTO2));
    }

    @Test
    public void testCanEqual_DifferentDrugsId() {
        assertTrue(drugStockDTO1.canEqual(drugStockDTO3));
    }

    @Test
    public void testEquals_SameObject() {
        assertEquals(drugStockDTO1, drugStockDTO1);
    }

    @Test
    public void testEquals_NullObject() {
        assertNotEquals(drugStockDTO1, null);
    }

    @Test
    public void testEquals_DifferentClass() {
        assertNotEquals(drugStockDTO1, "DrugStockDTO");
    }

    @Test
    public void testEquals_EqualObjects() {
        assertEquals(drugStockDTO1, drugStockDTO2);
    }

    @Test
    public void testEquals_DifferentDrugsId() {
        assertNotEquals(drugStockDTO1, drugStockDTO3);
    }

    @Test
    public void testToString() {
        String expected = "DrugStockDTO(drugsId=[Drug1, Drug2])";
        assertEquals(expected, drugStockDTO1.toString());
    }

    @Test
    public void testHashCode_EqualObjects() {
        assertEquals(drugStockDTO1.hashCode(), drugStockDTO2.hashCode());
    }

    @Test
    public void testHashCode_DifferentObjects() {
        assertNotEquals(drugStockDTO1.hashCode(), drugStockDTO3.hashCode());
    }

    @Test
    public void testSetDrugsId() {
        // Create a new DrugStockDTO object
        DrugStockDTO drugStockDTO = new DrugStockDTO();

        // Create a list of drugsId
        List<String> drugsId = new ArrayList<>();
        drugsId.add("drugId1");
        drugsId.add("drugId2");

        // Set the drugsId using the setDrugsId() method
        drugStockDTO.setDrugsId(drugsId);

        // Verify that the drugsId is set correctly
        assertEquals(drugsId, drugStockDTO.getDrugsId());
    }
}
