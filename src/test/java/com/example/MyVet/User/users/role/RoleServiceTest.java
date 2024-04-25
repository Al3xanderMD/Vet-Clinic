package com.example.MyVet.User.users.role;


import com.example.MyVet.Exceptions.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RoleServiceTest {

    private RoleService roleService;

    @Mock
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        roleService = new RoleService(roleRepository);
    }

    @Test
    void testGetRoleByName() {
        // Arrange
        Role.ERole roleName = Role.ERole.ROLE_ADMIN;
        Role role = new Role(roleName);
        when(roleRepository.findByName(roleName)).thenReturn(Optional.of(role));

        // Act
        Role result = null;
        try {
            result = roleService.getRoleByName(roleName);
        } catch (EntityNotFoundException e) {
            fail("EntityNotFoundException should not be thrown");
        }

        // Assert
        assertNotNull(result);
        assertEquals(roleName, result.getName());
    }

    @Test
    void testGetRoleByName_EntityNotFoundException() {
        // Arrange
        Role.ERole roleName = Role.ERole.ROLE_ADMIN;
        when(roleRepository.findByName(roleName)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> roleService.getRoleByName(roleName));
    }

    @Test
    void testExistsRoleByName_True() {
        // Arrange
        Role.ERole roleName = Role.ERole.ROLE_ADMIN;
        when(roleRepository.existsByName(roleName)).thenReturn(true);

        // Act
        boolean result = roleService.existsRoleByName(roleName);

        // Assert
        assertTrue(result);
    }

    @Test
    void testExistsRoleByName_False() {
        // Arrange
        Role.ERole roleName = Role.ERole.ROLE_ADMIN;
        when(roleRepository.existsByName(roleName)).thenReturn(false);

        // Act
        boolean result = roleService.existsRoleByName(roleName);

        // Assert
        assertFalse(result);
    }
}
