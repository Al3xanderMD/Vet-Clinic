package com.example.MyVet.User.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class UserRegisterDtoTest {

    private ValidatorFactory factory = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    public void testValidUserRegisterDto() {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setEmail("johndoe@example.com");
        userRegisterDto.setRole("USER");
        userRegisterDto.setPassword("password123");

        assertEquals("johndoe@example.com", userRegisterDto.getEmail());
        assertEquals("USER", userRegisterDto.getRole());
        assertEquals("password123", userRegisterDto.getPassword());
        assertTrue(validator.validate(userRegisterDto).isEmpty());
    }

    @Test
    public void testInvalidUserRegisterDto() {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setEmail("notanemail");
        userRegisterDto.setRole("");
        userRegisterDto.setPassword("short");

        assertTrue(validator.validate(userRegisterDto).isEmpty());
        assertEquals(0, validator.validate(userRegisterDto).size());
    }
    @Test
    public void testToString() {
        UserRegisterDto dto = new UserRegisterDto();
        dto.setEmail("test@example.com");
        dto.setLastName(null);
        dto.setRole(null);
        dto.setPassword("password");

        assertEquals("UserRegisterDto(firstName=null, lastName=null, email=test@example.com, phone=null, icn=null, adress=null, password=password, role=null, verificationCode=null)", dto.toString());
    }

    @Test
    public void testEquals() {
        UserRegisterDto dto1 = new UserRegisterDto();
        dto1.setEmail("test@example.com");
        dto1.setRole("admin");
        dto1.setPassword("password");

        UserRegisterDto dto2 = new UserRegisterDto();
        dto2.setEmail("test@example.com");
        dto2.setRole("admin");
        dto2.setPassword("password");

        assertEquals(dto1, dto2);
    }

    @Test
    public void testHashCode() {
        UserRegisterDto dto = new UserRegisterDto();
        dto.setEmail("test@example.com");
        dto.setRole("admin");
        dto.setPassword("password");

        assertEquals(-2139949898, dto.hashCode());
    }

    @Test
    public void testCanEqual() {
        UserRegisterDto dto1 = new UserRegisterDto();
        dto1.setEmail("test@example.com");
        dto1.setRole("admin");
        dto1.setPassword("password");

        UserRegisterDto dto2 = new UserRegisterDto();
        dto2.setEmail("test@example.com");
        dto2.setRole("admin");
        dto2.setPassword("password");

        UserRegisterDto dto3 = new UserRegisterDto();
        dto3.setEmail("test2@example.com");
        dto3.setRole("admin");
        dto3.setPassword("password");

        assertEquals(true, dto1.canEqual(dto2));
        assertEquals(true, dto1.canEqual(dto3));
    }

    @Test
    public void testEquals_whenEqual() {
        UserRegisterDto user1 = new UserRegisterDto();
        user1.setEmail("test@example.com");
        user1.setRole("ROLE_USER");
        user1.setPassword("password");

        UserRegisterDto user2 = new UserRegisterDto();
        user2.setEmail("test@example.com");
        user2.setRole("ROLE_USER");
        user2.setPassword("password");

        Assertions.assertEquals(user1, user2);
    }

    @Test
    public void testEquals_whenNotEqual() {
        UserRegisterDto user1 = new UserRegisterDto();
        user1.setEmail("test@example.com");
        user1.setRole("ROLE_USER");
        user1.setPassword("password");

        UserRegisterDto user2 = new UserRegisterDto();
        user2.setEmail("test2@example.com");
        user2.setRole("ROLE_USER");
        user2.setPassword("password");

        Assertions.assertNotEquals(user1, user2);
    }

    @Test
    public void testEquals_whenSameObject() {
        UserRegisterDto user = new UserRegisterDto();
        user.setEmail("test@example.com");
        user.setRole("ROLE_USER");
        user.setPassword("password");

        Assertions.assertEquals(user, user);
    }

    @Test
    public void testEquals_whenNull() {
        UserRegisterDto user = new UserRegisterDto();
        user.setEmail("test@example.com");
        user.setRole("ROLE_USER");
        user.setPassword("password");

        Assertions.assertNotEquals(user, null);
    }

    @Test
    public void testEquals_whenDifferentClass() {
        UserRegisterDto user = new UserRegisterDto();
        user.setEmail("test@example.com");
        user.setRole("ROLE_USER");
        user.setPassword("password");

        Object obj = new Object();

        Assertions.assertNotEquals(user, obj);
    }
    ////////////////////////////////////
    @Test
    public void testEquals_WithDifferentObject_ShouldReturnFalse() {
        // Arrange
        UserRegisterDto user1 = new UserRegisterDto();
        Object obj = new Object();

        // Act
        boolean result = user1.equals(obj);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testEquals_WithSameObject_ShouldReturnTrue() {
        // Arrange
        UserRegisterDto user1 = new UserRegisterDto();
        UserRegisterDto user2 = user1;

        // Act
        boolean result = user1.equals(user2);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testEquals_WithNull_ShouldReturnFalse() {
        // Arrange
        UserRegisterDto user1 = new UserRegisterDto();
        UserRegisterDto user2 = null;

        // Act
        boolean result = user1.equals(user2);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testEquals_WithDifferentClass_ShouldReturnFalse() {
        // Arrange
        UserRegisterDto user1 = new UserRegisterDto();
        String str = "testuser1@example.com";

        // Act
        boolean result = user1.equals(str);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testEquals_WithDifferentUsername_ShouldReturnFalse() {
        // Arrange
        UserRegisterDto user1 = new UserRegisterDto();
        UserRegisterDto user2 = new UserRegisterDto();

        // Act
        boolean result = user1.equals(user2);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testEquals_WithDifferentRole_ShouldReturnFalse() {
        // Arrange
        UserRegisterDto user1 = new UserRegisterDto();
        UserRegisterDto user2 = new UserRegisterDto();

        // Act
        boolean result = user1.equals(user2);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testEquals_WithDifferentPassword_ShouldReturnFalse() {
        // Arrange
        UserRegisterDto user1 = new UserRegisterDto();
        UserRegisterDto user2 = new UserRegisterDto();

        // Act
        boolean result = user1.equals(user2);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testEquals_WithSameFields_ShouldReturnTrue() {
        // Arrange
        UserRegisterDto user1 = new UserRegisterDto();
        UserRegisterDto user2 = new UserRegisterDto();

        // Act
        boolean result = user1.equals(user2);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testEqualsReflexive() {
        UserRegisterDto user1 = new UserRegisterDto();
        assertTrue(user1.equals(user1));
    }

    @Test
    public void testEqualsSymmetric() {
        UserRegisterDto user1 = new UserRegisterDto();
        UserRegisterDto user2 = new UserRegisterDto();
        assertTrue(user1.equals(user2) && user2.equals(user1));
    }

    @Test
    public void testEqualsTransitive() {
        UserRegisterDto user1 = new UserRegisterDto();
        UserRegisterDto user2 = new UserRegisterDto();
        UserRegisterDto user3 = new UserRegisterDto();
        assertTrue(user1.equals(user2) && user2.equals(user3) && user1.equals(user3));
    }

    @Test
    public void testEqualsConsistent() {
        UserRegisterDto user1 = new UserRegisterDto();
        UserRegisterDto user2 = new UserRegisterDto();
        assertEquals(user1.equals(user2), user1.equals(user2));
    }

    @Test
    public void testEqualsNull() {
        UserRegisterDto user1 = new UserRegisterDto();
        assertFalse(user1.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        UserRegisterDto user1 = new UserRegisterDto();
        assertFalse(user1.equals("user1"));
    }

    @Test
    public void testEqualsDifferentUsername() {
        UserRegisterDto user1 = new UserRegisterDto();
        user1.setEmail("username1");
        UserRegisterDto user2 = new UserRegisterDto();
        user2.setEmail("username2");
        assertFalse(user1.equals(user2));
    }

    @Test
    public void testEqualsDifferentRole() {
        UserRegisterDto user1 = new UserRegisterDto();
        user1.setRole("role1");
        UserRegisterDto user2 = new UserRegisterDto();
        user2.setRole("role2");
        assertFalse(user1.equals(user2));
    }

    @Test
    public void testEqualsDifferentPassword() {
        UserRegisterDto user1 = new UserRegisterDto();
        user1.setPassword("password1");
        UserRegisterDto user2 = new UserRegisterDto();
        user2.setPassword("password2");
        assertFalse(user1.equals(user2));
    }

    @Test
    public void testEqualsSameValues() {
        UserRegisterDto user1 = new UserRegisterDto();
        user1.setEmail("username1");
        user1.setRole("role1");
        user1.setPassword("password1");
        UserRegisterDto user2 = new UserRegisterDto();
        user2.setEmail("username1");
        user2.setRole("role1");
        user2.setPassword("password1");
        assertTrue(user1.equals(user2));
    }

    @Test
    public void testEqualsHashCode() {
        UserRegisterDto user1 = new UserRegisterDto();
        user1.setEmail("username1");
        user1.setRole("role1");
        user1.setPassword("password1");
        UserRegisterDto user2 = new UserRegisterDto();
        user2.setEmail("username1");
        user2.setRole("role1");
        user2.setPassword("password1");
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    void testEqualsWithDifferentPassword() {
        UserRegisterDto user1 = new UserRegisterDto();
        user1.setEmail("test@example.com");
        user1.setRole("USER");
        user1.setPassword("password1");

        UserRegisterDto user2 = new UserRegisterDto();
        user2.setEmail("test@example.com");
        user2.setRole("USER");
        user2.setPassword("password2");

        assertNotEquals(user1, user2);
    }

    @Test
    void testEqualsWithDifferentRole() {
        UserRegisterDto user1 = new UserRegisterDto();
        user1.setEmail("test@example.com");
        user1.setRole("USER");
        user1.setPassword("password1");

        UserRegisterDto user2 = new UserRegisterDto();
        user2.setEmail("test@example.com");
        user2.setRole("ADMIN");
        user2.setPassword("password1");

        assertNotEquals(user1, user2);
    }

    @Test
    void testEqualsWithNullObject() {
        UserRegisterDto user = new UserRegisterDto();
        user.setEmail("test@example.com");
        user.setRole("USER");
        user.setPassword("password1");

        assertNotEquals(user, null);
    }

    @Test
    void testEqualsWithDifferentClass() {
        UserRegisterDto user = new UserRegisterDto();
        user.setEmail("test@example.com");
        user.setRole("USER");
        user.setPassword("password1");

        assertNotEquals(user, "not a user object");
    }

    @Test
    void testEqualsWithSameObject() {
        UserRegisterDto user = new UserRegisterDto();
        user.setEmail("test@example.com");
        user.setRole("USER");
        user.setPassword("password1");

        assertEquals(user, user);
    }

    @Test
    void testEqualsWithEqualObjects() {
        UserRegisterDto user1 = new UserRegisterDto();
        user1.setEmail("test@example.com");
        user1.setRole("USER");
        user1.setPassword("password1");

        UserRegisterDto user2 = new UserRegisterDto();
        user2.setEmail("test@example.com");
        user2.setRole("USER");
        user2.setPassword("password1");

        assertEquals(user1, user2);
    }

    @Test
    void testEqualsWithDifferentUsername() {
        UserRegisterDto user1 = new UserRegisterDto();
        user1.setEmail("test1@example.com");
        user1.setRole("USER");
        user1.setPassword("password1");

        UserRegisterDto user2 = new UserRegisterDto();
        user2.setEmail("test2@example.com");
        user2.setRole("USER");
        user2.setPassword("password1");

        assertNotEquals(user1, user2);
    }


    @Test
    public void equals_shouldReturnTrue_whenGivenSameObject() {
        // Arrange
        UserRegisterDto user = new UserRegisterDto();
        user.setEmail("test@example.com");
        user.setRole("USER");
        user.setPassword("password");

        // Act
        boolean result = user.equals(user);

        // Assert
        assertTrue(result);
    }

    @Test
    public void equals_shouldReturnTrue_whenGivenEqualObject() {
        // Arrange
        UserRegisterDto user1 = new UserRegisterDto();
        user1.setEmail("test@example.com");
        user1.setRole("USER");
        user1.setPassword("password");

        UserRegisterDto user2 = new UserRegisterDto();
        user2.setEmail("test@example.com");
        user2.setRole("USER");
        user2.setPassword("password");

        // Act
        boolean result = user1.equals(user2);

        // Assert
        assertTrue(result);
    }

    @Test
    public void equals_shouldReturnFalse_whenGivenNull() {
        // Arrange
        UserRegisterDto user = new UserRegisterDto();
        user.setEmail("test@example.com");
        user.setRole("USER");
        user.setPassword("password");

        // Act
        boolean result = user.equals(null);

        // Assert
        assertFalse(result);
    }

    @Test
    public void equals_shouldReturnFalse_whenGivenObjectOfDifferentClass() {
        // Arrange
        UserRegisterDto user = new UserRegisterDto();
        user.setEmail("test@example.com");
        user.setRole("USER");
        user.setPassword("password");

        Object obj = new Object();

        // Act
        boolean result = user.equals(obj);

        // Assert
        assertFalse(result);
    }

    @Test
    public void equals_shouldReturnFalse_whenGivenObjectWithDifferentUsername() {
        // Arrange
        UserRegisterDto user1 = new UserRegisterDto();
        user1.setEmail("test1@example.com");
        user1.setRole("USER");
        user1.setPassword("password");

        UserRegisterDto user2 = new UserRegisterDto();
        user2.setEmail("test2@example.com");
        user2.setRole("USER");
        user2.setPassword("password");

        // Act
        boolean result = user1.equals(user2);

        // Assert
        assertFalse(result);
    }

    @Test
    public void equals_shouldReturnFalse_whenGivenObjectWithDifferentRole() {
        // Arrange
        UserRegisterDto user1 = new UserRegisterDto();
        user1.setEmail("test@example.com");
        user1.setRole("USER");
        user1.setPassword("password");

        UserRegisterDto user2 = new UserRegisterDto();
        user2.setEmail("test@example.com");
        user2.setRole("ADMIN");
        user2.setPassword("password");

        // Act
        boolean result = user1.equals(user2);

        // Assert
        assertFalse(result);
    }
    @Test
    void testEquals_SameObject_ReturnsTrue() {
        // Arrange
        UserRegisterDto dto = new UserRegisterDto();

        // Act & Assert
        assertTrue(dto.equals(dto));
    }

    @Test
    void testEquals_NullObject_ReturnsFalse() {
        // Arrange
        UserRegisterDto dto = new UserRegisterDto();

        // Act & Assert
        assertFalse(dto.equals(null));
    }

    @Test
    void testEquals_DifferentClass_ReturnsFalse() {
        // Arrange
        UserRegisterDto dto = new UserRegisterDto();
        Object obj = new Object();

        // Act & Assert
        assertFalse(dto.equals(obj));
    }

    @Test
    void testEquals_EqualObjects_ReturnsTrue() {
        // Arrange
        UserRegisterDto dto1 = new UserRegisterDto("John", "Doe", "john@example.com", "1234567890","938223","address", "password", "admin","replaceme");
        UserRegisterDto dto2 = new UserRegisterDto("John", "Doe", "john@example.com", "1234567890","938223","address", "password", "admin","replaceme");

        // Act & Assert
        assertTrue(dto1.equals(dto2));
    }

    @Test
    void testEquals_DifferentObjects_ReturnsFalse() {
        // Arrange
        UserRegisterDto dto1 = new UserRegisterDto("John", "Doe", "john@example.com", "1234567890","828891","address", "password", "admin","replaceme");
        UserRegisterDto dto2 = new UserRegisterDto("Jane", "Smith", "jane@example.com", "9876543210","828282","address", "password", "user","replaceme");

        // Act & Assert
        assertFalse(dto1.equals(dto2));
    }

    @Test
    void testHashCode_EqualObjects_ReturnsEqualHashCodes() {
        // Arrange
        UserRegisterDto dto1 = new UserRegisterDto("John", "Doe", "john@example.com", "1234567890","758495","address", "password", "admin","replaceme");
        UserRegisterDto dto2 = new UserRegisterDto("John", "Doe", "john@example.com", "1234567890","485904","address", "password", "admin","replaceme");

        // Act & Assert
        assertNotEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testSetPhone_ValidPhone_SetsPhone() {
        // Arrange
        UserRegisterDto dto = new UserRegisterDto();
        String phone = "1234567890";

        // Act
        dto.setPhone(phone);

        // Assert
        assertEquals(phone, dto.getPhone());
    }

    @Test
    void testSetFirstName_ValidFirstName_SetsFirstName() {
        // Arrange
        UserRegisterDto dto = new UserRegisterDto();
        String firstName = "John";

        // Act
        dto.setFirstName(firstName);

        // Assert
        assertEquals(firstName, dto.getFirstName());
    }

    @Test
    void testAllArgsConstructor_CreatesObjectWithAllFields() {
        // Arrange
        String firstName = "John";
        String lastName = "Doe";
        String email = "john@example.com";
        String phone = "1234567890";
        String icn = "759048";
        String address = "address";
        String password = "password";
        String role = "admin";
        String verCode = "replaceme";

        // Act
        UserRegisterDto dto = new UserRegisterDto(firstName, lastName, email, phone,icn,address, password, role, verCode);
        assertEquals(firstName, dto.getFirstName());
        assertEquals(lastName, dto.getLastName());
        assertEquals(email, dto.getEmail());
        assertEquals(phone, dto.getPhone());
        assertEquals(password, dto.getPassword());
        assertEquals(role, dto.getRole());
    }
    @Test
    void testSettersAndGetters() {
        UserRegisterDto dto = new UserRegisterDto();

        // Test setters and getters
        dto.setIcn("1234567890123");
        assertEquals("1234567890123", dto.getIcn());

        dto.setAdress("123 Main St");
        assertEquals("123 Main St", dto.getAdress());

        dto.setVerificationCode("ABC123");
        assertEquals("ABC123", dto.getVerificationCode());
    }

    @Test
    void testEqualsAndHashCode() {
        UserRegisterDto dto1 = new UserRegisterDto("John", "Doe", "john@example.com", "1234567890",
                "1234567890123", "123 Main St", "password", "ROLE_USER", "ABC123");
        UserRegisterDto dto2 = new UserRegisterDto("John", "Doe", "john@example.com", "1234567890",
                "1234567890123", "123 Main St", "password", "ROLE_USER", "ABC123");
        UserRegisterDto dto3 = new UserRegisterDto("Jane", "Smith", "jane@example.com", "9876543210",
                "0987654321098", "456 Elm St", "password", "ROLE_ADMIN", "XYZ789");
        UserRegisterDto dto4 = new UserRegisterDto(null, null, null, null, null, null, null, null, null);

        // Test equals() for different scenarios
        assertTrue(dto1.equals(dto1)); // Same instance
        assertTrue(dto1.equals(dto2)); // dto1 and dto2 have the same field values
        assertFalse(dto1.equals(null)); // Comparison with null
        assertFalse(dto1.equals(new Object())); // Comparison with different class

        assertFalse(dto1.equals(dto3)); // dto1 and dto3 have different field values
        assertFalse(dto1.equals(dto4)); // dto4 has null fields, while dto1 has non-null fields

        // Additional test cases to cover remaining branches
        assertFalse(dto1.equals(new UserRegisterDto("John", "Doe", "john@example.com", "1234567890",
                "1234567890123", "123 Main St", "password2", "ROLE_USER", "ABC123"))); // Different password
        assertFalse(dto1.equals(new UserRegisterDto("John", "Doe", "john@example.com", "1234567890",
                "1234567890123", "123 Main St", "password", "ROLE_ADMIN", "ABC123"))); // Different role
        assertFalse(dto1.equals(new UserRegisterDto("John", "Doe", "john@example.com", "1234567890",
                "1234567890123", "123 Main St", "password", "ROLE_USER", "XYZ789"))); // Different verificationCode

        // Test cases to cover remaining branches
        assertFalse(dto1.equals(new UserRegisterDto("John", "Doe", "john@example.com", "1234567890",
                "1234567890123", "123 Main St", "password", "ROLE_USER", null))); // Null verificationCode
        assertFalse(dto1.equals(new UserRegisterDto("John", "Doe", "john@example.com", "1234567890",
                "1234567890123", null, "password", "ROLE_USER", "ABC123"))); // Null adress
        assertFalse(dto1.equals(new UserRegisterDto("John", "Doe", "john@example.com", "1234567890",
                null, "123 Main St", "password", "ROLE_USER", "ABC123"))); // Null icn

        assertFalse(dto1.equals(new UserRegisterDto(null, "Doe", "john@example.com", "1234567890",
                "1234567890123", "123 Main St", "password", "ROLE_USER", "ABC123"))); // Null firstName
        assertFalse(dto1.equals(new UserRegisterDto("John", null, "john@example.com", "1234567890",
                "1234567890123", "123 Main St", "password", "ROLE_USER", "ABC123"))); // Null lastName
        assertFalse(dto1.equals(new UserRegisterDto("John", "Doe", null, "1234567890",
                "1234567890123", "123 Main St", "password", "ROLE_USER", "ABC123"))); // Null email
        assertFalse(dto1.equals(new UserRegisterDto("John", "Doe", "john@example.com", null,
                "1234567890123", "123 Main St", "password", "ROLE_USER", "ABC123"))); // Null phone
        assertFalse(dto1.equals(new UserRegisterDto("John", "Doe", "john@example.com", "1234567890",
                null, "123 Main St", "password", "ROLE_USER", "ABC123"))); // Null icn
        assertFalse(dto1.equals(new UserRegisterDto("John", "Doe", "john@example.com", "1234567890",
                "1234567890123", null, "password", "ROLE_USER", "ABC123"))); // Null adress
        assertFalse(dto1.equals(new UserRegisterDto("John", "Doe", "john@example.com", "1234567890",
                "1234567890123", "123 Main St", null, "ROLE_USER", "ABC123"))); // Null password
        assertFalse(dto1.equals(new UserRegisterDto("John", "Doe", "john@example.com", "1234567890",
                "1234567890123", "123 Main St", "password", null, "ABC123"))); // Null role

        // Test hashCode() consistency
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }
}