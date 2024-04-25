package com.example.MyVet.User.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRegisterResponseDtoTest {

    @Test
    public void testConstructorAndGetters() {
        String email = "test@example.com";
        String password = "password123";
        String role="admin";
        String verCode="replaceme";
        String icn="859094";
        String address="address";
        UserRegisterResponseDto dto = new UserRegisterResponseDto(email, password, role, verCode, icn, List.of(address));
        assertEquals(email, dto.getEmail());
        assertEquals(password, dto.getPassword());
    }

    @Test
    public void testEqualsAndHashCode() {
        UserRegisterResponseDto dto1 = new UserRegisterResponseDto("test@example.com", "password123", "admin", "replaceme", "994039", List.of("address"));
        UserRegisterResponseDto dto2 = new UserRegisterResponseDto("test@example.com", "password123", "admin", "replaceme", "994039", List.of("address"));
        UserRegisterResponseDto dto3 = new UserRegisterResponseDto("test2@example.com", "password456", "admin", "replaceme", "009588",List.of( "address"));

        assertEquals(dto1, dto2);
        Assertions.assertNotEquals(dto1, dto3);
        Assertions.assertNotEquals(dto2, dto3);

        assertEquals(dto1.hashCode(), dto2.hashCode());
        Assertions.assertNotEquals(dto1.hashCode(), dto3.hashCode());
        Assertions.assertNotEquals(dto2.hashCode(), dto3.hashCode());
    }
    @Test
    public void setEmail_shouldSetEmail() {
        // given
        UserRegisterResponseDto user = new UserRegisterResponseDto("oldEmail", "password", "admin", "replaceme", "884993", List.of("address"));

        // when
        user.setEmail("newEmail");

        // then
        assertEquals("newEmail", user.getEmail());
    }

    @Test
    public void setPassword_shouldSetPassword() {
        // given
        UserRegisterResponseDto user = new UserRegisterResponseDto("email", "oldPassword","admin","replaceme", "008588", List.of("Adress"));

        // when
        user.setPassword("newPassword");

        // then
        assertEquals("newPassword", user.getPassword());
    }
}