package com.example.MyVet.User.config;

import com.example.MyVet.Exceptions.PasswordResetTokenExpiredException;
import com.example.MyVet.User.JWT.JwtTokenProvider;
import com.example.MyVet.User.dto.*;
import com.example.MyVet.User.email.EmailService;
import com.example.MyVet.User.users.User;
import com.example.MyVet.User.users.UserService;
import com.example.MyVet.User.users.role.Role;
import com.example.MyVet.User.users.role.RoleService;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import static com.mongodb.assertions.Assertions.assertNull;
import static com.mongodb.assertions.Assertions.assertTrue;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private RoleService roleService;

    @Mock
    private PasswordResetTokenService passwordResetTokenService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private EmailService emailService;

    @Mock
    private JavaMailSender mailSender; // Add the mailSender mock

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
//    @Test
//    void registerUser_ShouldCreateUserWithRolePetOwner() throws MessagingException, UnsupportedEncodingException {
//        // Arrange
//        UserRegisterDto userRegisterDto = new UserRegisterDto();
//        userRegisterDto.setFirstName("John");
//        userRegisterDto.setLastName("Doe");
//        userRegisterDto.setEmail("john.doe@example.com");
//        userRegisterDto.setPassword("password");
//        // Set other properties for userRegisterDto
//
//        when(roleService.getRoleByName(Role.ERole.ROLE_PetOwner)).thenReturn(new Role());
//        when(encoder.encode(userRegisterDto.getPassword())).thenReturn("encodedPassword");
//
//        // Act
//        authService.registerUser(userRegisterDto);
//
//        // Assert
//        verify(roleService, times(1)).getRoleByName(Role.ERole.ROLE_PetOwner);
//        verify(encoder, times(1)).encode(userRegisterDto.getPassword());
//        verify(userService, times(1)).createUser(any(User.class));
////        verify(emailService, times(1)).sendVerificationEmail(any(User.class));
//        // Additional assertions for the created user, if needed
//    }

