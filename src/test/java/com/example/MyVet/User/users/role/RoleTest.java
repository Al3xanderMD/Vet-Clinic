package com.example.MyVet.User.users.role;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class RoleTest {
    @Test
    public void testConstructorAndGetters() {
        Role role = new Role(Role.ERole.ROLE_ADMIN);
        Assertions.assertEquals(Role.ERole.ROLE_ADMIN, role.getName());
    }

    @Test
    public void testSetName() {
        Role role = new Role();
        role.setName(Role.ERole.ROLE_ADMIN);
        Assertions.assertEquals(Role.ERole.ROLE_ADMIN, role.getName());
    }

    @Test
    void testSetId() {
        Role role = new Role(Role.ERole.ROLE_PetOwner);
        role.setId("1");
        Assertions.assertEquals("1", role.getId());
    }

    @Test
    void testHashCode() {
        Role role1 = new Role(Role.ERole.ROLE_PetOwner);
        Role role2 = new Role(Role.ERole.ROLE_VET);
        Role role3 = new Role(Role.ERole.ROLE_PetOwner);

        // Test consistency
        Assertions.assertEquals(role1.hashCode(), role1.hashCode());

        // Test equality implies same hash code
        Assertions.assertTrue(role1.equals(role3));
        Assertions.assertEquals(role1.hashCode(), role3.hashCode());

        // Test inequality implies different hash code
        Assertions.assertFalse(role1.equals(role2));
        Assertions.assertNotEquals(role1.hashCode(), role2.hashCode());
    }

    @Test
    void testEquals() {
        Role role1 = new Role(Role.ERole.ROLE_PetOwner);
        Role role2 = new Role(Role.ERole.ROLE_VET);
        Role role3 = new Role(Role.ERole.ROLE_PetOwner);

        // Test reflexivity
        assertTrue(role1.equals(role1));

        // Test symmetry
        assertTrue(role1.equals(role3));
        assertTrue(role3.equals(role1));

        // Test transitivity
        assertTrue(role1.equals(role3));
        assertFalse(role3.equals(role2));
        assertFalse(role1.equals(role2));

        // Test null comparison
        assertFalse(role1.equals(null));

        // Test comparison to different type
        assertFalse(role1.equals("not a role"));

        // Test comparison with same name and different ID
        role1.setId("1");
        role3.setId("2");
        assertFalse(role1.equals(role3));

        // Test comparison with different name and same ID
        role1.setName(Role.ERole.ROLE_VET);
        role2.setName(Role.ERole.ROLE_VET);
        role1.setId("1");
        role2.setId("1");
        assertTrue(role1.equals(role2));

        // Test comparison with different name and different ID
        role1.setId("1");
        role2.setId("2");
        assertFalse(role1.equals(role2));
    }


    @Test
    void testNoArgsConstructor() {
        Role role = new Role();
        Assertions.assertNull(role.getId());
        Assertions.assertNull(role.getName());
    }

    @Test
    void testAllArgsConstructor() {
        Role role = new Role(Role.ERole.ROLE_VET);
        Assertions.assertNull(role.getId());
        Assertions.assertEquals(Role.ERole.ROLE_VET, role.getName());
    }

    @Test
    void testEqualsAndHashCodeForSameObject() {
        Role role = new Role(Role.ERole.ROLE_PetOwner);
        Assertions.assertTrue(role.equals(role));
        Assertions.assertEquals(role.hashCode(), role.hashCode());
    }

    @Test
    void testEqualsAndHashCodeForEqualObjects() {
        Role role1 = new Role(Role.ERole.ROLE_PetOwner);
        Role role2 = new Role(Role.ERole.ROLE_PetOwner);
        Assertions.assertTrue(role1.equals(role2));
        Assertions.assertEquals(role1.hashCode(), role2.hashCode());
    }
    @Test
    public void testEqualsAndHashCodeWithNullValues() {
        Role role1 = new Role();
        Role role2 = new Role();
        assertTrue(role1.equals(role2));
        assertEquals(role1.hashCode(), role2.hashCode());
    }

    @Test
    public void testEqualsAndHashCodeWithNonNullValues() {
        Role role1 = new Role(Role.ERole.ROLE_ADMIN);
        Role role2 = new Role(Role.ERole.ROLE_ADMIN);
        assertTrue(role1.equals(role2));
        assertEquals(role1.hashCode(), role2.hashCode());
    }

    @Test
    public void testEqualsAndHashCodeWithDifferentValues() {
        Role role1 = new Role(Role.ERole.ROLE_ADMIN);
        Role role2 = new Role(Role.ERole.ROLE_VET);
        assertFalse(role1.equals(role2));
        assertNotEquals(role1.hashCode(), role2.hashCode());
    }
    @Test
    void testHashCodeWithNullId() {
        Role role = new Role(Role.ERole.ROLE_PetOwner);
        role.setId(null);
        assertNotEquals(Objects.hash(role.getName()), role.hashCode());
    }

    @Test
    void testEqualsWithSameInstance() {
        Role role = new Role(Role.ERole.ROLE_ADMIN);
        assertTrue(role.equals(role));
    }

    @Test
    void testEqualsWithDifferentInstance() {
        Role role1 = new Role(Role.ERole.ROLE_PetOwner);
        Role role2 = new Role(Role.ERole.ROLE_VET);
        assertFalse(role1.equals(role2));
    }

    @Test
    void testEqualsWithNull() {
        Role role = new Role(Role.ERole.ROLE_VET);
        assertFalse(role.equals(null));
    }

    @Test
    void testEqualsWithDifferentClass() {
        Role role = new Role(Role.ERole.ROLE_VET);
        assertFalse(role.equals("not a role"));
    }

    @Test
    void testEqualsAndHashCodeWithNullId() {
        Role role1 = new Role(Role.ERole.ROLE_ADMIN);
        Role role2 = new Role(Role.ERole.ROLE_ADMIN);
        role1.setId(null);
        role2.setId(null);

        // Test consistency
        assertEquals(role1.hashCode(), role1.hashCode());
        assertEquals(role2.hashCode(), role2.hashCode());

        // Test equality implies same hash code
        assertTrue(role1.equals(role2));
        assertEquals(role1.hashCode(), role2.hashCode());
    }
    @Test
    void testCanEqualMethod_WithDifferentType() {
        Role role = new Role(Role.ERole.ROLE_ADMIN);
        assertFalse(role.equals("not a role"));
    }

    @Test
    void testToString_WithIdIsNull() {
        Role role = new Role(Role.ERole.ROLE_ADMIN);
        role.setName(Role.ERole.ROLE_VET);

        String expected = "Role(id=null, name=ROLE_VET)";
        assertEquals(expected, role.toString());
    }

    @Test
    void testToString_WithNonNullId() {
        Role role = new Role(Role.ERole.ROLE_ADMIN);
        role.setId("1");
        role.setName(Role.ERole.ROLE_VET);

        String expected = "Role(id=1, name=ROLE_VET)";
        assertEquals(expected, role.toString());
    }

    @Test
    void testToString_WithNameIsNull() {
        Role role = new Role(Role.ERole.ROLE_ADMIN);
        role.setId("1");

        String expected = "Role(id=1, name=null)";
        assertNotEquals(expected, role.toString());
    }

    @Test
    void testHashCode_WithNullField() {
        Role role1 = new Role(null);
        Role role2 = new Role(Role.ERole.ROLE_PetOwner);

        // Test inequality implies different hash code
        assertFalse(role1.equals(role2));
        assertNotEquals(role1.hashCode(), role2.hashCode());
    }

    @Test
    void testEquals_WithDifferentType() {
        Role role = new Role(Role.ERole.ROLE_ADMIN);
        assertFalse(role.equals("not a role"));
    }

    @Test
    void testEquals_WithNullField() {
        Role role1 = new Role(null);
        Role role2 = new Role(Role.ERole.ROLE_PetOwner);

        assertFalse(role1.equals(role2));
        assertFalse(role2.equals(role1));
    }
    @Test
    void testEqualsAndHashCodeForNullId() {
        Role role1 = new Role(Role.ERole.ROLE_PetOwner);
        Role role2 = new Role(Role.ERole.ROLE_PetOwner);

        assertTrue(role1.equals(role2));
        assertEquals(role1.hashCode(), role2.hashCode());
    }

    @Test
    void testEqualsAndHashCodeForNonNullId() {
        Role role1 = new Role(Role.ERole.ROLE_PetOwner);
        role1.setId("1");

        Role role2 = new Role(Role.ERole.ROLE_PetOwner);
        role2.setId("2");

        assertFalse(role1.equals(role2));
        assertNotEquals(role1.hashCode(), role2.hashCode());
    }

    @Test
    void testToString() {
        Role role = new Role(Role.ERole.ROLE_ADMIN);
        role.setId("1");

        String expected = "Role(id=1, name=ROLE_ADMIN)";
        assertEquals(expected, role.toString());
    }

    @Test
    void testEqualsAndHashCodeForNullName() {
        Role role1 = new Role();
        Role role2 = new Role();

        assertTrue(role1.equals(role2));
        assertEquals(role1.hashCode(), role2.hashCode());
    }

    @Test
    void testEqualsAndHashCodeForNonNullName() {
        Role role1 = new Role(Role.ERole.ROLE_PetOwner);
        Role role2 = new Role(Role.ERole.ROLE_ADMIN);

        assertFalse(role1.equals(role2));
        assertNotEquals(role1.hashCode(), role2.hashCode());
    }

    @Test
    void testEqualsAndHashCodeForDifferentClasses() {
        Role role = new Role(Role.ERole.ROLE_PetOwner);
        String notARole = "not a role";

        assertFalse(role.equals(notARole));
    }
    @Test
    void testSetNameWithNull() {
        Role role = new Role(Role.ERole.ROLE_ADMIN);
        role.setName(null);
        Assertions.assertNull(role.getName());
    }
    @Test
    void testAllArgsConstructorWithNonNullId() {
        Role role = new Role(Role.ERole.ROLE_VET);
        role.setId("1");
        Assertions.assertEquals("1", role.getId());
        Assertions.assertEquals(Role.ERole.ROLE_VET, role.getName());
    }
    @Test
    void equals_shouldReturnFalseForNullArgument() {
        Role role = new Role(Role.ERole.ROLE_ADMIN);

        assertFalse(role.equals(null));
    }

    @Test
    void equals_shouldReturnFalseForDifferentClass() {
        Role role = new Role(Role.ERole.ROLE_ADMIN);

        assertFalse(role.equals("ADMIN"));
    }
    @Test
    void testEqualsReturnsFalseForNull() {
        Role role1 = new Role(Role.ERole.ROLE_ADMIN);
        assertFalse(role1.equals(null));
    }

    @Test
    void testEqualsReturnsFalseForDifferentObjectClass() {
        Role role1 = new Role(Role.ERole.ROLE_ADMIN);
        assertFalse(role1.equals("admin"));
    }
    @Test
    void equals_shouldReturnFalse_whenComparedObjectIsNotInstanceOfRole() {
        // Arrange
        Role role = new Role(Role.ERole.ROLE_ADMIN);
        Object obj = new Object();

        // Act
        boolean result = role.equals(obj);

        // Assert
        assertFalse(result);
    }

    @Test
    void equals_shouldReturnFalse_whenComparedObjectIsNull() {
        // Arrange
        Role role = new Role(Role.ERole.ROLE_ADMIN);
        Role nullRole = null;

        // Act
        boolean result = role.equals(nullRole);

        // Assert
        assertFalse(result);
    }
    @Test
    void testEqualsWithDifferentName() {
        Role role1 = new Role(Role.ERole.ROLE_ADMIN);
        Role role2 = new Role(Role.ERole.ROLE_USER);

        assertFalse(role1.equals(role2));
        assertFalse(role2.equals(role1));
    }

    @Test
    void testEqualsWithDifferentIdAndSameName() {
        Role role1 = new Role(Role.ERole.ROLE_PetOwner);
        role1.setId("1");
        Role role2 = new Role(Role.ERole.ROLE_PetOwner);
        role2.setId("2");

        assertFalse(role1.equals(role2));
        assertFalse(role2.equals(role1));
    }



}