package com.example.MyVet.User.config;


import com.example.MyVet.User.users.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PasswordResetTokenTest {

    @Mock
    User mockUser = Mockito.mock(User.class);

    @InjectMocks
    private PasswordResetToken passwordResetToken;

    @Test
    public void testGetId() {
        assertNull(passwordResetToken.getId());
    }

    @Test
    public void testGetToken() {
        passwordResetToken.setToken("token");
        assertEquals("token", passwordResetToken.getToken());
    }

    @Test
    public void testGetUser() {
        assertEquals(mockUser, passwordResetToken.getUser());
    }

    @Test
    public void testEquals() {
        User user1 = new User();
        User user2 = new User();
        PasswordResetToken token1 = new PasswordResetToken(user1, "token1");
        PasswordResetToken token2 = new PasswordResetToken(user1, "token1");
        PasswordResetToken token3 = new PasswordResetToken(user2, "token1");
        PasswordResetToken token4 = new PasswordResetToken(user1, "token2");
        PasswordResetToken token5 = new PasswordResetToken(user1, "token1");
        token5.setId(String.valueOf(1));


        assertTrue(token1.equals(token1));


        assertTrue(token1.equals(token2));
        assertTrue(token2.equals(token1));


        assertTrue(token1.equals(token2));
        assertTrue(token2.equals(token3));
        assertTrue(token1.equals(token3));

        assertTrue(token1.equals(token2));
        assertTrue(token1.equals(token2));
        assertTrue(token1.equals(token2));

        assertFalse(token1.equals(null));
        assertFalse(token1.equals(user1));

        assertFalse(token1.equals(token4));
        assertFalse(token1.equals(token5));
    }

    @Test
    public void testHashCode() {
        User user = new User();
        user.setId("12345");
        PasswordResetToken token1 = new PasswordResetToken(user, "token1");
        PasswordResetToken token2 = new PasswordResetToken(user, "token2");
        PasswordResetToken token3 = new PasswordResetToken(user, "token1");

        assertNotEquals(token1.hashCode(), token2.hashCode());
        assertEquals(token1.hashCode(), token3.hashCode());
    }

    @Test
    void testToString() {
        User user = new User("john", "doe","email","3298282","299292","adress","password");
        PasswordResetToken token = new PasswordResetToken(user, "abc123");

        String expected = "PasswordResetToken(id=null, token=abc123, user=User(id=null, firstName=john, lastName=doe, email=email, phone=3298282, password=password, icn=299292, adresses=[adress], role=null, verificationCode=null, enabled=false), expiryDate=" + token.getExpiryDate() + ")";
        String actual = token.toString();

        assertEquals(expected, actual);
    }
    @Test
    void testSetExpiryDate() {
        User user = new User();
        String token = "my_token";
        PasswordResetToken resetToken = new PasswordResetToken(user, token);

        // Set expiry date to 1 hour from now
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR_OF_DAY, 1);
        Date expiryDate = cal.getTime();

        // Set the expiry date
        resetToken.setExpiryDate(expiryDate);

        // Check that the expiry date was set correctly
        assertEquals(expiryDate, resetToken.getExpiryDate());
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


}

