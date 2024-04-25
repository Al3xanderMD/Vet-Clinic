package com.example.MyVet.Cabinet;

import com.example.MyVet.DrugStock.DrugStock;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CabinetTest {

    @Test
    public void testConstructorAndGetters() {
        String id = "CAB001";
        String name = "Cabinet 1";
        DrugStock drugStock = new DrugStock();

        Cabinet cabinet = new Cabinet(id, name, drugStock);

        assertEquals(id, cabinet.getId());
        assertEquals(name, cabinet.getName());
        assertEquals(drugStock, cabinet.getDrugStock());
    }

    @Test
    public void testSetters() {
        Cabinet cabinet = new Cabinet();

        String id = "CAB001";
        String name = "Cabinet 1";
        DrugStock drugStock = new DrugStock();

        cabinet.setId(id);
        cabinet.setName(name);
        cabinet.setDrugStock(drugStock);

        assertEquals(id, cabinet.getId());
        assertEquals(name, cabinet.getName());
        assertEquals(drugStock, cabinet.getDrugStock());
    }

    @Test
    public void testEqualsAndHashCode() {
        String id = "CAB001";
        String name = "Cabinet 1";
        DrugStock drugStock = new DrugStock();

        Cabinet cabinet1 = new Cabinet(id, name, drugStock);
        Cabinet cabinet2 = new Cabinet(id, name, drugStock);

        assertEquals(cabinet1, cabinet2);
        assertEquals(cabinet1.hashCode(), cabinet2.hashCode());
    }

    @Test
    public void testToString() {
        String id = "CAB001";
        String name = "Cabinet 1";
        DrugStock drugStock = new DrugStock();

        Cabinet cabinet = new Cabinet(id, name, drugStock);

        String expectedToString = "Cabinet(id=" + id + ", name=" + name + ", drugStock=" + drugStock + ")";
        assertEquals(expectedToString, cabinet.toString());
    }
}
