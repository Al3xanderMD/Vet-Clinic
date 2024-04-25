package com.example.MyVet.User.dto;

import lombok.Data;
import org.h2.engine.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
public class UserJwtResponseDtoTest {

    @Test
    void testConstructorAndGetters() {
        // given
        String accessToken = "example-access-token";
        UUID id = UUID.randomUUID();
        String username = "email";
        List<String> roles = Collections.singletonList("ROLE_USER");

        // when
        UserJwtResponseDto userJwtResponseDto = new UserJwtResponseDto(accessToken, id.toString(), username,"ROLE_USER");

        // then
        assertEquals(accessToken, userJwtResponseDto.getToken());
        assertEquals("Bearer", userJwtResponseDto.getType());
        assertEquals(id.toString(), userJwtResponseDto.getId().toString());
        assertEquals(username, userJwtResponseDto.getEmail());
//        assertEquals(roles, userJwtResponseDto.getRoles());
    }
    @Test
    void setToken() {
        UserJwtResponseDto response = new UserJwtResponseDto("token", UUID.randomUUID().toString(), "email","ROLE_USER");
        response.setToken("newToken");
        assertEquals("newToken", response.getToken());
    }

    @Test
    void setType() {
        UserJwtResponseDto response = new UserJwtResponseDto("token", UUID.randomUUID().toString(), "email","ROLE_USER");
        response.setType("newType");
        assertEquals("newType", response.getType());
    }

    @Test
    void setId() {
        UserJwtResponseDto response = new UserJwtResponseDto("token", UUID.randomUUID().toString(), "email","ROLE_USER");
        UUID newId = UUID.randomUUID();
        response.setId(newId.toString());
        assertEquals(newId.toString(), response.getId().toString());
    }

    @Test
    void setUsername() {
        UserJwtResponseDto response = new UserJwtResponseDto("token", UUID.randomUUID().toString(), "email","ROLE_USER");
        response.setEmail("newUsername");
        assertEquals("newUsername", response.getEmail());
    }

//    @Test
//    void setRoles() {
//        UserJwtResponseDto response = new UserJwtResponseDto("token", UUID.randomUUID(), "username", List.of("role1", "role2"));
//        List<String> newRoles = List.of("role3", "role4");
//        response.setRole();
//        assertEquals(newRoles, response.getRoles());
//    }
    @Test
    void testAnnotations(){
        assertFalse(UserJwtResponseDto.class.isAnnotationPresent(Data.class));
    }