//    @Test
//    public void testRegisterUser() throws MessagingException, UnsupportedEncodingException {
//        // Create a userRegisterDto object
//        UserRegisterDto userRegisterDto = new UserRegisterDto();
//        userRegisterDto.setFirstName("John");
//        userRegisterDto.setLastName("Doe");
//        userRegisterDto.setAdress("address");
//        userRegisterDto.setIcn("1889289289");
//        userRegisterDto.setPhone("9192992");
//        userRegisterDto.setEmail("email");
//        userRegisterDto.setPassword("passsword");
//        // Set other properties as needed
//
//        // Mock the roleService.getRoleByName() method to return a role
//        when(roleService.getRoleByName(Role.ERole.ROLE_ADMIN)).thenReturn(new Role());
//
//        // Mock the passwordResetTokenService.createToken() method to do nothing (void method)
//        doNothing().when(passwordResetTokenService).createToken(any(PasswordResetToken.class));
//
//        // Call the registerUser() method
//        authService.registerUser(userRegisterDto);
//
//        // Perform your assertions or verifications
//        // For example, you can verify that passwordResetTokenService.createToken() was called
//        verify(passwordResetTokenService).createToken(any(PasswordResetToken.class));
//    }
//
//    @Test
//    public void testSendVerificationEmail() throws MessagingException, UnsupportedEncodingException {
//        // Create a user object
//        User user = new User();
//        user.setFirstName("John");
//        user.setEmail("email");
//        // Set other properties as needed
//
//        // Mock the mailSender.createMimeMessage() method to return a MimeMessage
//        MimeMessage mimeMessage = mock(MimeMessage.class);
//        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
//
//        // Mock the mailSender.send() method to do nothing (void method)
//        doNothing().when(mailSender).send(any(MimeMessage.class));
//
//        // Call the sendVerificationEmail() method
//        authService.sendVerificationEmail(user);
//
//        // Perform your assertions or verifications
//        // For example, you can verify that mailSender.send() was called with the correct arguments
//        verify(mailSender).send(any(MimeMessage.class));
//    }
//
//    @Test
//    void testRegisterUserWithRoleAdmin() throws MessagingException, UnsupportedEncodingException {
//        // Mock the required dependencies
//        Role role = new Role(Role.ERole.ROLE_ADMIN);
//        when(roleService.getRoleByName(Role.ERole.ROLE_ADMIN)).thenReturn(role);
//        when(encoder.encode(anyString())).thenReturn("hashedPassword");
//        doNothing().when(userService).createUser(any(User.class));
//        doNothing().when(emailService).sendMail(anyString(), anyString(), anyString(), anyString());
//        when(mailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
//
//        // Create a UserRegisterDto with ROLE_ADMIN role
//        UserRegisterDto userRegisterDto = new UserRegisterDto();
//        userRegisterDto.setFirstName("John");
//        userRegisterDto.setLastName("Doe");
//        userRegisterDto.setEmail("john.doe@example.com");
//        userRegisterDto.setPhone("123456789");
//        userRegisterDto.setIcn("ICN123");
//        userRegisterDto.setAdress("Some Address");
//        userRegisterDto.setPassword("password");
//        userRegisterDto.setRole("ROLE_ADMIN");
//
//        // Call the method
//        authService.registerUser(userRegisterDto);
//
//        // Verify the interactions and assertions
//        verify(roleService).getRoleByName(Role.ERole.ROLE_ADMIN);
//        verify(encoder).encode("password");
//        verify(userService).createUser(any(User.class));
//        verify(emailService).sendMail(
//                eq("alex.ionascu2001@gmail.com"),
//                eq("john.doe@example.com"),
//                eq("Please verify your registration"),
//                contains("VERIFY")
//        );
//    }
//
//
//@Test
//    void testRegisterUserWithRoleUser() throws MessagingException, UnsupportedEncodingException {
//        // Mock the required dependencies
//        Role role = new Role(Role.ERole.ROLE_USER);
//        when(roleService.getRoleByName(Role.ERole.ROLE_USER)).thenReturn(role);
//        when(encoder.encode(anyString())).thenReturn("hashedPassword");
//        doNothing().when(userService).createUser(any(User.class));
//        doNothing().when(emailService).sendMail(anyString(), anyString(), anyString(), anyString());
//
//        // Create a UserRegisterDto with ROLE_USER role
//        UserRegisterDto userRegisterDto = new UserRegisterDto();
//        userRegisterDto.setFirstName("Jane");
//        userRegisterDto.setLastName("Smith");
//        userRegisterDto.setEmail("jane.smith@example.com");
//        userRegisterDto.setPhone("987654321");
//        userRegisterDto.setIcn("ICN456");
//        userRegisterDto.setAdress("Another Address");
//        userRegisterDto.setPassword("password");
//
//        // Call the method
//        authService.registerUser(userRegisterDto);
//
//        // Verify the interactions and assertions
//        verify(roleService).getRoleByName(Role.ERole.ROLE_USER);
//        verify(encoder).encode("password");
//        verify(userService).createUser(any(User.class));
//        verify(emailService).sendMail(
//                eq("alex.ionascu2001@gmail.com"),
//                eq("jane.smith@example.com"),
//                eq("Please verify your registration"),
//                contains("VERIFY")
//        );
//    }

    @Test
    void authenticateUser_ShouldReturnUserJwtResponseDto() {
        // Arrange
        UserLoginDto userLoginDto = new UserLoginDto("example@example.com", "password");
        User user = new User();
        user.setId("1");
        user.setEmail("example@example.com");
        user.setRole(new Role());
        user.getRole().setName(Role.ERole.ROLE_USER);

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(jwtTokenProvider.generateJwtToken(authentication)).thenReturn("jwt-token");
        when(authentication.getPrincipal()).thenReturn(user);

        // Act
        UserJwtResponseDto response = authService.authenticateUser(userLoginDto);

        // Assert
        assertEquals("jwt-token", response.getToken());
        assertEquals("1", response.getId());
        assertEquals("example@example.com", response.getEmail());
        assertEquals("ROLE_USER", response.getRole());
//        verify(SecurityContextHolder.getContext()).setAuthentication(authentication);
    }

    @Test
    void verify_WithValidVerificationCode_ShouldReturnTrue() {
        // Arrange
        String verificationCode = "123456";
        User user = new User();
        user.setEnabled(false);
        when(userService.getUserByVerificationCode(verificationCode)).thenReturn(user);

        // Act
        boolean result = authService.verify(verificationCode);

        // Assert
        assertTrue(result);
        assertNull(user.getVerificationCode());
        assertTrue(user.isEnabled());
        verify(userService).updateUser(user);
    }

    @Test
    void verify_WithInvalidVerificationCode_ShouldReturnFalse() {
        // Arrange
        String verificationCode = "123456";
        when(userService.getUserByVerificationCode(verificationCode)).thenReturn(null);

        // Act
        boolean result = authService.verify(verificationCode);

        // Assert
        assertFalse(result);
        verify(userService, never()).updateUser(any(User.class));
    }

    @Test
    void verify_WithAlreadyEnabledUser_ShouldReturnFalse() {
        // Arrange
        String verificationCode = "123456";
        User user = new User();
        user.setEnabled(true);
        when(userService.getUserByVerificationCode(verificationCode)).thenReturn(user);

        // Act
        boolean result = authService.verify(verificationCode);

        // Assert
        assertFalse(result);
        verify(userService, never()).updateUser(any(User.class));
    }

    @Test
    void resetPasswordUser_ShouldSendResetPasswordEmail() {
        // Arrange
        String email = "johndoe@example.com";
        User user = new User();
        when(userService.getUserByEmail(email)).thenReturn(user);
        doNothing().when(passwordResetTokenService).createToken(any(PasswordResetToken.class));

        // Act
        MessageDto response = authService.resetPasswordUser(email);

        // Assert
        assertEquals("Email has been sent.", response.getMessage());
        verify(emailService).sendMail(eq("alex.ionascu2001@gmail.com"), eq(email), eq("Reset Password"), anyString());
    }

    @Test
    void changePasswordUser_WithValidToken_ShouldChangeUserPassword() {
        // Arrange
        UserChangePasswordDto userChangePasswordDto = new UserChangePasswordDto();
        userChangePasswordDto.setNewPassword("new-password");

        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setUser(new User());
        when(passwordResetTokenService.getPasswordResetTokenByToken(token)).thenReturn(passwordResetToken);
        when(passwordResetTokenService.isPasswordResetTokenExpired(passwordResetToken)).thenReturn(false);
        User user = passwordResetToken.getUser();
        when(encoder.encode(userChangePasswordDto.getNewPassword())).thenReturn("encoded-new-password");

        // Act
        MessageDto response = authService.changePasswordUser(userChangePasswordDto, token);

        // Assert
        assertEquals("Password has changed.", response.getMessage());
        assertEquals("encoded-new-password", user.getPassword());
        verify(userService).updateUser(user);
    }

    @Test
    void changePasswordUser_WithExpiredToken_ShouldThrowPasswordResetTokenExpiredException() {
        // Arrange
        UserChangePasswordDto userChangePasswordDto = new UserChangePasswordDto();
        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setUser(new User());
        when(passwordResetTokenService.getPasswordResetTokenByToken(token)).thenReturn(passwordResetToken);
        when(passwordResetTokenService.isPasswordResetTokenExpired(passwordResetToken)).thenReturn(true);

        // Act & Assert
        assertThrows(PasswordResetTokenExpiredException.class, () -> authService.changePasswordUser(userChangePasswordDto, token));
        verify(userService, never()).updateUser(any(User.class));
    }
}
