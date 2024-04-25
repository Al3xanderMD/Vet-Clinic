package com.example.MyVet.User.users;

import static com.example.MyVet.User.users.role.Role.ERole.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.MyVet.User.users.role.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;

class UserTest {
    private User user1;
    private User user2;
    private User user;
    private String email = "john_doe";
    private String password = "my_secret_password";
    private Role role = new Role(ROLE_ADMIN);
    private Set<Role> roles = new HashSet<>();

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail(email);
        user.setPassword(password);
        roles.add(role);
        //user.setRoles(roles);
    }

    @Test
    void testGetId() {
        assertEquals(user.getId(), user.getId());
    }

    @Test
    void testGetUsername() {
        assertEquals(user.getEmail(), email);
    }

    @Test
    void testGetPassword() {
        assertEquals(user.getPassword(), password);
    }

//    @Test
//    void testGetRoles() {
//        assertEquals(user.getRoles(), roles);
//    }

    @Test
    void testSetId() {
        String newId = UUID.randomUUID().toString();
        user.setId(String.valueOf(newId));
        assertEquals(user.getId(), newId);
    }

    @Test
    void testSetUsername() {
        String newUsername = "jane_doe";
        user.setEmail(newUsername);
        assertEquals(user.getUsername(), newUsername);
    }

    @Test
    void testSetPassword() {
        String newPassword = "my_new_password";
        user.setPassword(newPassword);
        assertEquals(user.getPassword(), newPassword);
    }

//    @Test
//    void testSetRoles() {
//        Set<Role> newRoles = new HashSet<>();
//        Role newRole = new Role(ROLE_ADMIN);
//        newRoles.add(newRole);
//        user.setRoles(newRoles);
//        assertEquals(user.getRoles(), newRoles);
//    }

    @Test
    void testConstructorWithArguments() {
        User newUser = new User("firstname","lastname",email,"28282","777889","address", password);
        assertEquals(newUser.getUsername(), email);
        assertEquals(newUser.getPassword(), password);
    }

