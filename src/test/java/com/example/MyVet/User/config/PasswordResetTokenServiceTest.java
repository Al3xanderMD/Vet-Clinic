package com.example.MyVet.User.config;


import com.example.MyVet.User.users.User;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PasswordResetTokenServiceTest {
    @Mock
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @InjectMocks
    private PasswordResetTokenService passwordResetTokenService;

    @Test
    void getPasswordResetTokenByTokenTest() {
        // Given
        String token = "token";
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(token);
        Mockito.when(passwordResetTokenRepository.findByToken(token)).thenReturn(Optional.of(passwordResetToken));

        // When
        PasswordResetToken result = passwordResetTokenService.getPasswordResetTokenByToken(token);

        // Then
        assertEquals(passwordResetToken, result);
    }

    @Test
    void getPasswordResetTokenByTokenNotFoundTest() {
        // Given
        String token = "token";
        Mockito.when(passwordResetTokenRepository.findByToken(token)).thenReturn(Optional.empty());

        // When
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> passwordResetTokenService.getPasswordResetTokenByToken(token));

        // Then
        assertEquals("PasswordResetToken Not Found: " + token, exception.getMessage());
    }

    @Test
    void createTokenTest() {
        // Given
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken("token");
        Mockito.when(passwordResetTokenRepository.existsByToken(passwordResetToken.getToken())).thenReturn(false);

        // When
        passwordResetTokenService.createToken(passwordResetToken);

        // Then
        Mockito.verify(passwordResetTokenRepository, Mockito.times(1)).save(passwordResetToken);
    }

    @Test
    void createTokenAlreadyExistsTest() {
        // Given
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken("1234567890");
        passwordResetToken.setUser(new User());

        //When
        Mockito.when(passwordResetTokenRepository.existsByToken(passwordResetToken.getToken()))
                .thenReturn(true);

        EntityExistsException exception = assertThrows(EntityExistsException.class,
                () -> passwordResetTokenService.createToken(passwordResetToken));
        assertEquals("Password Reset Token already exists: null", exception.getMessage());

        // Then
        assertEquals("Password Reset Token already exists: null", exception.getMessage());
    }

    @Test
    void existsPasswordResetTokenByTokenTest() {
        // Given
        String token = "token";
        Mockito.when(passwordResetTokenRepository.existsByToken(token)).thenReturn(true);

        // When
        Boolean result = passwordResetTokenService.existsPasswordResetTokenByToken(token);

        // Then
        assertTrue(result);
    }

    @Test
    void testEqualsAndHashCodeWithSameObject() {
        // Given
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken("token");

        // Then
        assertEquals(passwordResetToken, passwordResetToken);
        assertEquals(passwordResetToken.hashCode(), passwordResetToken.hashCode());
    }

    @Test
    void testEqualsAndHashCodeWithEqualObjects() {
        // Given
        PasswordResetToken passwordResetToken1 = new PasswordResetToken();
        passwordResetToken1.setToken("token");

        PasswordResetToken passwordResetToken2 = new PasswordResetToken();
        passwordResetToken2.setToken("token");

        // Then
        assertEquals(passwordResetToken1, passwordResetToken2);
        assertEquals(passwordResetToken1.hashCode(), passwordResetToken2.hashCode());
    }

    @Test
    void testEqualsAndHashCodeWithDifferentObjects() {
        // Given
        PasswordResetToken passwordResetToken1 = new PasswordResetToken();
        passwordResetToken1.setToken("token1");

        PasswordResetToken passwordResetToken2 = new PasswordResetToken();
        passwordResetToken2.setToken("token2");

        // Then
        assertNotEquals(passwordResetToken1, passwordResetToken2);
        assertNotEquals(passwordResetToken1.hashCode(), passwordResetToken2.hashCode());
    }

    @Test
    void testEqualsAndHashCodeWithDifferentTypes() {
        // Given
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken("token");

        String someString = "some string";

        // Then
        assertNotEquals(passwordResetToken, someString);
        assertNotEquals(passwordResetToken.hashCode(), someString.hashCode());
    }

    @Test
    void testEqualsAndHashCodeWithNullObject() {
        // Given
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken("token");

        // Then
        assertNotEquals(passwordResetToken, null);
    }
    @Test
    public void testIsPasswordResetTokenExpired() {
        User user = new User();
        user.setEmail("testemail");
        PasswordResetToken passwordResetToken =
                new PasswordResetToken(user, "testtoken");

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, -60);
        passwordResetToken.setExpiryDate(cal.getTime());

        Assertions.assertTrue(passwordResetTokenService.isPasswordResetTokenExpired(passwordResetToken));
    }

}
