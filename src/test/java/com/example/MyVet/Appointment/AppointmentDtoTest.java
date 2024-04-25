package com.example.MyVet.Appointment;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentDtoTest {

    private final ValidatorFactory factory = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory();
    private final Validator validator = factory.getValidator();


    @Test
    public void testValidAppointmentDto() {
        AppointmentDto appointmentDto = new AppointmentDto(
                "petId", "ownerId", "vetId", 2023, 5, 25, 10, 30
        );

        Set<ConstraintViolation<AppointmentDto>> violations = validator.validate(appointmentDto);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidAppointmentDto() {
        AppointmentDto appointmentDto = new AppointmentDto(
                null, null, null, 2023, 5, 25, 10, 30
        );

        Set<ConstraintViolation<AppointmentDto>> violations = validator.validate(appointmentDto);
        assertTrue(violations.isEmpty());
        assertEquals(0, violations.size());

        // Assert specific error messages
        for (ConstraintViolation<AppointmentDto> violation : violations) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();

            switch (propertyPath) {
                case "petId":
                    assertEquals("Pet id cannot be null", message);
                    break;
                case "ownerId":
                    assertEquals("PetOwner cannot be null", message);
                    break;
                case "VetId":
                    assertEquals("Doctor cannot be null", message);
                    break;
            }
        }
    }
    @Test
    public void testPetIdGetterAndSetter() {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setPetId("petId");
        assertEquals("petId", appointmentDto.getPetId());
    }

    @Test
    public void testOwnerIdGetterAndSetter() {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setOwnerId("ownerId");
        assertEquals("ownerId", appointmentDto.getOwnerId());
    }

    @Test
    public void testVetIdGetterAndSetter() {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setVetId("vetId");
        assertEquals("vetId", appointmentDto.getVetId());
    }

    @Test
    public void testYearGetterAndSetter() {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setYear(2023);
        assertEquals(2023, appointmentDto.getYear());
    }

    @Test
    public void testMonthGetterAndSetter() {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setMonth(5);
        assertEquals(5, appointmentDto.getMonth());
    }

    @Test
    public void testDayGetterAndSetter() {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setDay(25);
        assertEquals(25, appointmentDto.getDay());
    }

    @Test
    public void testHourGetterAndSetter() {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setHour(10);
        assertEquals(10, appointmentDto.getHour());
    }

    @Test
    public void testMinuteGetterAndSetter() {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setMinute(30);
        assertEquals(30, appointmentDto.getMinute());
    }
    @Test
    public void testEqualsAndHashCode() {
        AppointmentDto dto1 = new AppointmentDto("petId", "ownerId", "vetId", 2023, 5, 25, 10, 30);
        AppointmentDto dto2 = new AppointmentDto("petId", "ownerId", "vetId", 2023, 5, 25, 10, 30);
        AppointmentDto dto3 = new AppointmentDto("petId", "ownerId", "vetId", 2023, 5, 25, 12, 0);

        // Test equals method
        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);

        // Test hashCode method
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
    }

    @Test
    public void testCanEqual() {
        AppointmentDto dto1 = new AppointmentDto("petId", "ownerId", "vetId", 2023, 5, 25, 10, 30);
        AppointmentDto dto2 = new AppointmentDto("petId", "ownerId", "vetId", 2023, 5, 25, 10, 30);
        AppointmentDto dto3 = new AppointmentDto("petId", "ownerId", "vetId", 2023, 5, 25, 12, 0);

        assertTrue(dto1.canEqual(dto2));
        assertTrue(dto2.canEqual(dto1));
        assertTrue(dto1.canEqual(dto3));
        assertTrue(dto3.canEqual(dto1));
    }

    @Test
    public void testToString() {
        AppointmentDto dto = new AppointmentDto("petId", "ownerId", "vetId", 2023, 5, 25, 10, 30);
        String expectedString = "AppointmentDto(petId=petId, ownerId=ownerId, VetId=vetId, year=2023, month=5, day=25, hour=10, minute=30)";

        assertEquals(expectedString, dto.toString());
    }
}
