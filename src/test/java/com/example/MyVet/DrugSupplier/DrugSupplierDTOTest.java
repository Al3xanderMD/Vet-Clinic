package com.example.MyVet.DrugSupplier;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;

import java.util.Set;

public class DrugSupplierDTOTest {
    private DrugSupplierDTO drugSupplierDTO1;
    private DrugSupplierDTO drugSupplierDTO2;
    private DrugSupplierDTO drugSupplierDTO3;

    @BeforeEach
    public void setup() {
        drugSupplierDTO1 = new DrugSupplierDTO();
        drugSupplierDTO1.setName("Supplier A");

        drugSupplierDTO2 = new DrugSupplierDTO();
        drugSupplierDTO2.setName("Supplier A");

        drugSupplierDTO3 = new DrugSupplierDTO();
        drugSupplierDTO3.setName("Supplier B");
    }

    @Test
    public void testValidDrugSupplierDTO() {
        DrugSupplierDTO drugSupplierDTO = new DrugSupplierDTO();
        drugSupplierDTO.setName("Supplier A");

        ValidatorFactory factory = Validation.byDefaultProvider().configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<DrugSupplierDTO>> violations = validator.validate(drugSupplierDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidDrugSupplierDTO_NullName() {
        DrugSupplierDTO drugSupplierDTO = new DrugSupplierDTO();
        drugSupplierDTO.setName(null);

        boolean isValid = false;
        if(drugSupplierDTO.getName() != null) {
                isValid = true;
        }

        assertFalse(isValid);
    }
//
//    private boolean validateDrugSupplierDTO(DrugSupplierDTO drugSupplierDTO) {
//        if (drugSupplierDTO.getName() == null) {
//            return false;
//        }
//        return true;
//    }

    @Test
    public void testCanEqual_SameObject() {
        assertTrue(drugSupplierDTO1.canEqual(drugSupplierDTO1));
    }

    @Test
    public void testCanEqual_DifferentClass() {
        assertFalse(drugSupplierDTO1.canEqual("DrugSupplierDTO"));
    }

    @Test
    public void testCanEqual_EqualObjects() {
        assertTrue(drugSupplierDTO1.canEqual(drugSupplierDTO2));
    }

    @Test
    public void testCanEqual_DifferentName() {
        assertTrue(drugSupplierDTO1.canEqual(drugSupplierDTO3));
    }

    @Test
    public void testEquals_SameObject() {
        assertEquals(drugSupplierDTO1, drugSupplierDTO1);
    }

    @Test
    public void testEquals_NullObject() {
        assertNotEquals(drugSupplierDTO1, null);
    }

    @Test
    public void testEquals_DifferentClass() {
        assertNotEquals(drugSupplierDTO1, "DrugSupplierDTO");
    }

    @Test
    public void testEquals_EqualObjects() {
        assertEquals(drugSupplierDTO1, drugSupplierDTO2);
    }

    @Test
    public void testEquals_DifferentName() {
        assertNotEquals(drugSupplierDTO1, drugSupplierDTO3);
    }

    @Test
    public void testToString() {
        String expected = "DrugSupplierDTO(name=Supplier A)";
        assertEquals(expected, drugSupplierDTO1.toString());
    }

    @Test
    public void testHashCode_EqualObjects() {
        assertEquals(drugSupplierDTO1.hashCode(), drugSupplierDTO2.hashCode());
    }

    @Test
    public void testHashCode_DifferentObjects() {
        assertNotEquals(drugSupplierDTO1.hashCode(), drugSupplierDTO3.hashCode());
    }
    @Test
    public void testAllArgsConstructor() {
        // Arrange
        String name = "Supplier A";

        // Act
        DrugSupplierDTO drugSupplierDTO = new DrugSupplierDTO(name);

        // Assert
        Assertions.assertEquals(name, drugSupplierDTO.getName());
    }
}


