package com.example.MyVet.DrugSupplier;

import org.junit.jupiter.api.Test;

import static com.mongodb.assertions.Assertions.assertFalse;
import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DrugSupplierTest {

    @Test
    public void testConstructorAndGetters() {
        // Arrange
        String id = "123";
        String name = "Supplier Name";

        // Act
        DrugSupplier supplier = new DrugSupplier(id, name);

        // Assert
        assertEquals(id, supplier.getId());
        assertEquals(name, supplier.getName());
    }

    @Test
    public void testSetters() {
        // Arrange
        DrugSupplier supplier = new DrugSupplier();

        // Act
        String id = "123";
        String name = "Supplier Name";
        supplier.setId(id);
        supplier.setName(name);

        // Assert
        assertEquals(id, supplier.getId());
        assertEquals(name, supplier.getName());
    }
    @Test
    public void testEquals_SameInstance() {
        // Arrange
        DrugSupplier supplier = new DrugSupplier("123", "Supplier Name");

        // Act and Assert
        assertTrue(supplier.equals(supplier));
    }

    @Test
    public void testEquals_SameFields() {
        // Arrange
        DrugSupplier supplier1 = new DrugSupplier("123", "Supplier Name");
        DrugSupplier supplier2 = new DrugSupplier("123", "Supplier Name");

        // Act and Assert
        assertTrue(supplier1.equals(supplier2));
        assertTrue(supplier2.equals(supplier1));
    }

    @Test
    public void testEquals_DifferentId() {
        // Arrange
        DrugSupplier supplier1 = new DrugSupplier("123", "Supplier Name");
        DrugSupplier supplier2 = new DrugSupplier("456", "Supplier Name");

        // Act and Assert
        assertFalse(supplier1.equals(supplier2));
        assertFalse(supplier2.equals(supplier1));
    }

    @Test
    public void testEquals_DifferentName() {
        // Arrange
        DrugSupplier supplier1 = new DrugSupplier("123", "Supplier Name");
        DrugSupplier supplier2 = new DrugSupplier("123", "Different Name");

        // Act and Assert
        assertFalse(supplier1.equals(supplier2));
        assertFalse(supplier2.equals(supplier1));
    }

    @Test
    public void testEquals_NullObject() {
        // Arrange
        DrugSupplier supplier = new DrugSupplier("123", "Supplier Name");

        // Act and Assert
        assertFalse(supplier.equals(null));
    }

    @Test
    public void testEquals_DifferentClass() {
        // Arrange
        DrugSupplier supplier = new DrugSupplier("123", "Supplier Name");
        String differentObject = "Not a DrugSupplier object";

        // Act and Assert
        assertFalse(supplier.equals(differentObject));
    }

    @Test
    public void testEquals_EqualHashCode() {
        // Arrange
        DrugSupplier supplier1 = new DrugSupplier("123", "Supplier Name");
        DrugSupplier supplier2 = new DrugSupplier("123", "Supplier Name");

        // Act and Assert
        assertTrue(supplier1.hashCode() == supplier2.hashCode());
    }

    @Test
    public void testHashCode_SameInstance() {
        // Arrange
        DrugSupplier supplier = new DrugSupplier("123", "Supplier Name");

        // Act and Assert
        assertEquals(supplier.hashCode(), supplier.hashCode());
    }

    @Test
    public void testHashCode_SameFields() {
        // Arrange
        DrugSupplier supplier1 = new DrugSupplier("123", "Supplier Name");
        DrugSupplier supplier2 = new DrugSupplier("123", "Supplier Name");

        // Act and Assert
        assertEquals(supplier1.hashCode(), supplier2.hashCode());
    }

    @Test
    public void testHashCode_DifferentId() {
        // Arrange
        DrugSupplier supplier1 = new DrugSupplier("123", "Supplier Name");
        DrugSupplier supplier2 = new DrugSupplier("456", "Supplier Name");

        // Act and Assert
        assertNotEquals(supplier1.hashCode(), supplier2.hashCode());
    }
    @Test
    public void testHashCode_DifferentName() {
        // Arrange
        DrugSupplier supplier1 = new DrugSupplier("123", "Supplier Name");
        DrugSupplier supplier2 = new DrugSupplier("123", "Different Name");

        // Act and Assert
        assertNotEquals(supplier1.hashCode(), supplier2.hashCode());
    }

    @Test
    public void testToString() {
        // Arrange
        DrugSupplier supplier = new DrugSupplier("123", "Supplier Name");

        // Act
        String toStringResult = supplier.toString();

        // Assert
        String expectedToString = "DrugSupplier(id=123, name=Supplier Name)";
        assertEquals(expectedToString, toStringResult);
    }

    @Test
    public void testCanEqual_SameInstance() {
        // Arrange
        DrugSupplier supplier = new DrugSupplier("123", "Supplier Name");

        // Act and Assert
        assertTrue(supplier.canEqual(supplier));
    }

    @Test
    public void testCanEqual_SameFields() {
        // Arrange
        DrugSupplier supplier1 = new DrugSupplier("123", "Supplier Name");
        DrugSupplier supplier2 = new DrugSupplier("123", "Supplier Name");

        // Act and Assert
        assertTrue(supplier1.canEqual(supplier2));
    }

    @Test
    public void testCanEqual_DifferentClass() {
        // Arrange
        DrugSupplier supplier = new DrugSupplier("123", "Supplier Name");
        String differentObject = "Not a DrugSupplier object";

        // Act and Assert
        assertFalse(supplier.canEqual(differentObject));
    }
}