    @Test
    void testEquals() {

        UserJwtResponseDto dto1 = new UserJwtResponseDto("token1", UUID.randomUUID().toString(), "user1","ROLE_USER");
        UserJwtResponseDto dto2 = new UserJwtResponseDto("token1", UUID.randomUUID().toString(), "user1","ROLE_USER");

        UserJwtResponseDto dto3 = new UserJwtResponseDto("token2", UUID.randomUUID().toString(), "user1","ROLE_USER");
        UserJwtResponseDto dto4 = new UserJwtResponseDto("token2", UUID.randomUUID().toString(), "user1","ROLE_USER");

        UserJwtResponseDto dto5 = new UserJwtResponseDto("token3", UUID.randomUUID().toString(), "user1","ROLE_USER");
        UserJwtResponseDto dto6 = new UserJwtResponseDto("token3", UUID.randomUUID().toString(), "user2","ROLE_USER");

        UserJwtResponseDto dto7 = new UserJwtResponseDto("token4", UUID.randomUUID().toString(), "user1","ROLE_USER");
        UserJwtResponseDto dto8 = new UserJwtResponseDto("token4", UUID.randomUUID().toString(), "user1","ROLE_USER");

        assertFalse(dto1.equals(dto2));

        assertFalse(dto3.equals(dto4));

        assertFalse(dto5.equals(dto6));

        assertFalse(dto7.equals(dto8));

        assertFalse(dto1.equals("not a DTO"));

        assertFalse(dto1.equals(null));
    }
    @Test
    void testEquals1() {
        UUID uuid1 = UUID.fromString("e1d51ab5-48af-44e2-bd8b-7c12a9f9a7c1");
        UUID uuid2 = UUID.fromString("b36fc0f1-8d01-4f3e-9f1d-0d8bcb5376c2");

        UserJwtResponseDto dto1 = new UserJwtResponseDto("token1", uuid1.toString(), "user1","ROLE_USER");
        UserJwtResponseDto dto2 = new UserJwtResponseDto("token1", uuid1.toString(), "user1","ROLE_USER");
        UserJwtResponseDto dto3 = new UserJwtResponseDto("token2", uuid1.toString(), "user1","ROLE_USER");
        UserJwtResponseDto dto4 = new UserJwtResponseDto("token2", uuid1.toString(), "user1","ROLE_USER");
        UserJwtResponseDto dto5 = new UserJwtResponseDto("token3", uuid1.toString(), "user1","ROLE_USER");
        UserJwtResponseDto dto6 = new UserJwtResponseDto("token3", uuid2.toString(), "user2","ROLE_USER");
        UserJwtResponseDto dto7 = new UserJwtResponseDto("token4", uuid1.toString(), "user1","ROLE_USER");
        UserJwtResponseDto dto8 = new UserJwtResponseDto("token4", uuid1.toString(), "user1","ROLE_USER");

        assertTrue(dto1.equals(dto2));
        assertTrue(dto1.hashCode() == dto2.hashCode());

        assertTrue(dto3.equals(dto4));
        assertTrue(dto3.hashCode() == dto4.hashCode());

        assertFalse(dto5.equals(dto6));
        assertFalse(dto5.hashCode() == dto6.hashCode());

        assertTrue(dto7.equals(dto8));
        assertTrue(dto7.hashCode() == dto8.hashCode());

        assertFalse(dto1.equals("not a DTO"));
        assertFalse(dto1.equals(null));
    }


    @Test
    void testToString() {
        UserJwtResponseDto dto = new UserJwtResponseDto("token1", UUID.randomUUID().toString(), "user1","ROLE_USER");

        String expectedString = "UserJwtResponseDto(token=token1, type=Bearer, id=" + dto.getId().toString() +
                ", email=user1, role=ROLE_USER)";
        assertEquals(expectedString, dto.toString());
    }

    @Test
    void testCanEqual() {
        UserJwtResponseDto dto1 = new UserJwtResponseDto("token1", UUID.randomUUID().toString(), "user1","ROLE_USER");
        UserJwtResponseDto dto2 = new UserJwtResponseDto("token1", UUID.randomUUID().toString(), "user1","ROLE_USER");
        UserJwtResponseDto dto3 = new UserJwtResponseDto("token2", UUID.randomUUID().toString(), "user1","ROLE_USER");

        assertTrue(dto1.canEqual(dto2));
        assertTrue(dto2.canEqual(dto1));
        assertTrue(dto1.canEqual(dto3));
    }

    @Test
    void testHashCode() {
        String uuidString1 = "2f5b4bc4-8a4b-4aa5-9f4e-3b3a25d263a9";
        String uuidString2 = "b6c67b88-c836-4dbf-b561-fd7a934ed843";
        UUID uuid1 = UUID.fromString(uuidString1);
        UUID uuid2 = UUID.fromString(uuidString2);

        UserJwtResponseDto dto1 = new UserJwtResponseDto("token1", uuid1.toString(), "user1","ROLE_USER");
        UserJwtResponseDto dto2 = new UserJwtResponseDto("token1", uuid1.toString(), "user1","ROLE_USER");
        UserJwtResponseDto dto3 = new UserJwtResponseDto("token2", uuid2.toString(), "user1","ROLE_USER");

        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());

        UserJwtResponseDto dto4 = new UserJwtResponseDto("token3", uuid2.toString(), "user3","ROLE_USER");

        // Test that equal objects have the same hashCode()
        assertEquals(dto1.hashCode(), dto2.hashCode());

