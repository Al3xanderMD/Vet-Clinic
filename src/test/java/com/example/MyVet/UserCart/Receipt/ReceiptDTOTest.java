package com.example.MyVet.UserCart.Receipt;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ReceiptDTOTest {
    private static Validator validator;

    @BeforeAll
    public static void setupValidator() {
        Configuration<?> configuration = Validation.byDefaultProvider().configure();
        configuration.messageInterpolator(new ParameterMessageInterpolator());
        ValidatorFactory factory = configuration.buildValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testNoArgsConstructor() {
        // Arrange & Act
        ReceiptDTO receiptDTO = new ReceiptDTO();

        // Assert
        Assertions.assertNotNull(receiptDTO);
        Assertions.assertEquals(new ArrayList<>(), receiptDTO.getDrugsSoldId());
    }

    @Test
    public void testAllArgsConstructor() {
        // Arrange
        List<String> drugsSoldId = List.of("123", "456", "789");

        // Act
        ReceiptDTO receiptDTO = new ReceiptDTO(drugsSoldId);

        // Assert
        Assertions.assertNotNull(receiptDTO);
        Assertions.assertEquals(drugsSoldId, receiptDTO.getDrugsSoldId());
    }

    @Test
    public void testNotBlankValidation_Success() {
        // Arrange
        ReceiptDTO receiptDTO = new ReceiptDTO(List.of("123", "456"));

        // Act
        Set<ConstraintViolation<ReceiptDTO>> violations = validator.validate(receiptDTO);

        // Assert
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    public void testNotBlankValidation_Failure() {
        // Arrange
        ReceiptDTO receiptDTO = new ReceiptDTO(List.of("", "456", " "));

        // Act
        Set<ConstraintViolation<ReceiptDTO>> violations = validator.validate(receiptDTO);

        // Assert
        Assertions.assertEquals(0, violations.size());
        for (ConstraintViolation<ReceiptDTO> violation : violations) {
            Assertions.assertEquals("must not be blank", violation.getMessage());
        }
    }

    private Set<ConstraintViolation<ReceiptDTO>> validateReceiptDTO(ReceiptDTO receiptDTO) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(receiptDTO);
    }

    @Test
    public void testGettersAndSetters() {
        // Arrange
        List<String> drugsSoldId = List.of("123", "456", "789");
        ReceiptDTO receiptDTO = new ReceiptDTO();

        // Act
        receiptDTO.setDrugsSoldId(drugsSoldId);

        // Assert
        Assertions.assertEquals(drugsSoldId, receiptDTO.getDrugsSoldId());
    }

    @Test
    public void testEquals() {
        // Arrange
        List<String> drugsSoldId1 = List.of("123", "456");
        List<String> drugsSoldId2 = List.of("123", "456");
        List<String> drugsSoldId3 = List.of("789", "101112");

        ReceiptDTO receiptDTO1 = new ReceiptDTO(drugsSoldId1);
        ReceiptDTO receiptDTO2 = new ReceiptDTO(drugsSoldId2);
        ReceiptDTO receiptDTO3 = new ReceiptDTO(drugsSoldId3);

        // Assert
        Assertions.assertEquals(receiptDTO1, receiptDTO2); // Same values, so should be equal
        Assertions.assertNotEquals(receiptDTO1, receiptDTO3); // Different drugsSoldId, so not equal
        Assertions.assertEquals(receiptDTO1, receiptDTO1); // Same instance, should be equal
        Assertions.assertNotEquals(receiptDTO1, null); // Comparing to null, should not be equal
    }

    @Test
    public void testHashCode() {
        // Arrange
        List<String> drugsSoldId1 = List.of("123", "456");
        List<String> drugsSoldId2 = List.of("123", "456");

        ReceiptDTO receiptDTO1 = new ReceiptDTO(drugsSoldId1);
        ReceiptDTO receiptDTO2 = new ReceiptDTO(drugsSoldId2);

        // Act
        int hashCode1 = receiptDTO1.hashCode();
        int hashCode2 = receiptDTO2.hashCode();

        // Assert
        Assertions.assertEquals(hashCode1, hashCode2);
    }

    @Test
    public void testToString() {
        // Arrange
        List<String> drugsSoldId = List.of("123", "456");

        ReceiptDTO receiptDTO = new ReceiptDTO(drugsSoldId);

        // Act
        String toString = receiptDTO.toString();

        // Assert
        Assertions.assertTrue(toString.contains("drugsSoldId=" + drugsSoldId));
    }

}
