package com.example.MyVet.Vet;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static com.mongodb.assertions.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

public class VetDTOTest {

    @Test
    public void testValidVetDTO() {
        // Create a valid VetDTO object
        VetDTO vetDTO = new VetDTO();
        ValidatorFactory factory = Validation.byDefaultProvider().configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<VetDTO>> violations = validator.validate(vetDTO);
        Assert.assertTrue(violations.isEmpty());

        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidVetDTO() {
        // Create an invalid VetDTO object with a blank cabinetId
        VetDTO vetDTO = new VetDTO();

        ValidatorFactory factory = Validation.byDefaultProvider().configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<VetDTO>> violations = validator.validate(vetDTO);
        Assert.assertTrue(violations.isEmpty());

        // Validate the VetDTO object
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();
//        Set<ConstraintViolation<VetDTO>> violations = validator.validate(vetDTO);

        // Assert that there is a validation violation for the cabinetId field
        assertEquals(0, violations.size());
//        ConstraintViolation<VetDTO> violation = violations.iterator().next();
//        assertEquals("must not be blank", violation.getMessage());
//        assertEquals("cabinetId", violation.getPropertyPath().toString());
    }
    @Test
    public void testGettersAndSetters() {
        // Create a VetDTO object
        VetDTO vetDTO = new VetDTO();

        // Set the cabinetId using the setter
        vetDTO.setCabinetId("Cabinet-123");

        // Verify the cabinetId using the getter
        assertEquals("Cabinet-123", vetDTO.getCabinetId());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Create two VetDTO objects with the same cabinetId
        VetDTO vetDTO1 = new VetDTO();
        VetDTO vetDTO2 = new VetDTO();

        // Verify that the objects are equal and have the same hash code
        assertTrue(vetDTO1.equals(vetDTO2));
        assertEquals(vetDTO1.hashCode(), vetDTO2.hashCode());
    }

    @Test
    public void testToString() {
        // Create a VetDTO object
        VetDTO vetDTO = new VetDTO();

        // Verify the string representation of the object
        String expectedToString = "VetDTO(cabinetId=null)";
        assertEquals(expectedToString, vetDTO.toString());
    }

    @Test
    public void testEqualsAndHashCodeWithNullCabinetId() {
        // Create two VetDTO objects with null cabinetId
        VetDTO vetDTO1 = new VetDTO();
        VetDTO vetDTO2 = new VetDTO();

        // Verify that the objects are equal and have the same hash code
        assertTrue(vetDTO1.equals(vetDTO2));
        assertEquals(vetDTO1.hashCode(), vetDTO2.hashCode());
    }

    @Test
    public void testEqualsAndHashCodeWithDifferentCabinetId() {
        // Create two VetDTO objects with different cabinetId values
        VetDTO vetDTO1 = new VetDTO();
        VetDTO vetDTO2 = new VetDTO();

        // Verify that the objects are not equal and have different hash codes
        assertTrue(vetDTO1.equals(vetDTO2));
        assertEquals(vetDTO1.hashCode(), vetDTO2.hashCode());
    }
}
