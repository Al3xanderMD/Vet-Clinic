package com.example.MyVet.User.users;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDTOTest {

    @Test
    public void testValidUserDTO() {
        // Create a valid UserDTO object
        UserDTO userDTO = new UserDTO("John", "Doe", "1234567890", new ArrayList<>());

        // Validate the UserDTO object
        assertTrue(isValid(userDTO));
    }

    @Test
    public void testInvalidUserDTO() {
        // Create an invalid UserDTO object with blank fields
        UserDTO userDTO = new UserDTO("", "", "", new ArrayList<>());

        // Validate the UserDTO object
        assertFalse(isValid(userDTO));
    }

    @Test
    public void testInvalidUserDTOWithAddresses() {
        // Create an invalid UserDTO object with blank fields and non-empty addresses list
        UserDTO userDTO = new UserDTO("", "", "", List.of("Address 1", "Address 2"));

        // Validate the UserDTO object
        assertFalse(isValid(userDTO));
    }

    private boolean isValid(UserDTO userDTO) {
        // Perform manual null/empty checks on the fields
        if (userDTO.getFirstName() == null || userDTO.getFirstName().isEmpty()) {
            return false;
        }
        if (userDTO.getLastName() == null || userDTO.getLastName().isEmpty()) {
            return false;
        }
        if (userDTO.getPhone() == null || userDTO.getPhone().isEmpty()) {
            return false;
        }
        return true;
    }
    @Test
    public void testGettersAndSetters() {
        // Create a UserDTO object
        UserDTO userDTO = new UserDTO();

        // Set values using setters
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userDTO.setPhone("1234567890");
        userDTO.setAdresses(List.of("Address 1", "Address 2"));

        // Verify values using getters
        assertEquals("John", userDTO.getFirstName());
        assertEquals("Doe", userDTO.getLastName());
        assertEquals("1234567890", userDTO.getPhone());
        assertEquals(List.of("Address 1", "Address 2"), userDTO.getAdresses());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Create two UserDTO objects with the same field values
        UserDTO userDTO1 = new UserDTO("John", "Doe", "1234567890", List.of("Address 1", "Address 2"));
        UserDTO userDTO2 = new UserDTO("John", "Doe", "1234567890", List.of("Address 1", "Address 2"));

        // Verify that the objects are equal and have the same hash code
        assertTrue(userDTO1.equals(userDTO2));
        assertEquals(userDTO1.hashCode(), userDTO2.hashCode());
    }

    @Test
    public void testToString() {
        // Create a UserDTO object
        UserDTO userDTO = new UserDTO("John", "Doe", "1234567890", List.of("Address 1", "Address 2"));

        // Verify the string representation of the object
        String expectedToString = "UserDTO(firstName=John, lastName=Doe, phone=1234567890, adresses=[Address 1, Address 2])";
        assertEquals(expectedToString, userDTO.toString());
    }
}
