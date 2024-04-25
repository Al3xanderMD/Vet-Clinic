package com.example.MyVet.MedicalStaff;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import java.util.Set;

import static com.mongodb.assertions.Assertions.assertFalse;
import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MedicalStaffDTOTest {
    private Validator validator;

    public MedicalStaffDTOTest() {

        MessageInterpolator interpolator = new ParameterMessageInterpolator();
        ValidatorFactory factory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(interpolator)
                .buildValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidMedicalStaffDTO() {
        MedicalStaffDTO dto = new MedicalStaffDTO("Monday-Friday", 5000, 5, "CAB001");

        Set<ConstraintViolation<MedicalStaffDTO>> violations = validator.validate(dto);
        assertEquals(0, violations.size());
    }

    @Test
    public void testInvalidMedicalStaffDTO() {
        MedicalStaffDTO dto = new MedicalStaffDTO("", -100, 0, "");

        Set<ConstraintViolation<MedicalStaffDTO>> violations = validator.validate(dto);
        assertEquals(0, violations.size());

        for (ConstraintViolation<MedicalStaffDTO> violation : violations) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            System.out.println(propertyPath + ": " + message);
        }
    }
    @Test
    public void testConstructor() {
        MedicalStaffDTO dto = new MedicalStaffDTO("Monday-Friday", 5000, 5, "CAB001");

        assertEquals("Monday-Friday", dto.getSchedule());
        assertEquals(5000, dto.getSalary());
        assertEquals(5, dto.getExperience());
        assertEquals("CAB001", dto.getCabinetId());
    }

    @Test
    public void testToString() {
        MedicalStaffDTO dto = new MedicalStaffDTO("Monday-Friday", 5000, 5, "CAB001");

        String expectedToString = "MedicalStaffDTO(schedule=Monday-Friday, salary=5000, experience=5, cabinetId=CAB001)";
        assertEquals(expectedToString, dto.toString());
    }

    @Test
    public void testHashCode() {
        MedicalStaffDTO dto1 = new MedicalStaffDTO("Monday-Friday", 5000, 5, "CAB001");
        MedicalStaffDTO dto2 = new MedicalStaffDTO("Monday-Friday", 5000, 5, "CAB001");

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testEquals() {
        MedicalStaffDTO dto1 = new MedicalStaffDTO("Monday-Friday", 5000, 5, "CAB001");
        MedicalStaffDTO dto2 = new MedicalStaffDTO("Monday-Friday", 5000, 5, "CAB001");
        MedicalStaffDTO dto3 = new MedicalStaffDTO("Saturday-Sunday", 6000, 3, "CAB002");

        assertTrue(dto1.equals(dto2));
        assertFalse(dto1.equals(dto3));
        assertFalse(dto1.equals(null));
    }

    @Test
    public void testCanEqual() {
        MedicalStaffDTO dto1 = new MedicalStaffDTO("Monday-Friday", 5000, 5, "CAB001");
        MedicalStaffDTO dto2 = new MedicalStaffDTO("Monday-Friday", 5000, 5, "CAB001");
        MedicalStaffDTO dto3 = new MedicalStaffDTO("Saturday-Sunday", 6000, 3, "CAB002");

        assertTrue(dto1.canEqual(dto2));
        assertTrue(dto1.canEqual(dto3));
    }

    @Test
    public void testSettersAndGetters() {
        MedicalStaffDTO dto = new MedicalStaffDTO();
        dto.setSchedule("Monday-Friday");
        dto.setSalary(5000);
        dto.setExperience(5);
        dto.setCabinetId("CAB001");

        assertEquals("Monday-Friday", dto.getSchedule());
        assertEquals(5000, dto.getSalary());
        assertEquals(5, dto.getExperience());
        assertEquals("CAB001", dto.getCabinetId());
    }
}
