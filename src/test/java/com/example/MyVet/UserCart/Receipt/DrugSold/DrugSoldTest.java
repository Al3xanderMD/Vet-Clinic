package com.example.MyVet.UserCart.Receipt.DrugSold;

import com.example.MyVet.Drug.Drug;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DrugSoldTest {
    @Test
    void allArgsConstructor_CreatesDrugSoldObjectWithAllProperties() {
        // Arrange
        String id = "123";
        Drug drug = new Drug();
        double quantitySold = 10.5;
        double price = 100.0;

        // Act
        DrugSold drugSold = new DrugSold(id, drug, quantitySold, price);

        // Assert
        Assertions.assertNotNull(drugSold);
        Assertions.assertEquals(id, drugSold.getId());
        Assertions.assertEquals(drug, drugSold.getDrug());
        Assertions.assertEquals(quantitySold, drugSold.getQuantitySold());
        Assertions.assertEquals(price, drugSold.getPrice());
    }

    @Test
    void definedConstructor_CreatesDrugSoldObjectWithDrugAndQuantitySold() {
        // Arrange
        Drug drug = new Drug();
        double quantitySold = 10.5;

        // Act
        DrugSold drugSold = new DrugSold(drug, quantitySold);

        // Assert
        Assertions.assertNotNull(drugSold);
        Assertions.assertEquals(drug, drugSold.getDrug());
        Assertions.assertEquals(quantitySold, drugSold.getQuantitySold());
        Assertions.assertEquals(0.0, drugSold.getPrice()); // Price is not set in the defined constructor
    }


    @Test
    public void testConstructorWithDrugAndQuantitySold() {
        // Arrange
        Drug drug = new Drug();
        double quantitySold = 10.0;

        // Act
        DrugSold drugSold = new DrugSold(drug, quantitySold);

        // Assert
        Assertions.assertEquals(drug, drugSold.getDrug());
        Assertions.assertEquals(quantitySold, drugSold.getQuantitySold());
    }

    @Test
    public void testGetterAndSetterMethods() {
        // Arrange
        DrugSold drugSold = new DrugSold();
        Drug drug = new Drug();
        double quantitySold = 20.0;
        double price = 50.0;

        // Act
        drugSold.setDrug(drug);
        drugSold.setQuantitySold(quantitySold);
        drugSold.setPrice(price);

        // Assert
        Assertions.assertEquals(drug, drugSold.getDrug());
        Assertions.assertEquals(quantitySold, drugSold.getQuantitySold());
        Assertions.assertEquals(price, drugSold.getPrice());
    }

    @Test
    public void testIdSetterAndGetter() {
        // Arrange
        DrugSold drugSold = new DrugSold();
        String id = "123";

        // Act
        drugSold.setId(id);

        // Assert
        Assertions.assertEquals(id, drugSold.getId());
    }

    @Test
    public void testConstructorWithDrugOnly() {
        // Arrange
        Drug drug = new Drug();

        // Act
        DrugSold drugSold = new DrugSold();

        // Assert
        Assertions.assertNotEquals(drug, drugSold.getDrug());
        Assertions.assertEquals(0.0, drugSold.getQuantitySold());
    }

    @Test
    public void testDefaultConstructor() {
        // Arrange
        DrugSold drugSold = new DrugSold();

        // Assert
        Assertions.assertNull(drugSold.getId());
        Assertions.assertNull(drugSold.getDrug());
        Assertions.assertEquals(0.0, drugSold.getQuantitySold());
        Assertions.assertEquals(0.0, drugSold.getPrice());
    }

    @Test
    public void testToStringMethod() {
        // Arrange
        Drug drug = new Drug();
        drug.setName("Aspirin");
        double quantitySold = 10.0;
        double price = 50.0;
        DrugSold drugSold = new DrugSold(drug, quantitySold);
        drugSold.setPrice(price);
        String expectedString = "DrugSold(id=null, drug=Drug(imageURL=null, id=null, name=Aspirin, drugSuppliersList=null, reqPrescription=null, quantity=0.0, priceUnit=0.0), quantitySold=10.0, price=50.0)";

        // Act
        String actualString = drugSold.toString();

        // Assert
        Assertions.assertEquals(expectedString, actualString);
    }

    @Test
    public void testEqualsMethodWithEqualObjects() {
        // Arrange
        Drug drug1 = new Drug();
        drug1.setName("Aspirin");
        Drug drug2 = new Drug();
        drug2.setName("Aspirin");
        double quantitySold1 = 10.0;
        double quantitySold2 = 10.0;
        double price1 = 50.0;
        double price2 = 50.0;
        DrugSold drugSold1 = new DrugSold(drug1, quantitySold1);
        DrugSold drugSold2 = new DrugSold(drug2, quantitySold2);
        drugSold1.setPrice(price1);
        drugSold2.setPrice(price2);

        // Act
        boolean areEqual = drugSold1.equals(drugSold2);

        // Assert
        Assertions.assertTrue(areEqual);
    }

    @Test
    public void testEqualsMethodWithUnequalObjects() {
        // Arrange
        Drug drug1 = new Drug();
        drug1.setName("Aspirin");
        Drug drug2 = new Drug();
        drug2.setName("Ibuprofen");
        double quantitySold1 = 10.0;
        double quantitySold2 = 15.0;
        double price1 = 50.0;
        double price2 = 60.0;
        DrugSold drugSold1 = new DrugSold(drug1, quantitySold1);
        DrugSold drugSold2 = new DrugSold(drug2, quantitySold2);
        drugSold1.setPrice(price1);
        drugSold2.setPrice(price2);

        // Act
        boolean areEqual = drugSold1.equals(drugSold2);

        // Assert
        Assertions.assertFalse(areEqual);
    }

    @Test
    public void testCanEqualMethodWithEqualObjects() {
        // Arrange
        Drug drug1 = new Drug();
        drug1.setName("Aspirin");
        Drug drug2 = new Drug();
        drug2.setName("Aspirin");
        double quantitySold1 = 10.0;
        double quantitySold2 = 10.0;
        double price1 = 50.0;
        double price2 = 50.0;
        DrugSold drugSold1 = new DrugSold(drug1, quantitySold1);
        DrugSold drugSold2 = new DrugSold(drug2, quantitySold2);
        drugSold1.setPrice(price1);
        drugSold2.setPrice(price2);

        // Act
        boolean canEqual = drugSold1.canEqual(drugSold2);

        // Assert
        Assertions.assertTrue(canEqual);
    }

    @Test
    public void testHashCodeMethod() {
        // Arrange
        Drug drug = new Drug();
        drug.setName("Aspirin");
        double quantitySold = 10.0;
        double price = 50.0;
        DrugSold drugSold = new DrugSold(drug, quantitySold);
        drugSold.setPrice(price);
        int expectedHashCode = -1684886775;

        // Act
        int actualHashCode = drugSold.hashCode();

        // Assert
        Assertions.assertNotEquals(expectedHashCode, actualHashCode);
    }

}
