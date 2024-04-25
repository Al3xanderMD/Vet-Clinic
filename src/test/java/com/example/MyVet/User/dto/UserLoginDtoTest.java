package com.example.MyVet.User.dto;

import static org.junit.jupiter.api.Assertions.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotBlank;
import java.util.Set;

public class UserLoginDtoTest {

    private Validator validator;

    public UserLoginDtoTest() {
        ValidatorFactory factory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidUserLoginDto() {
        UserLoginDto userLoginDto = new UserLoginDto();
        Set<ConstraintViolation<UserLoginDto>> violations = validator.validate(userLoginDto);
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidUserLoginDto() {
        UserLoginDto userLoginDto = new UserLoginDto();
        Set<ConstraintViolation<UserLoginDto>> violations = validator.validate(userLoginDto);
        Assertions.assertEquals(0, violations.size());
    }


    @Test
    void testGettersAndSetters() {
        // given
        String username = "john_doe";
        String password = "secret123";
        UserLoginDto userLoginDto = new UserLoginDto();

        // when
        userLoginDto.setEmail(username);
        userLoginDto.setPassword(password);

        // then
        assertEquals(username, userLoginDto.getEmail());
        assertEquals(password, userLoginDto.getPassword());
    }

    @Test
    void testConstructorWithoutParameters() {
        // given
        UserLoginDto userLoginDto = new UserLoginDto();

        // then
        assertNull(userLoginDto.getEmail());
        assertNull(userLoginDto.getPassword());
    }
    @Test
    void testAnnotations(){
        assertFalse(UserLoginDto.class.isAnnotationPresent(Data.class));
        assertFalse(UserLoginDto.class.isAnnotationPresent(NoArgsConstructor.class));
        assertFalse(UserLoginDto.class.isAnnotationPresent(NotBlank.class));
    }
    @Test
    void testEquals() {
        UserLoginDto dto1 = new UserLoginDto();
        dto1.setEmail("user1");
        dto1.setPassword("pass1");

        UserLoginDto dto2 = new UserLoginDto();
        dto2.setEmail("user1");
        dto2.setPassword("pass1");

        UserLoginDto dto3 = new UserLoginDto();
        dto3.setEmail("user2");
        dto3.setPassword("pass2");

        assertTrue(dto1.equals(dto2));
        assertFalse(dto1.equals(dto3));
    }

    @Test
    void testToString() {
        UserLoginDto dto = new UserLoginDto();
        dto.setEmail("user1");
        dto.setPassword("pass1");

        String expectedString = "UserLoginDto(email=user1, password=pass1)";
        assertEquals(expectedString, dto.toString());
    }

    @Test
    void testCanEqual() {
        UserLoginDto dto1 = new UserLoginDto();
        dto1.setEmail("user1");
        dto1.setPassword("pass1");

        UserLoginDto dto2 = new UserLoginDto();
        dto2.setEmail("user1");
        dto2.setPassword("pass1");

        UserLoginDto dto3 = new UserLoginDto();
        dto3.setEmail("user2");
        dto3.setPassword("pass2");

        assertTrue(dto1.canEqual(dto2));
        assertTrue(dto2.canEqual(dto1));
        assertTrue(dto1.canEqual(dto3));
    }

    @Test
    void testHashCode() {
        UserLoginDto dto1 = new UserLoginDto();
        dto1.setEmail("user1");
        dto1.setPassword("pass1");

        UserLoginDto dto2 = new UserLoginDto();
        dto2.setEmail("user1");
        dto2.setPassword("pass1");

        UserLoginDto dto3 = new UserLoginDto();
        dto3.setEmail("user2");
        dto3.setPassword("pass2");

        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
    }
    @Test
    void testEqualsReflexive() {
        UserLoginDto dto = new UserLoginDto();
        dto.setEmail("user1");
        dto.setPassword("pass1");

        assertTrue(dto.equals(dto));
    }

    @Test
    void testEqualsSymmetric() {
        UserLoginDto dto1 = new UserLoginDto();
        dto1.setEmail("user1");
        dto1.setPassword("pass1");

        UserLoginDto dto2 = new UserLoginDto();
        dto2.setEmail("user1");
        dto2.setPassword("pass1");

        assertTrue(dto1.equals(dto2));
        assertTrue(dto2.equals(dto1));
    }

    @Test
    void testEqualsTransitive() {
        UserLoginDto dto1 = new UserLoginDto();
        dto1.setEmail("user1");
        dto1.setPassword("pass1");

        UserLoginDto dto2 = new UserLoginDto();
        dto2.setEmail("user1");
        dto2.setPassword("pass1");

        UserLoginDto dto3 = new UserLoginDto();
        dto3.setEmail("user1");
        dto3.setPassword("pass1");

        assertTrue(dto1.equals(dto2));
        assertTrue(dto2.equals(dto3));
        assertTrue(dto1.equals(dto3));
    }

    @Test
    void testEqualsConsistent() {
        UserLoginDto dto1 = new UserLoginDto();
        dto1.setEmail("user1");
        dto1.setPassword("pass1");

        UserLoginDto dto2 = new UserLoginDto();
        dto2.setEmail("user1");
        dto2.setPassword("pass1");

        assertTrue(dto1.equals(dto2));
        assertTrue(dto1.equals(dto2));
        assertTrue(dto1.equals(dto2));
    }

    @Test
    void testEqualsNull() {
        UserLoginDto dto = new UserLoginDto();
        dto.setEmail("user1");
        dto.setPassword("pass1");

        assertFalse(dto.equals(null));
    }

    @Test
    void testHashCodeConsistent() {
        UserLoginDto dto = new UserLoginDto();
        dto.setEmail("user1");
        dto.setPassword("pass1");

        int hashCode = dto.hashCode();
        assertEquals(hashCode, dto.hashCode());
    }

    @Test
    void testHashCodeSameForEqualObjects() {
        UserLoginDto dto1 = new UserLoginDto();
        dto1.setEmail("user1");
        dto1.setPassword("pass1");

        UserLoginDto dto2 = new UserLoginDto();
        dto2.setEmail("user1");
        dto2.setPassword("pass1");

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testHashCodeDifferentForNonEqualObjects() {
        UserLoginDto dto1 = new UserLoginDto();
        dto1.setEmail("user1");
        dto1.setPassword("pass1");

        UserLoginDto dto2 = new UserLoginDto();
        dto2.setEmail("user2");
        dto2.setPassword("pass2");

        assertNotEquals(dto1.hashCode(), dto2.hashCode());
    }
    @Test
    public void testConstructor() {
        // Arrange
        String email = "test@example.com";
        String password = "password";

        // Act
        UserLoginDto userLoginDto = new UserLoginDto(email, password);

        // Assert
        assertEquals(email, userLoginDto.getEmail());
        assertEquals(password, userLoginDto.getPassword());
    }

}
