package com.example.MyVet.User.JWT;

import com.example.MyVet.User.users.User;
import io.jsonwebtoken.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(MockitoExtension.class)
class JwtTokenProviderTest {

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private Authentication authentication;
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.auth.jwtSecret}")
    String jwtSecret = UUID.randomUUID().toString();
    @Value("${app.auth.jwtExpirationMs}")
    private int jwtExpirationMs;
    @BeforeEach
    void setUp() {
        jwtTokenProvider = new JwtTokenProvider();
        ReflectionTestUtils.setField(jwtTokenProvider, "jwtSecret", "testsecretkeyertyuioiuytrertyuiuytretyuiuytrertyuytrertyuytrdfguiuoi345678987654345678ulyterrtyioyetrrtrytu");
        ReflectionTestUtils.setField(jwtTokenProvider, "jwtExpirationMs", 10000);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    @Test
    void generateJwtToken_shouldGenerateToken() {
        // Create a mock authentication object
        Authentication authentication = mock(Authentication.class);
        User userPrincipal = new User("testuser", "lastname","email","2881","885933","address","password123");
        when(authentication.getPrincipal()).thenReturn(userPrincipal);

        // Call the method being tested
        String jwtToken = jwtTokenProvider.generateJwtToken(authentication);

        // Verify the results
        assertNotNull(jwtToken);
        assertTrue(jwtToken.length() > 0);
    }

    @Test
    void validateJwtToken_shouldReturnFalseWhenTokenIsInvalid() {
        String jwtToken = "invalidtoken";

        boolean isValid = jwtTokenProvider.validateJwtToken(jwtToken);

        assertFalse(isValid);
    }
    @Test
    void validateJwtToken_shouldReturnFalseWhenTokenIsExpired() {
        // Arrange
        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyLCJleHAiOjE1MTYyMzkwMjJ9.zHHzhPf27g-lSN60jzV7-0ZxWJuc0e1ZaHju6pkK1Ow"; // Expired token

        // Act
        boolean isValid = jwtTokenProvider.validateJwtToken(jwtToken);

        // Assert
        assertFalse(isValid);
    }

    @Test
    void validateJwtToken_shouldReturnFalseWhenTokenIsNotSignedWithCorrectSecret() {
        // Arrange
        String jwtToken = Jwts.builder()
                .setSubject("testuser")
                .signWith(SignatureAlgorithm.HS512, "wrongsecrethf83ry7ty5484htg84hg4h98rj39hg945dgfhytigbfnruethfudvbuergt87734g8r7fuehwruhf87w7hgf87wr7hgf7werg8fgw4847hg8iudgh4y")
                .compact();

        // Act
        boolean isValid = jwtTokenProvider.validateJwtToken(jwtToken);

        // Assert
        assertFalse(isValid);
    }

    @Test
    void validateJwtToken_shouldReturnFalseWhenTokenIsMalformed() {
        // Arrange
        String jwtToken = "malformed-token";

        // Act
        boolean isValid = jwtTokenProvider.validateJwtToken(jwtToken);

        // Assert
        assertFalse(isValid);
    }

    @Test
    void validateJwtToken_shouldReturnTrueWhenTokenIsValid() {
        // Arrange
        User user = new User("testuser", "lastname","email","2881","884995","address","password123");
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);
        String jwtToken = jwtTokenProvider.generateJwtToken(authentication);

        // Act
        boolean isValid = jwtTokenProvider.validateJwtToken(jwtToken);

        // Assert
        assertTrue(isValid);
    }
    @Test
    void getUserNameFromJwtToken_shouldReturnUsername() {
        // Arrange
        User user = new User("testuser", "lastname","email","2881","884985","address","password123");
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);
        String jwtToken = jwtTokenProvider.generateJwtToken(authentication);

        // Act
        String email = jwtTokenProvider.getUserNameFromJwtToken(jwtToken);

        // Assert
        assertEquals("email", email);
    }

    @Test
    public void testValidateJwtTokenExpired() {
        String token = Jwts.builder()
                .setSubject("username")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() - 1000))
                .signWith(SignatureAlgorithm.HS512, "wrongsecrethf83ry7ty5484htg84hg4h98rj39hg945dgfhytigbfnruethfudvbuergt87734g8r7fuehwruhf87w7hgf87wr7hgf7werg8fgw4847hg8iudgh4y")
                .compact();

        boolean result = jwtTokenProvider.validateJwtToken(token);

        assertFalse(result);
    }

    @Test
    public void testValidateJwtTokenUnsupported() {
        String token = "unsupportedtoken";

        boolean result = jwtTokenProvider.validateJwtToken(token);

        assertFalse(result);
    }

    @Test
    public void testValidateJwtTokenEmptyString() {
        String token = "";

        boolean result = jwtTokenProvider.validateJwtToken(token);

        assertFalse(result);
    }

}