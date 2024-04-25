package com.example.MyVet.User.config;

import com.example.MyVet.User.dto.*;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

    @Mock
    private AuthService authService;

    private AuthController authController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        authController = new AuthController(authService);
    }

    @Test
    public void testAuthenticateUser() {
        UserLoginDto userLoginDto = new UserLoginDto();
        UserJwtResponseDto expectedResponse = new UserJwtResponseDto("token", "id", "email", "role"); // completeaza astea

        when(authService.authenticateUser(userLoginDto)).thenReturn(expectedResponse);

        UserJwtResponseDto actualResponse = authController.authenticateUser(userLoginDto);

        assertEquals(expectedResponse, actualResponse);
        verify(authService, times(1)).authenticateUser(userLoginDto);
    }

    @Test
    public void testRegisterUser() throws MessagingException, UnsupportedEncodingException {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        UserRegisterResponseDto expectedResponse = new UserRegisterResponseDto("email", "pass", "role", "verificationCode", "icn", List.of("bau")); // completeaza astea

        when(authService.registerUser(userRegisterDto)).thenReturn(expectedResponse);

        UserRegisterResponseDto actualResponse = authController.registerUser(userRegisterDto);

        assertEquals(expectedResponse, actualResponse);
        verify(authService, times(1)).registerUser(userRegisterDto);
    }

    @Test
    public void testVerifyUser() {
        String code = "verificationCode";
        String successMessage = "verify_success";
        String failureMessage = "verify_fail";

        when(authService.verify(code)).thenReturn(true);

        String successResult = authController.verifyUser(code);
        assertEquals(successMessage, successResult);

        when(authService.verify(code)).thenReturn(false);

        String failureResult = authController.verifyUser(code);
        assertEquals(failureMessage, failureResult);

        verify(authService, times(2)).verify(code);
    }

    @Test
    public void testResetPasswordUser() {
        String email = "test@example.com";
        MessageDto expectedResponse = new MessageDto("message");

        when(authService.resetPasswordUser(email)).thenReturn(expectedResponse);

        MessageDto actualResponse = authController.resetPasswordUser(email);

        assertEquals(expectedResponse, actualResponse);
        verify(authService, times(1)).resetPasswordUser(email);
    }

    @Test
    public void testChangePasswordUser() {
        UserChangePasswordDto userChangePasswordDto = new UserChangePasswordDto();
        String token = "resetToken";
        MessageDto expectedResponse = new MessageDto("message");

        when(authService.changePasswordUser(userChangePasswordDto, token)).thenReturn(expectedResponse);

        MessageDto actualResponse = authController.changePasswordUser(userChangePasswordDto, token);

        assertEquals(expectedResponse, actualResponse);
        verify(authService, times(1)).changePasswordUser(userChangePasswordDto, token);
    }
}