//    @Test
//    void testGetAuthorities() {
//        List<GrantedAuthority> authorities = new ArrayList<>();
////        authorities.add(new SimpleGrantedAuthority(role.getName().name()));
//        assertEquals(user.getAuthorities(), authorities);
//    }

    @Test
    void testIsAccountNonExpired() {
        assertTrue(user.isAccountNonExpired());
    }

    @Test
    void testIsAccountNonLocked() {
        assertTrue(user.isAccountNonLocked());
    }

    @Test
    void testIsCredentialsNonExpired() {
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    void testIsEnabled() {
        assertFalse(user.isEnabled());
    }
    @Test
    void testToString() {
        User user = new User("john","doe","email","829292","889002","address", "password");
        user.setId(String.valueOf(UUID.fromString("11223344-5566-7788-99aa-bbccddeeff00")));
//        Role role = new Role(ROLE_ADMIN);
//        Set<Role> roles = new HashSet<>();
//        roles.add(role);
//        user.setRoles(roles);

        String expected = "User(id=11223344-5566-7788-99aa-bbccddeeff00, firstName=john, lastName=doe, email=email, phone=829292, password=password, icn=889002, adresses=[address], role=null, verificationCode=null, enabled=false)";
        String actual = user.toString();
        assertEquals(expected, actual);
    }
    @Test
    void equalsAndHashCodeTest() {
        // Create user objects for testing
        User user1 = new User("user1", "last1", "email1", "929292","774889","address", "password1");
        user1.setId(String.valueOf(UUID.fromString("e1d51ab5-48af-44e2-bd8b-7c12a9f9a7c1")));
        // user1.getRoles().add(new Role(ROLE_PATIENT));

        User user2 = new User("user2", "last2", "email2", "929293","723889","address", "password2");
        user2.setId(String.valueOf(UUID.fromString("e1d51ab5-48af-44e2-bd8b-7c12a9f9a7c1")));
        // user2.getRoles().add(new Role(ROLE_PATIENT));

        User user3 = new User("user1", "last1", "email1", "929292","774889","address", "password1");
        user3.setId(String.valueOf(UUID.fromString("e1d51ab5-48af-44e2-bd8b-7c12a9f9a7c1")));
        //  user3.getRoles().add(new Role(ROLE_ADMIN));

        User user4 = new User("user4", "last4", "email4", "929294","771189","address", "password4");
        user4.setId(String.valueOf(UUID.fromString("5ec5b0ad-b6ce-4b5c-bd5c-bda196ed5f51")));

        // Test object equality
        assertTrue(user1.equals(user1));
        assertTrue(user2.equals(user2));
        assertTrue(user3.equals(user3));
        assertTrue(user4.equals(user4));
        assertFalse(user1.equals(null));

        assertFalse(user1.equals(user2));
        assertFalse(user2.equals(user1));
        assertTrue(user1.equals(user3));
        assertFalse(user2.equals(user3));
        assertFalse(user1.equals(user4));
        assertFalse(user2.equals(user4));
        assertFalse(user3.equals(user4));

        // Test hashCode method
        assertEquals(user1.hashCode(), user3.hashCode());
        assertNotEquals(user1.hashCode(), user2.hashCode());
        assertNotEquals(user1.hashCode(), user4.hashCode());
        assertNotEquals(user2.hashCode(), user4.hashCode());

        // Test setRoles method
        //User user5 = new User("user5", "last5", "email5", "929295", "password5");
        //Role role1 = new Role(ROLE_ADMIN);
        //Role role2 = new Role(ROLE_NURSE);
        // Role role3 = new Role(ROLE_PATIENT);

        // Add roles to user5
        //user5.setRoles(new HashSet<>(Arrays.asList(role1, role2, role3)));

        // Verify that roles were added
//        assertTrue(user5.getRoles().contains(role1));
//        assertTrue(user5.getRoles().contains(role2));
//        assertTrue(user5.getRoles().contains(role3));
//        assertEquals(3, user5.getRoles().size());
//
//        // Remove role2 from user5
//        user5.getRoles().remove(role2);

        // Verify that role2 was removed
//        assertFalse(user5.getRoles().contains(role2));
//        assertTrue(user5.getRoles().contains(role1));
//        assertTrue(user5.getRoles().contains(role3));
//        assertEquals(2, user5.getRoles().size());
//
//        // Clear roles from user5
//        user5.setRoles(new HashSet<>());

        // Verify that roles were cleared
//        assertTrue(user5.getRoles().isEmpty());}}
    }

    @Test
    void testNotEquals() {
        User user1 = new User("john","doe","email","829292","774889","address", "password");
        user1.setId(String.valueOf(UUID.fromString("11223344-5566-7788-99aa-bbccddeeff00")));
//        Role role1 = new Role(ROLE_ADMIN);
//        Set<Role> roles1 = new HashSet<>();
//        roles1.add(role1);
//        user1.setRoles(roles1);

        User user2 = new User("jane","doe","email","829292","774899","address", "password");
        user2.setId(String.valueOf(UUID.fromString("11223344-5566-7788-99aa-bbccddeeff00")));
//        Role role2 = new Role(ROLE_ADMIN);
//        Set<Role> roles2 = new HashSet<>();
//        roles2.add(role2);
//        user2.setRoles(roles2);

        assertFalse(user1.equals(user2) || user2.equals(user1));
        assertNotEquals(user1.hashCode(), user2.hashCode());
    }
@Test
public void testHashCode() {
    UUID id = UUID.randomUUID();
    User user1 = new User("testUser","testname","testemail","8287278","774889","address","testPassword");
    User user2 = new User("testUser","testname","testemail","8287278","774889","address","testPassword");
    User user3 = new User("anotherUser","anotherName","anotherEmail","7282828","704889","address", "anotherPassword");

    // Check that the hashCode() method is consistent
    assertEquals(user1.hashCode(), user1.hashCode());
    assertEquals(user2.hashCode(), user2.hashCode());
    assertEquals(user3.hashCode(), user3.hashCode());

    // Check that equal objects have the same hashCode()
    assertEquals(user1.hashCode(), user2.hashCode());

    // Check that unequal objects have different hashCode()
    assertNotEquals(user1.hashCode(), user3.hashCode());

    // Check that changing the ID changes the hashCode()
    user1.setId(String.valueOf(id));
    assertNotEquals(user1.hashCode(), user2.hashCode());

    // Check that changing the username changes the hashCode()
    user2.setEmail("newEmail");
    assertNotEquals(user1.hashCode(), user2.hashCode());

    // Check that changing the password changes the hashCode()
    user3.setPassword("newPassword");
    assertNotEquals(user2.hashCode(), user3.hashCode());
}

    @Test
    public void testCanEqual() {
        UUID id = UUID.randomUUID();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        assertTrue(user1.canEqual(user2));
        assertTrue(user2.canEqual(user1));
        assertTrue(user1.canEqual(user3));
        assertTrue(user3.canEqual(user1));
        assertTrue(user2.canEqual(user3));
        assertTrue(user3.canEqual(user2));
    }
    @Test
    public void testSettersAndGetAuthorities() {
        // Arrange
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        String phone = "1234567890";
        String password = "password123";
        Role role = new Role(ROLE_USER);

        // Act
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setRole(role);

        // Assert
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(email, user.getEmail());
        assertEquals(phone, user.getPhone());
        assertEquals(password, user.getPassword());
        assertEquals(role, user.getRole());

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        assertNotNull(authorities);
        assertEquals(1, authorities.size());
        assertEquals(new SimpleGrantedAuthority("ROLE_USER"), authorities.iterator().next());
    }
    @Test
    public void testSetEnabled() {
        User user = new User();
        user.setEnabled(true);
        Assertions.assertTrue(user.isEnabled());
        user.setEnabled(false);
        Assertions.assertFalse(user.isEnabled());
    }

    @Test
    void testSetVerification() {
        User user = new User();
        String verificationCode = "1234";
        user.setVerificationCode(verificationCode);
        assertEquals(verificationCode, user.getVerificationCode());
    }
    @Test
    public void testSetIcn() {
        String icn = "1234567890123";
        user.setIcn(icn);

        assertEquals(icn, user.getIcn());
    }

    @Test
    public void testSetAdress() {
        List<String> address = List.of("Sample Address");
        user.setAdresses(List.of("Sample Address"));

        assertEquals(address, user.getAdresses());
    }

    @Test
    public void testHashCode2() {
        User user1 = new User();
        User user2 = new User();

        assertEquals(user1.hashCode(), user2.hashCode());

        user1.setIcn("1234567890123");
        user2.setIcn("1234567890123");

        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    public void testEquals2() {
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();

        assertTrue(user1.equals(user2));
        assertTrue(user2.equals(user1));

        user1.setIcn("1234567890123");
        user2.setIcn("1234567890123");

        assertTrue(user1.equals(user2));
        assertTrue(user2.equals(user1));

        assertFalse(user1.equals(user3));
        assertFalse(user2.equals(null));
        assertFalse(user2.equals("not a User object"));
    }
}