        // Test that changing the token changes the hashCode()
        dto2.setToken("newToken");
        assertNotEquals(dto1.hashCode(), dto2.hashCode());

        // Test that changing the ID changes the hashCode()
        dto2.setId(uuid2.toString());
        assertNotEquals(dto1.hashCode(), dto2.hashCode());

        // Test that changing the username changes the hashCode()
        dto2.setEmail("newUser");
        assertNotEquals(dto1.hashCode(), dto2.hashCode());

        // Test that changing the roles changes the hashCode()
//        dto2.setRoles(Arrays.asList("ROLE_ADMIN"));
        assertNotEquals(dto1.hashCode(), dto2.hashCode());

        // Test that objects with different values have different hashCode()
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
        assertNotEquals(dto2.hashCode(), dto3.hashCode());
        assertNotEquals(dto1.hashCode(), dto4.hashCode());
        assertNotEquals(dto2.hashCode(), dto4.hashCode());
    }
    @Test
    public void testGetToken() {
        String token = "test-token";
        UserJwtResponseDto responseDto = new UserJwtResponseDto(token, UUID.randomUUID().toString(), "test-user","ROLE_USER");
        assertEquals(token, responseDto.getToken());
    }

    @Test
    public void testGetType() {
        UserJwtResponseDto responseDto = new UserJwtResponseDto("test-token", UUID.randomUUID().toString(), "test-user","ROLE_USER");
        assertEquals("Bearer", responseDto.getType());
    }

    @Test
    public void testGetId() {
        UUID id = UUID.randomUUID();
        UserJwtResponseDto responseDto = new UserJwtResponseDto("test-token", id.toString(), "test-user","ROLE_USER");
        assertEquals(id.toString(), responseDto.getId());
    }

    @Test
    public void testGetUsername() {
        String username = "test-user";
        UserJwtResponseDto responseDto = new UserJwtResponseDto("test-token", UUID.randomUUID().toString(), username,"ROLE_USER");
        assertEquals(username, responseDto.getEmail());
    }

