package com.example.MyVet.Drug;

import static org.junit.jupiter.api.Assertions.*;
import com.example.MyVet.DrugSupplier.DrugSupplier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class DrugTest {

    @Test
    void testConstructorWithArguments() {
        String imageURL = "http://example.com/drug.jpg";
        String id = "123456";
        String name = "Drug Name";
        List<DrugSupplier> supplierList = new ArrayList<>();
        Boolean reqPrescription = true;
        DrugSupplier drugSupplier = new DrugSupplier(null);

        Drug drug = new Drug(imageURL,name, drugSupplier, reqPrescription, 10, 10);
        drug.setId("123456");
        assertEquals(imageURL, drug.getImageURL());
        assertEquals(id, drug.getId());
        assertEquals(name, drug.getName());
        supplierList.add(new DrugSupplier(null,null));
        assertEquals(supplierList, drug.getDrugSuppliersList());
        assertEquals(reqPrescription, drug.getReqPrescription());
    }

    @Test
    void testDefaultConstructor() {
        Drug drug = new Drug();

        assertNull(drug.getImageURL());
        assertNull(drug.getId());
        assertNull(drug.getName());
        assertNull(drug.getDrugSuppliersList());
        assertNull(drug.getReqPrescription());
    }

    @Test
    void testSettersAndGetters() {
        Drug drug = new Drug();

        String imageURL = "http://example.com/drug.jpg";
        drug.setImageURL(imageURL);
        assertEquals(imageURL, drug.getImageURL());

        String id = "123456";
        drug.setId(id);
        assertEquals(id, drug.getId());

        String name = "Drug Name";
        drug.setName(name);
        assertEquals(name, drug.getName());

        List<DrugSupplier> supplierList = new ArrayList<>();
        drug.setDrugSuppliersList(supplierList);
        assertEquals(supplierList, drug.getDrugSuppliersList());

        Boolean reqPrescription = true;
        drug.setReqPrescription(reqPrescription);
        assertEquals(reqPrescription, drug.getReqPrescription());
    }

    @Test
    void testEqualsAndHashCode() {
        Drug drug1 = new Drug("http://example.com/drug1.jpg", "Drug 1", new DrugSupplier(),true, 10, 100);
        Drug drug2 = new Drug("http://example.com/drug2.jpg", "Drug 2", new DrugSupplier(), true, 10, 100);
        Drug drug3 = new Drug("http://example.com/drug1.jpg", "Drug 3", new DrugSupplier(), false, 10, 100);

        // Test equality based on ID
        assertNotEquals(drug1, drug2);
        assertNotEquals(drug1, drug3);
        assertNotEquals(drug2, drug3);

        // Test hash code
        assertNotEquals(drug1.hashCode(), drug2.hashCode());
        assertNotEquals(drug1.hashCode(), drug3.hashCode());
        assertNotEquals(drug2.hashCode(), drug3.hashCode());
    }

    @Test
    public void testToString() {
        // Arrange
        DrugSupplier supplier1 = new DrugSupplier("1", "Supplier 1");
        DrugSupplier supplier2 = new DrugSupplier("2", "Supplier 2");
        Drug drug = new Drug("https://example.com/image.jpg","Drug 1",
                 new DrugSupplier(),true, 10, 100);

        String expectedString = "Drug(imageURL=https://example.com/image.jpg, id=null, name=Drug 1, " +
                "drugSuppliersList=[DrugSupplier(id=null, name=null)], " +
                "reqPrescription=true, quantity=10.0, priceUnit=100.0)";

        // Act
        String result = drug.toString();

        // Assert
        assertEquals(expectedString, result);
    }

}
