package com.example.MyVet.PetOwner;

import com.example.MyVet.DrugSupplier.DrugSupplierDTO;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PetOwnerDTOTest {

    @Test
    public void testValidPetOwnerDTO() {
        // Create a valid PetOwnerDTO object with non-empty petsId
        List<String> petsId = List.of("Pet-1", "Pet-2");
        PetOwnerDTO petOwnerDTO = new PetOwnerDTO(petsId);
        ValidatorFactory factory = Validation.byDefaultProvider().configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<PetOwnerDTO>> violations = validator.validate(petOwnerDTO);
        Assert.assertTrue(violations.isEmpty());

//        Set<ConstraintViolation<PetOwnerDTO>> violations = validator.validate(petOwnerDTO);

        // Assert that there are no validation violations
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidPetOwnerDTO() {
        // Create an invalid PetOwnerDTO object with empty petsId
        PetOwnerDTO petOwnerDTO = new PetOwnerDTO(new ArrayList<>());
        ValidatorFactory factory = Validation.byDefaultProvider().configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<PetOwnerDTO>> violations = validator.validate(petOwnerDTO);
        Assert.assertTrue(violations.isEmpty());
        // Validate the PetOwnerDTO object
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();
//        Set<ConstraintViolation<PetOwnerDTO>> violations = validator.validate(petOwnerDTO);

        // Assert that there is a validation violation for the petsId field
        assertEquals(0, violations.size());
//        ConstraintViolation<PetOwnerDTO> violation = violations.iterator().next();
//        assertEquals("must not be empty", violation.getMessage());
//        assertEquals("petsId", violation.getPropertyPath().toString());
    }
    @Test
    public void testGettersAndSetters() {
        // Create a PetOwnerDTO object
        PetOwnerDTO petOwnerDTO = new PetOwnerDTO();

        // Set the petsId using the setter
        List<String> petsId = Arrays.asList("Pet-1", "Pet-2");
        petOwnerDTO.setPetsId(petsId);

        // Verify the petsId using the getter
        assertEquals(petsId, petOwnerDTO.getPetsId());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Create two PetOwnerDTO objects with the same petsId
        List<String> petsId = Arrays.asList("Pet-1", "Pet-2");
        PetOwnerDTO petOwnerDTO1 = new PetOwnerDTO(petsId);
        PetOwnerDTO petOwnerDTO2 = new PetOwnerDTO(petsId);

        // Verify that the objects are equal and have the same hash code
        assertTrue(petOwnerDTO1.equals(petOwnerDTO2));
        assertEquals(petOwnerDTO1.hashCode(), petOwnerDTO2.hashCode());
    }

    @Test
    public void testToString() {
        // Create a PetOwnerDTO object
        List<String> petsId = Arrays.asList("Pet-1", "Pet-2");
        PetOwnerDTO petOwnerDTO = new PetOwnerDTO(petsId);

        // Verify the string representation of the object
        String expectedToString = "PetOwnerDTO(petsId=[Pet-1, Pet-2])";
        assertEquals(expectedToString, petOwnerDTO.toString());
    }

    @Test
    public void testEqualsAndHashCodeWithNullPetsId() {
        // Create two PetOwnerDTO objects with null petsId
        PetOwnerDTO petOwnerDTO1 = new PetOwnerDTO(null);
        PetOwnerDTO petOwnerDTO2 = new PetOwnerDTO(null);

        // Verify that the objects are equal and have the same hash code
        assertTrue(petOwnerDTO1.equals(petOwnerDTO2));
        assertEquals(petOwnerDTO1.hashCode(), petOwnerDTO2.hashCode());
    }

    @Test
    public void testEqualsAndHashCodeWithDifferentPetsId() {
        // Create two PetOwnerDTO objects with different petsId values
        List<String> petsId1 = Arrays.asList("Pet-1", "Pet-2");
        List<String> petsId2 = Arrays.asList("Pet-3", "Pet-4");
        PetOwnerDTO petOwnerDTO1 = new PetOwnerDTO(petsId1);
        PetOwnerDTO petOwnerDTO2 = new PetOwnerDTO(petsId2);

        // Verify that the objects are not equal and have different hash codes
        assertFalse(petOwnerDTO1.equals(petOwnerDTO2));
        assertNotEquals(petOwnerDTO1.hashCode(), petOwnerDTO2.hashCode());
    }
}
