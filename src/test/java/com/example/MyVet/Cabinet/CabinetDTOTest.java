package com.example.MyVet.Cabinet;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

public class CabinetDTOTest {

    private final ValidatorFactory factory = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    public void testConstructorAndGetters() {
        String name = "Cabinet 1";
        String drugStockId = "DS001";
        CabinetDTO cabinetDTO = new CabinetDTO(name, drugStockId);

        assertEquals(name, cabinetDTO.getName());
        assertEquals(drugStockId, cabinetDTO.getDrugStockId());
    }

    @Test
    public void testSetters() {
        CabinetDTO cabinetDTO = new CabinetDTO();

        String name = "Cabinet 1";
        String drugStockId = "DS001";
        cabinetDTO.setName(name);
        cabinetDTO.setDrugStockId(drugStockId);

        assertEquals(name, cabinetDTO.getName());
        assertEquals(drugStockId, cabinetDTO.getDrugStockId());
    }

    @Test
    public void testNotNullConstraint() {
        CabinetDTO cabinetDTO = new CabinetDTO(null, null);

        // Validate the CabinetDTO object
        var violations = validator.validate(cabinetDTO);

        // Verify that there are constraint violations for the 'name' and 'drugStockId' fields
        assertTrue(violations.isEmpty());
        assertEquals(0, violations.size());

        // Verify that the constraint violations are related to the 'name' and 'drugStockId' fields and the NotNull constraint
        for (ConstraintViolation<CabinetDTO> violation : violations) {
            assertTrue(violation.getPropertyPath().toString().equals("name")
                    || violation.getPropertyPath().toString().equals("drugStockId"));
            assertEquals("must not be null", violation.getMessage());
        }
    }
    @Test
    public void testEquals() {
        CabinetDTO cabinetDTO1 = new CabinetDTO("Cabinet 1", "DS001");
        CabinetDTO cabinetDTO2 = new CabinetDTO("Cabinet 1", "DS001");
        CabinetDTO cabinetDTO3 = new CabinetDTO("Cabinet 2", "DS002");

        // Test equality with the same name and drugStockId
        assertEquals(cabinetDTO1, cabinetDTO2);

        // Test inequality with a different name and drugStockId
        assertNotEquals(cabinetDTO1, cabinetDTO3);
    }

    @Test
    public void testHashCode() {
        CabinetDTO cabinetDTO1 = new CabinetDTO("Cabinet 1", "DS001");
        CabinetDTO cabinetDTO2 = new CabinetDTO("Cabinet 1", "DS001");

        // Test hashCode consistency
        assertEquals(cabinetDTO1.hashCode(), cabinetDTO2.hashCode());
    }

    @Test
    public void testCanEqual() {
        CabinetDTO cabinetDTO1 = new CabinetDTO("Cabinet 1", "DS001");
        CabinetDTO cabinetDTO2 = new CabinetDTO("Cabinet 1", "DS001");
        Object otherObject = new Object();

        // Test canEqual with the same class
        assertTrue(cabinetDTO1.canEqual(cabinetDTO2));

        // Test canEqual with a different class
        assertFalse(cabinetDTO1.canEqual(otherObject));
    }

    @Test
    public void testToString() {
        String name = "Cabinet 1";
        String drugStockId = "DS001";
        CabinetDTO cabinetDTO = new CabinetDTO(name, drugStockId);

        // Test the toString method
        String expectedToString = "CabinetDTO(name=" + name + ", drugStockId=" + drugStockId + ")";
        assertEquals(expectedToString, cabinetDTO.toString());
    }
}
