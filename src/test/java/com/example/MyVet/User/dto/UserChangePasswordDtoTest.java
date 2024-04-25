package com.example.MyVet.User.dto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class UserChangePasswordDtoTest {

    @Test
    void testConstructorAndGetters() {
        //given
        String expectedNewPassword = "newPassword123";
        UserChangePasswordDto userChangePasswordDto = new UserChangePasswordDto();
        userChangePasswordDto.setNewPassword(expectedNewPassword);

        //when
        String actualNewPassword = userChangePasswordDto.getNewPassword();

        //then
        assertNotNull(userChangePasswordDto);
        assertEquals(expectedNewPassword, actualNewPassword);
    }

    @Test
    void testEquals() {
        UserChangePasswordDto dto1 = new UserChangePasswordDto();
        dto1.setNewPassword("password1");

        UserChangePasswordDto dto2 = new UserChangePasswordDto();
        dto2.setNewPassword("password1");

        UserChangePasswordDto dto3 = new UserChangePasswordDto();
        dto3.setNewPassword("password2");

        UserChangePasswordDto dto4 = new UserChangePasswordDto();
        dto4.setNewPassword("password30");

        UserChangePasswordDto dto5 = new UserChangePasswordDto();
        dto5.setNewPassword("password2");

        assertTrue(dto1.equals(dto2));
        assertFalse(dto1.equals(dto3));
        assertFalse(dto1.equals(dto4));
        assertFalse(dto2.equals(dto4));
        assertTrue(dto5.equals(dto3));
    }

    @Test
    void testToString() {
        UserChangePasswordDto dto = new UserChangePasswordDto();
        dto.setNewPassword("password1");

        assertEquals("UserChangePasswordDto(newPassword=password1)", dto.toString());
    }

    @Test
    void testCanEqual() {
        UserChangePasswordDto dto1 = new UserChangePasswordDto();
        dto1.setNewPassword("password1");

        UserChangePasswordDto dto2 = new UserChangePasswordDto();
        dto2.setNewPassword("password1");

        UserChangePasswordDto dto3 = new UserChangePasswordDto();
        dto3.setNewPassword("password2");

        assertTrue(dto1.canEqual(dto2));
        assertTrue(dto2.canEqual(dto1));
        assertTrue(dto2.canEqual(dto3));
    }

    @Test
    void testHashCode() {
        UserChangePasswordDto dto1 = new UserChangePasswordDto();
        dto1.setNewPassword("password1");

        UserChangePasswordDto dto2 = new UserChangePasswordDto();
        dto2.setNewPassword("password1");

        UserChangePasswordDto dto3 = new UserChangePasswordDto();
        dto3.setNewPassword("password2");

        UserChangePasswordDto dto4  = new UserChangePasswordDto();
        dto4.setNewPassword("password2");

        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto4.hashCode());
        assertEquals(dto3.hashCode(), dto4.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
    }
    @Test
    void testHashCodeAndEquals() {
        UserChangePasswordDto dto1 = new UserChangePasswordDto();
        UserChangePasswordDto dto2 = new UserChangePasswordDto();
        UserChangePasswordDto dto3 = new UserChangePasswordDto();

        // Test branch 1: reflexivity
        assertEquals(dto1.hashCode(), dto1.hashCode());
        assertEquals(dto1, dto1);

        // Test branch 2: symmetry
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertEquals(dto2.hashCode(), dto1.hashCode());
        assertEquals(dto1, dto2);
        assertEquals(dto2, dto1);

        // Test branch 3: transitivity
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertEquals(dto2.hashCode(), dto3.hashCode());
        assertEquals(dto1.hashCode(), dto3.hashCode());
        assertEquals(dto1, dto2);
        assertEquals(dto2, dto3);
        assertEquals(dto1, dto3);

        // Test branch 4: non-nullity
        assertNotNull(dto1);

        // Test branch 5: consistency
        int hashCode = dto1.hashCode();
        assertEquals(hashCode, dto1.hashCode());
        assertEquals(hashCode, dto1.hashCode());

        // Test branch 6: non-equality to null
        assertNotEquals(dto1, null);

        // Test branch 7: non-equality to different class
        assertNotEquals(dto1, new Object());

        // Test branch 8: non-equality to different field
        UserChangePasswordDto dto4 = new UserChangePasswordDto();
        dto4.setNewPassword("different password");
        assertNotEquals(dto1, dto4);

        // Test branch 9: equality to self
        assertEquals(dto1, dto1);

        // Test branch 10: equality to same instance
        assertEquals(dto1, dto1);

        // Test branch 11: equality to equal instance
        assertEquals(dto1, dto2);

        // Test branch 12: equality to equal instance with set field
        dto2.setNewPassword("password");
        assertNotEquals(dto1, dto2);

        // Test branch 13: non-equality to unequal instance
        dto2.setNewPassword("different password");
        assertNotEquals(dto1, dto2);

        // Test branch 14: hash code consistency
        int hashCode2 = dto2.hashCode();
        assertEquals(hashCode2, dto2.hashCode());
        dto2.setNewPassword("password");
        assertNotEquals(hashCode2, dto2.hashCode());
    }

    @Test
    public void equalsAndHashCode_notEqual() {
        UserChangePasswordDto dto1 = new UserChangePasswordDto();
        dto1.setNewPassword("password1");

        UserChangePasswordDto dto2 = new UserChangePasswordDto();
        dto2.setNewPassword("password2");

        assertFalse(dto1.equals(dto2));
        assertNotEquals(dto1.hashCode(), dto2.hashCode());
    }
    @Test
    void testConstructor() {
        String newPassword = "newPassword123";
        UserChangePasswordDto dto = new UserChangePasswordDto(newPassword);

        // Test constructor
        assertEquals(newPassword, dto.getNewPassword());
    }

    @Test
    void testEquals1() {
        String newPassword = "newPassword123";
        UserChangePasswordDto dto1 = new UserChangePasswordDto(newPassword);
        UserChangePasswordDto dto2 = new UserChangePasswordDto(newPassword);
        UserChangePasswordDto dto3 = new UserChangePasswordDto("differentPassword");

        // Test equals() for different scenarios
        assertTrue(dto1.equals(dto1)); // Same instance
        assertTrue(dto1.equals(dto2)); // dto1 and dto2 have the same field values
        assertFalse(dto1.equals(null)); // Comparison with null
        assertFalse(dto1.equals(new Object())); // Comparison with different class
        assertFalse(dto1.equals(dto3)); // dto1 and dto3 have different field values
    }
}