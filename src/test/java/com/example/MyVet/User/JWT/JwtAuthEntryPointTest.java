package com.example.MyVet.User.JWT;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.AuthenticationException;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.web.AuthenticationEntryPoint;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JwtAuthEntryPointTest {

    private final AuthenticationEntryPoint authEntryPoint = new JwtAuthEntryPoint();

    @Test
    public void testCommence() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        AuthenticationException authException = new AuthenticationException("Unauthorized") {};
        authEntryPoint.commence(request, response, authException);
        assertEquals(401, response.getStatus());
        assertEquals("Error: Unauthorized", response.getErrorMessage());
    }
}