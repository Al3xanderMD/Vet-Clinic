package com.example.MyVet.User.users;

import com.example.MyVet.Exceptions.EntityNotFoundException;
import com.example.MyVet.Exceptions.UserNotFoundException;
import jakarta.persistence.EntityExistsException;
import org.assertj.core.api.InstanceOfAssertFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

//import static jdk.internal.jimage.ImageLocation.verify;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.internal.verification.VerifyNoMoreInteractions.verifyNoMoreInteractions;
import static org.springframework.test.web.client.ExpectedCount.times;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user, existingUser;

    @BeforeEach
    public void setUp() {

        user = new User("testuser", "lastname","email","2881","774889","address","password123");
        existingUser = new User("testuser", "lastname","email","07n-am cartela","774889","address","password123");
    }

    @Test
    public void testLoadUserByUsername() {
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(java.util.Optional.ofNullable(user));
        UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
        assertNotNull(userDetails);
        assertEquals(user.getUsername(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
    }

    @Test
    public void testGetUserByUsername() {
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(java.util.Optional.ofNullable(user));
        User foundUser = userService.getUserByEmail(user.getUsername());
        assertNotNull(foundUser);
        assertEquals(user.getUsername(), foundUser.getUsername());
        assertEquals(user.getPassword(), foundUser.getPassword());
    }

    @Test
    public void testGetUserByUsernameNotFound() {
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(java.util.Optional.empty());
        assertThrows(UserNotFoundException.class, () -> {
            userService.getUserByEmail(user.getUsername());
        });
    }

    @Test
    public void testCreateUser() {
        Mockito.when(userRepository.existsByEmail(anyString())).thenReturn(false);
        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);
        assertDoesNotThrow(() -> userService.createUser(user));
        Mockito.verify(userRepository, Mockito.times(1)).save(any(User.class));
    }

    @Test
    public void testCreateUserEntityExistsException() {
        Mockito.when(userRepository.existsByEmail(anyString())).thenReturn(true);
        assertThrows(EntityExistsException.class, () -> {
            userService.createUser(user);
        });
    }

    @Test
    public void testUpdateUserEntityNotFoundException() {
        Mockito.lenient().when(userRepository.existsByEmail(anyString())).thenReturn(false);
        assertThrows(EntityExistsException.class, () -> {
            userService.updateUser(user);
        });
    }

    @Test
    public void testExistsUserByUsername() {
        Mockito.when(userRepository.existsByEmail(anyString())).thenReturn(true);
        assertTrue(userService.existsUserByEmail(user.getUsername()));
    }

    @Test
    public void testExistsUserByUsernameFalse() {
        Mockito.when(userRepository.existsByEmail(anyString())).thenReturn(false);
        assertFalse(userService.existsUserByEmail(user.getUsername()));
    }
    @Test
    public void testCreateUserWithExistingId() throws EntityExistsException {
        User user = new User("John", "Doe", "johndoe@example.com", "1234567890","77889","address", "password");
        when(userService.existsUserById(user.getId())).thenReturn(true);
        when(userService.existsUserByEmail(user.getEmail())).thenReturn(false);

        userService.createUser(user);
    }

    @Test
    public void testExistsUserByEmailWithNonExistingEmail() {
        String email = "johndoe@example.com";
        when(userService.existsUserByEmail(email)).thenReturn(false);

        boolean result = userService.existsUserByEmail(email);

        assertFalse(result);
    }

    @Test
    public void testExistsUserByEmailWithExistingEmail() {
        String email = "johndoe@example.com";
        when(userService.existsUserByEmail(email)).thenReturn(true);

        boolean result = userService.existsUserByEmail(email);

        assertTrue(result);
    }

    @Test
    public void testExistsUserByIdWithNonExistingId() {
        String id = "1";
        when(userService.existsUserById(id)).thenReturn(false);

        boolean result = userService.existsUserById(id);

        assertFalse(result);
    }

    @Test
    public void testExistsUserByIdWithExistingId() {
        String id = "1";
        when(userService.existsUserById(id)).thenReturn(true);

        boolean result = userService.existsUserById(id);

        assertTrue(result);
    }
    @Test
    public void testGetUserByVerificationCode() {
        String verificationCode = "123456";
        User user = new User("testuser", "lastname", "email", "2881", "774889", "address", "password123");

        Mockito.when(userRepository.findByVerificationCode(verificationCode)).thenReturn(Optional.ofNullable(user));

        User foundUser = userService.getUserByVerificationCode(verificationCode);

        assertNotNull(foundUser);
        assertEquals(user.getUsername(), foundUser.getUsername());
        assertEquals(user.getPassword(), foundUser.getPassword());
    }

    @Test
    public void testGetUserByVerificationCodeNotFound() {
        String verificationCode = "123456";

        Mockito.when(userRepository.findByVerificationCode(verificationCode)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            userService.getUserByVerificationCode(verificationCode);
        });
    }


    @Test
    public void testUpdateUser() {
        User user = new User("testuser", "lastname", "email", "2881", "774889", "address", "password123");
        user.setId("1");

        Mockito.when(userRepository.existsById(user.getId())).thenReturn(true);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        assertDoesNotThrow(() -> userService.updateUser(user));

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    public void testExistsUserByEmail() {
        String email = "test@example.com";

        Mockito.when(userRepository.existsByEmail(email)).thenReturn(true);

        assertTrue(userService.existsUserByEmail(email));
    }

    @Test
    public void testExistsUserByEmailFalse() {
        String email = "test@example.com";

        Mockito.when(userRepository.existsByEmail(email)).thenReturn(false);

        assertFalse(userService.existsUserByEmail(email));
    }


    @Test
    public void testGetAll() {
        List<User> userList = List.of(user, existingUser);
        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.getAll();

        //assertThat(result, new InstanceOfAssertFactory<>()).hasSize(2).contains(user, existingUser);

       // verify(userRepository, times(1)).findAll();
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testDeleteById_ExistingUser_Success() {
        when(userRepository.findById("1")).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        User deletedUser = userService.deleteById("1");

        assertFalse(deletedUser.isEnabled());

        //verify(userRepository, times(1)).findById("1");
        //verify(userRepository, times(1)).save(any(User.class));
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testDeleteById_NonExistingUser_EntityNotFoundExceptionThrown() {
        when(userRepository.findById("2")).thenReturn(Optional.empty());

        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> userService.deleteById("2"));

        //verify(userRepository, times(1)).findById("2");
        verifyNoMoreInteractions(userRepository);
    }


    @Test
    void updateUserTest() {
        String userId = "123"; // Provide a sample user ID
        UserDTO userDTO = new UserDTO(); // Provide necessary data for userDTO
        User existingUser = new User(); // Create a sample existing User object

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(existingUser);

        User result = userService.updateById(userId, userDTO);
        Assertions.assertEquals(existingUser, result, "Returned user does not match the expected updated user.");
    }

    @Test
    void createUserTest() {
        User user = new User(); // Create a sample User object

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        Assertions.assertDoesNotThrow(() -> userService.createUser(user), "createUser() method throws an exception when it shouldn't.");
    }

    @Test
    void getByIdTest() {
        String userId = "123"; // Provide a sample user ID
        User user = new User(); // Create a sample User object

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getById(userId);
        Assertions.assertEquals(Optional.of(user), result, "Returned user does not match the expected user.");
    }

    @Test
    void existsUserByIdTest() {
        String userId = "123"; // Provide a sample user ID

        Mockito.when(userRepository.existsById(userId)).thenReturn(true);

        boolean result = userService.existsUserById(userId);
        Assertions.assertTrue(result, "existsUserById() method returns false for an existing user ID.");
    }
}


