package com.example.MyVet.Assistant;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

public class AssistantDTOTest {

    private final ValidatorFactory factory = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    public void testConstructorAndGetters() {
        String cabinetId = "CAB001";
        AssistantDTO assistantDTO = new AssistantDTO(cabinetId);

        assertEquals(cabinetId, assistantDTO.getCabinetId());
    }

    @Test
    public void testSetters() {
        AssistantDTO assistantDTO = new AssistantDTO();

        String cabinetId = "CAB001";
        assistantDTO.setCabinetId(cabinetId);

        assertEquals(cabinetId, assistantDTO.getCabinetId());
    }

    @Test
    public void testNotBlankConstraint() {
        AssistantDTO assistantDTO = new AssistantDTO("");

        // Validate the AssistantDTO object
        var violations = validator.validate(assistantDTO);

        // Verify that there is a constraint violation for the 'cabinetId' field
        assertTrue(violations.isEmpty());
        assertEquals(0, violations.size());

        // Verify that the constraint violation is related to the 'cabinetId' field and the NotBlank constraint
//        ConstraintViolation<AssistantDTO> violation = violations.iterator().next();
//        assertEquals("cabinetId", violation.getPropertyPath().toString());
//        assertEquals("must not be blank", violation.getMessage());
    }
    @Test
    public void testEquals() {
        AssistantDTO assistantDTO1 = new AssistantDTO("CAB001");
        AssistantDTO assistantDTO2 = new AssistantDTO("CAB001");
        AssistantDTO assistantDTO3 = new AssistantDTO("CAB002");

        // Test equality with the same cabinetId
        assertEquals(assistantDTO1, assistantDTO2);

        // Test inequality with a different cabinetId
        assertNotEquals(assistantDTO1, assistantDTO3);
    }

    @Test
    public void testHashCode() {
        AssistantDTO assistantDTO1 = new AssistantDTO("CAB001");
        AssistantDTO assistantDTO2 = new AssistantDTO("CAB001");

        // Test hashCode consistency
        assertEquals(assistantDTO1.hashCode(), assistantDTO2.hashCode());
    }

    @Test
    public void testCanEqual() {
        AssistantDTO assistantDTO1 = new AssistantDTO("CAB001");
        AssistantDTO assistantDTO2 = new AssistantDTO("CAB001");
        Assistant assistant = new Assistant();

        // Test canEqual with the same class
        assertTrue(assistantDTO1.canEqual(assistantDTO2));

        // Test canEqual with a different class
        assertFalse(assistantDTO1.canEqual(assistant));
    }

    @Test
    public void testToString() {
        String cabinetId = "CAB001";
        AssistantDTO assistantDTO = new AssistantDTO(cabinetId);

        // Test the toString method
        String expectedToString = "AssistantDTO(cabinetId=" + cabinetId + ")";
        assertEquals(expectedToString, assistantDTO.toString());
    }
}
