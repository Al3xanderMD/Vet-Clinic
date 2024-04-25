package com.example.MyVet.UserCart.Receipt;

import com.example.MyVet.UserCart.Receipt.DrugSold.DrugSold;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReceiptTest {

    @Test
    public void testConstructorAndGetters() {
        // Arrange
        String id = "123";
        List<DrugSold> drugSoldList = new ArrayList<>();
        double priceReceipt = 50.0;
        LocalDateTime date = LocalDateTime.of(2023, 5, 24, 12, 0);

        // Act
        Receipt receipt = new Receipt(id, drugSoldList, priceReceipt, date);

        // Assert
        Assertions.assertEquals(id, receipt.getId());
        Assertions.assertEquals(drugSoldList, receipt.getDrugSoldList());
        Assertions.assertEquals(priceReceipt, receipt.getPriceReceipt());
        Assertions.assertEquals(date, receipt.getDate());
    }

    @Test
    public void testDefaultConstructor() {
        // Arrange & Act
        Receipt receipt = new Receipt();

        // Assert
        Assertions.assertNull(receipt.getId());
        Assertions.assertNull(receipt.getDrugSoldList());
        Assertions.assertEquals(0.0, receipt.getPriceReceipt());
        Assertions.assertNotNull(receipt.getDate());
    }

    @Test
    public void testGettersAndSetters() {
        // Arrange
        String id = "123";
        List<DrugSold> drugSoldList = new ArrayList<>();
        double priceReceipt = 50.0;
        LocalDateTime date = LocalDateTime.of(2023, 5, 24, 12, 0);

        Receipt receipt = new Receipt();

        // Act
        receipt.setId(id);
        receipt.setDrugSoldList(drugSoldList);
        receipt.setPriceReceipt(priceReceipt);
        receipt.setDate(date);

        // Assert
        Assertions.assertEquals(id, receipt.getId());
        Assertions.assertEquals(drugSoldList, receipt.getDrugSoldList());
        Assertions.assertEquals(priceReceipt, receipt.getPriceReceipt());
        Assertions.assertEquals(date, receipt.getDate());
    }

    @Test
    public void testEquals() {
        // Arrange
        String id = "123";
        List<DrugSold> drugSoldList1 = new ArrayList<>();
        drugSoldList1.add(new DrugSold());
        List<DrugSold> drugSoldList2 = new ArrayList<>();
        drugSoldList2.add(new DrugSold());
        double priceReceipt = 50.0;
        LocalDateTime date = LocalDateTime.of(2023, 5, 24, 12, 0);

        Receipt receipt1 = new Receipt(id, drugSoldList1, priceReceipt, date);
        Receipt receipt2 = new Receipt(id, drugSoldList1, priceReceipt, date);
        Receipt receipt3 = new Receipt(id, drugSoldList2, priceReceipt, date);
        Receipt receipt4 = new Receipt("456", drugSoldList1, priceReceipt, date);

        // Assert
        Assertions.assertEquals(receipt1, receipt2); // Same values, so should be equal
        Assertions.assertEquals(receipt1, receipt3); // Different drugSoldList, so not equal
        Assertions.assertNotEquals(receipt1, receipt4); // Different id, so not equal
        Assertions.assertEquals(receipt1, receipt1); // Same instance, should be equal
        Assertions.assertNotEquals(receipt1, null); // Comparing to null, should not be equal
    }

    @Test
    public void testHashCode() {
        // Arrange
        String id = "123";
        List<DrugSold> drugSoldList = new ArrayList<>();
        double priceReceipt = 50.0;
        LocalDateTime date = LocalDateTime.of(2023, 5, 24, 12, 0);

        Receipt receipt1 = new Receipt(id, drugSoldList, priceReceipt, date);
        Receipt receipt2 = new Receipt(id, drugSoldList, priceReceipt, date);

        // Act
        int hashCode1 = receipt1.hashCode();
        int hashCode2 = receipt2.hashCode();

        // Assert
        Assertions.assertEquals(hashCode1, hashCode2);
    }

    @Test
    public void testToString() {
        // Arrange
        String id = "123";
        List<DrugSold> drugSoldList = new ArrayList<>();
        drugSoldList.add(new DrugSold());
        double priceReceipt = 50.0;
        LocalDateTime date = LocalDateTime.of(2023, 5, 24, 12, 0);

        Receipt receipt = new Receipt(id, drugSoldList, priceReceipt, date);

        // Act
        String toString = receipt.toString();

        // Assert
        Assertions.assertTrue(toString.contains("id=" + id));
        Assertions.assertTrue(toString.contains("drugSoldList=" + drugSoldList));
        Assertions.assertTrue(toString.contains("priceReceipt=" + priceReceipt));
        Assertions.assertTrue(toString.contains("date=" + date));
    }

}