//    @Test
//    public void testGetRoles() {
//        List<String> roles = List.of("ROLE_USER", "ROLE_ADMIN");
//        UserJwtResponseDto responseDto = new UserJwtResponseDto("test-token", UUID.randomUUID().toString(), "test-user");
//        assertEquals(roles, responseDto.getRoles());
//    }

    @Test
    void testEqualsAndHashCode() {
        UserJwtResponseDto dto1 = new UserJwtResponseDto("token1", UUID.randomUUID().toString(), "user1","ROLE_USER");
        UserJwtResponseDto dto2 = new UserJwtResponseDto("token1", UUID.randomUUID().toString(), "user1","ROLE_USER");
        UserJwtResponseDto dto3 = new UserJwtResponseDto("token2", UUID.randomUUID().toString(), "user2","ROLE_USER");

        // test reflexive property
        assertEquals(dto1, dto1);
        // test symmetric property
        assertNotEquals(dto1, dto2);
        assertNotEquals(dto2, dto1);
        // test transitive property
        assertNotEquals(dto1, dto2);
        assertNotEquals(dto2, dto3);
        assertNotEquals(dto1, dto3);
        // test non-equality
        assertNotEquals(dto1, null);
        assertNotEquals(dto1, "not a DTO");
        assertNotEquals(dto1, new Object());
        assertNotEquals(dto1, dto3);
        // test hash code consistency
        assertNotEquals(dto1.hashCode(), dto2.hashCode());
    }
    @Test
    void testSetRole() {
        UserRegisterResponseDto dto = new UserRegisterResponseDto("email","password","role","verify","919191", Collections.singletonList("address"));
        dto.setRole("ROLE_USER");
        assertEquals("ROLE_USER", dto.getRole());
    }

    @Test
    void testSetVerificationCode() {
        UserRegisterResponseDto dto = new UserRegisterResponseDto("email","password","role","verify","919191", Collections.singletonList("address"));
        dto.setVerificationCode("123456");
        assertEquals("123456", dto.getVerificationCode());
    }

    @Test
    void testSetIcn() {
        UserRegisterResponseDto dto = new UserRegisterResponseDto("email","password","role","verify","919191", Collections.singletonList("address"));
        dto.setIcn("1234567890");
        assertEquals("1234567890", dto.getIcn());
    }

    @Test
    void testSetAdress() {
        UserRegisterResponseDto dto = new UserRegisterResponseDto("email","password","role","verify","919191", Collections.singletonList("address"));
        dto.setAdresses(List.of("123 Main St"));
        assertEquals(List.of("123 Main St"), dto.getAdresses());
    }

    @Test
    void testToString2() {
        UserRegisterResponseDto dto = new UserRegisterResponseDto("john@example.com", "password", "ROLE_USER",
                "123456", "1234567890", List.of("123 Main St"));

        String expected = "UserRegisterResponseDto(email=john@example.com, password=password, role=ROLE_USER, " +
                "verificationCode=123456, icn=1234567890, adresses=[123 Main St])";
        assertEquals(expected, dto.toString());
    }

    @Test
    void testEqualsAndHashCode2() {
        UserRegisterResponseDto dto1 = new UserRegisterResponseDto("john@example.com", "password", "ROLE_USER",
                "123456", "1234567890", List.of("123 Main St"));
        UserRegisterResponseDto dto2 = new UserRegisterResponseDto("john@example.com", "password", "ROLE_USER",
                "123456", "1234567890", List.of("123 Main St"));
        UserRegisterResponseDto dto3 = new UserRegisterResponseDto("jane@example.com", "password", "ROLE_ADMIN",
                "654321", "0987654321", List.of("456 Main St"));

        // Test equals()
        assertTrue(dto1.equals(dto2)); // dto1 and dto2 have the same field values
        assertFalse(dto1.equals(dto3)); // dto1 and dto3 have different email and role values

        // Test hashCode()
        assertEquals(dto1.hashCode(), dto2.hashCode()); // dto1 and dto2 have the same field values
        assertNotEquals(dto1.hashCode(), dto3.hashCode()); // dto1 and dto3 have different email and role values
    }
    @Test
    void testEqualsAndHashCode3() {
        UserRegisterResponseDto dto1 = new UserRegisterResponseDto("john@example.com", "password", "ROLE_USER",
                "123456", "1234567890", List.of("123 Main St"));
        UserRegisterResponseDto dto2 = new UserRegisterResponseDto("john@example.com", "password", "ROLE_USER",
                "123456", "1234567890", List.of("123 Main St"));
        UserRegisterResponseDto dto3 = new UserRegisterResponseDto("jane@example.com", "password", "ROLE_ADMIN",
                "654321", "0987654321", List.of("456 Main St"));
        UserRegisterResponseDto dto4 = new UserRegisterResponseDto(null, null, null, null, null, null);

        // Test equals() for different scenarios
        assertTrue(dto1.equals(dto1)); // Same instance
        assertTrue(dto1.equals(dto2)); // dto1 and dto2 have the same field values
        assertFalse(dto1.equals(null)); // Comparison with null
        assertFalse(dto1.equals(new Object())); // Comparison with different class

        assertFalse(dto1.equals(dto3)); // dto1 and dto3 have different email and role values
        assertFalse(dto1.equals(dto4)); // dto4 has null fields, while dto1 has non-null fields

        // Test hashCode() for different scenarios
        assertEquals(dto1.hashCode(), dto1.hashCode()); // Same instance
        assertEquals(dto1.hashCode(), dto2.hashCode()); // dto1 and dto2 have the same field values
        assertNotEquals(dto1.hashCode(), dto3.hashCode()); // dto1 and dto3 have different email and role values
        assertNotEquals(dto1.hashCode(), dto4.hashCode()); // dto4 has null fields, while dto1 has non-null fields
    }
    @Test
    void testEqualsAndHashCode4() {
        UserRegisterResponseDto dto1 = new UserRegisterResponseDto("john@example.com", "password", "ROLE_USER",
                "123456", "1234567890", List.of("123 Main St"));
        UserRegisterResponseDto dto2 = new UserRegisterResponseDto("john@example.com", "password", "ROLE_USER",
                "123456", "1234567890", List.of("123 Main St"));
        UserRegisterResponseDto dto3 = new UserRegisterResponseDto("jane@example.com", "password", "ROLE_ADMIN",
                "654321", "0987654321", List.of("456 Main St"));
        UserRegisterResponseDto dto4 = new UserRegisterResponseDto(null, null, null, null, null, null);

        // Test equals() for different scenarios
        assertTrue(dto1.equals(dto1)); // Same instance
        assertTrue(dto1.equals(dto2)); // dto1 and dto2 have the same field values
        assertFalse(dto1.equals(null)); // Comparison with null
        assertFalse(dto1.equals(new Object())); // Comparison with different class

        assertFalse(dto1.equals(dto3)); // dto1 and dto3 have different email and role values
        assertFalse(dto1.equals(dto4)); // dto4 has null fields, while dto1 has non-null fields

        // Additional test cases to cover remaining branches
        assertFalse(dto1.equals(new UserRegisterResponseDto("john@example.com", "password2", "ROLE_USER",
                "123456", "1234567890", Collections.singletonList("123 Main St")))); // Different password
        assertFalse(dto1.equals(new UserRegisterResponseDto("john@example.com", "password", "ROLE_ADMIN",
                "123456", "1234567890", Collections.singletonList("123 Main St")))); // Different role
        assertFalse(dto1.equals(new UserRegisterResponseDto("john@example.com", "password", "ROLE_USER",
                "654321", "1234567890", Collections.singletonList("123 Main St")))); // Different verificationCode
        assertFalse(dto1.equals(new UserRegisterResponseDto("john@example.com", "password", "ROLE_USER",
                "123456", "0987654321", Collections.singletonList("123 Main St")))); // Different icn
        assertFalse(dto1.equals(new UserRegisterResponseDto("john@example.com", "password", "ROLE_USER",
                "123456", "1234567890", Collections.singletonList("456 Main St")))); // Different address
    }
    @Test
    void testEqualsAndHashCode5() {
        UserRegisterResponseDto dto1 = new UserRegisterResponseDto("john@example.com", "password", "ROLE_USER",
                "123456", "1234567890", Collections.singletonList("123 Main St"));
        UserRegisterResponseDto dto2 = new UserRegisterResponseDto("john@example.com", "password", "ROLE_USER",
                "123456", "1234567890", Collections.singletonList("123 Main St"));
        UserRegisterResponseDto dto3 = new UserRegisterResponseDto("jane@example.com", "password", "ROLE_ADMIN",
                "654321", "0987654321", Collections.singletonList("456 Main St"));
        UserRegisterResponseDto dto4 = new UserRegisterResponseDto(null, null, null, null, null, null);

        // Test equals() for different scenarios
        assertTrue(dto1.equals(dto1)); // Same instance
        assertTrue(dto1.equals(dto2)); // dto1 and dto2 have the same field values
        assertFalse(dto1.equals(null)); // Comparison with null
        assertFalse(dto1.equals(new Object())); // Comparison with different class

        assertFalse(dto1.equals(dto3)); // dto1 and dto3 have different email and role values
        assertFalse(dto1.equals(dto4)); // dto4 has null fields, while dto1 has non-null fields

        // Additional test cases to cover remaining branches
        assertFalse(dto1.equals(new UserRegisterResponseDto("john@example.com", "password2", "ROLE_USER",
                "123456", "1234567890", Collections.singletonList("123 Main St")))); // Different password
        assertFalse(dto1.equals(new UserRegisterResponseDto("john@example.com", "password", "ROLE_ADMIN",
                "123456", "1234567890", Collections.singletonList("123 Main St")))); // Different role
        assertFalse(dto1.equals(new UserRegisterResponseDto("john@example.com", "password", "ROLE_USER",
                "654321", "1234567890", Collections.singletonList("123 Main St")))); // Different verificationCode
        assertFalse(dto1.equals(new UserRegisterResponseDto("john@example.com", "password", "ROLE_USER",
                "123456", "0987654321", Collections.singletonList("123 Main St")))); // Different icn
        assertFalse(dto1.equals(new UserRegisterResponseDto("john@example.com", "password", "ROLE_USER",
                "123456", "1234567890", Collections.singletonList("456 Main St")))); // Different address

        // Test cases to cover remaining branches
        assertFalse(dto1.equals(new UserRegisterResponseDto(null, "password", "ROLE_USER",
                "123456", "1234567890", Collections.singletonList("123 Main St")))); // Null email
        assertFalse(dto1.equals(new UserRegisterResponseDto("john@example.com", null, "ROLE_USER",
                "123456", "1234567890", Collections.singletonList("123 Main St")))); // Null password
        assertFalse(dto1.equals(new UserRegisterResponseDto("john@example.com", "password", null,
                "123456", "1234567890", Collections.singletonList("123 Main St")))); // Null role
        assertFalse(dto1.equals(new UserRegisterResponseDto("john@example.com", "password", "ROLE_USER",
                null, "1234567890", Collections.singletonList("123 Main St")))); // Null verificationCode
        assertFalse(dto1.equals(new UserRegisterResponseDto("john@example.com", "password", "ROLE_USER",
                "123456", null, Collections.singletonList("123 Main St")))); // Null icn
        assertFalse(dto1.equals(new UserRegisterResponseDto("john@example.com", "password", "ROLE_USER",
                "123456", "1234567890", null))); // Null address
        assertFalse(dto1.equals(new UserRegisterResponseDto(null, null, null,
                null, null, null))); // All null fields
    }
    @Test
    void testSetRole2() {
        UserJwtResponseDto dto = new UserJwtResponseDto("token", "123", "john@example.com", "ROLE_USER");

        // Test setRole()
        dto.setRole("ROLE_ADMIN");
        assertEquals("ROLE_ADMIN", dto.getRole());
    }

    @Test
    void testEqualsAndHashCode6() {
        UserJwtResponseDto dto1 = new UserJwtResponseDto("token", "123", "john@example.com", "ROLE_USER");
        UserJwtResponseDto dto2 = new UserJwtResponseDto("token", "123", "john@example.com", "ROLE_USER");
        UserJwtResponseDto dto3 = new UserJwtResponseDto("token", "456", "jane@example.com", "ROLE_ADMIN");
        UserJwtResponseDto dto4 = new UserJwtResponseDto(null, null, null, null);

        // Test equals() for different scenarios
        assertTrue(dto1.equals(dto1)); // Same instance
        assertTrue(dto1.equals(dto2)); // dto1 and dto2 have the same field values
        assertFalse(dto1.equals(null)); // Comparison with null
        assertFalse(dto1.equals(new Object())); // Comparison with different class

        assertFalse(dto1.equals(dto3)); // dto1 and dto3 have different field values
        assertFalse(dto1.equals(dto4)); // dto4 has null fields, while dto1 has non-null fields

        // Additional test cases to cover remaining branches
        assertFalse(dto1.equals(new UserJwtResponseDto("token", "123", "john@example.com", "ROLE_ADMIN"))); // Different role
        assertFalse(dto1.equals(new UserJwtResponseDto(null, "123", "john@example.com", "ROLE_USER"))); // Null token
        assertFalse(dto1.equals(new UserJwtResponseDto("token", null, "john@example.com", "ROLE_USER"))); // Null id
        assertFalse(dto1.equals(new UserJwtResponseDto("token", "123", null, "ROLE_USER"))); // Null email
        assertFalse(dto1.equals(new UserJwtResponseDto("token", "123", "john@example.com", null))); // Null role

        // Test hashCode() consistency
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }
}