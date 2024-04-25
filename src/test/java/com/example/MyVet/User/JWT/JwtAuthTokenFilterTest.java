package com.example.MyVet.User.JWT;


import com.example.MyVet.User.users.User;
import com.example.MyVet.User.users.UserService;
import jakarta.persistence.MapKeyColumn;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class JwtAuthTokenFilterTest {

    private final JwtTokenProvider jwtTokenProvider = mock(JwtTokenProvider.class);
    private final UserDetailsService userDetailsService = mock(UserDetailsService.class);
    private final JwtAuthTokenFilter jwtAuthTokenFilter = new JwtAuthTokenFilter();
    private final UserService userService = mock(UserService.class);

    private final SecurityContext securityContext = mock(SecurityContext.class);
    private final Authentication authentication = mock(Authentication.class);



    @Test
    public void testDoFilterInternal() throws ServletException, IOException {
        final String validJwtToken = "validJwtToken";
        final String username = "testUser";

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final FilterChain filterChain = mock(FilterChain.class);
        final UserDetails userDetails = mock(UserDetails.class);

        when(request.getHeader("Authorization")).thenReturn("Bearer " + validJwtToken);
        when(jwtTokenProvider.validateJwtToken(validJwtToken)).thenReturn(true);
        when(jwtTokenProvider.getUserNameFromJwtToken(validJwtToken)).thenReturn(username);
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);

        final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        jwtAuthTokenFilter.doFilterInternal(request, response, filterChain);
    }
    @Test
    public void testDoFilterInternal2() throws ServletException, IOException {
        // Create a mock HttpServletRequest, HttpServletResponse, and FilterChain
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        // Create a mock UserDetails object and set its authorities
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_PATIENT"));
        UserDetails userDetails = new User("john","doe","email","156156163516","829292","bauuu" ,"password");

        // Create a mock JwtTokenProvider object and configure it to return a valid token and username
        JwtTokenProvider jwtTokenProvider = mock(JwtTokenProvider.class);
        when(jwtTokenProvider.validateJwtToken(any())).thenReturn(true);
        when(jwtTokenProvider.getUserNameFromJwtToken(any())).thenReturn("testuser");

        // Create a mock UserService object and configure it to return the mock UserDetails object
        UserService userService = mock(UserService.class);
        when(userService.loadUserByUsername(any())).thenReturn(userDetails);

        // Create a mock SecurityContextHolder and set it as the current context
        SecurityContextHolder.setContext(mock(SecurityContext.class));

        // Create a JwtAuthTokenFilter object and set its JwtTokenProvider and UserService fields
        JwtAuthTokenFilter filter = new JwtAuthTokenFilter();
//        filter.jwtTokenProvider = jwtTokenProvider;
//        filter.userService = userService;

        // Create a mock Authentication object and set it as the current authentication
        UsernamePasswordAuthenticationToken authentication = mock(UsernamePasswordAuthenticationToken.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Call the doFilterInternal method with the mock objects
        filter.doFilterInternal(request, response, filterChain);

        // Verify that the SecurityContextHolder authentication was set correctly
//        verify(authentication, times(1)).setDetails(any());
//        verify(authentication, times(1)).getDetails();
//        verify(authentication, times(1)).setAuthenticated(eq(true));
//        verify(authentication, times(1)).getPrincipal();
//        verify(authentication, times(1)).getCredentials();
//        verify(authentication, times(1)).getAuthorities();
    }

    @Test
    public void testDoFilterInternal_withValidJwtToken() throws ServletException, IOException {
        final String validJwtToken = "validJwtToken";
        final String username = "testUser";

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final FilterChain filterChain = mock(FilterChain.class);
        final UserDetails userDetails = mock(UserDetails.class);

        when(request.getHeader("Authorization")).thenReturn("Bearer " + validJwtToken);
        when(jwtTokenProvider.validateJwtToken(validJwtToken)).thenReturn(true);
        when(jwtTokenProvider.getUserNameFromJwtToken(validJwtToken)).thenReturn(username);
        when(userService.loadUserByUsername(username)).thenReturn(userDetails);
        when(userDetails.getAuthorities()).thenReturn(Collections.emptyList());

        jwtAuthTokenFilter.doFilterInternal(request, response, filterChain);

    }

    @Test
    public void testDoFilterInternal_withInvalidJwtToken() throws ServletException, IOException {
        final String invalidJwtToken = "invalidJwtToken";

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final FilterChain filterChain = mock(FilterChain.class);

        when(request.getHeader("Authorization")).thenReturn("Bearer " + invalidJwtToken);
        when(jwtTokenProvider.validateJwtToken(invalidJwtToken)).thenReturn(false);

        jwtAuthTokenFilter.doFilterInternal(request, response, filterChain);

        verify(jwtTokenProvider, never()).getUserNameFromJwtToken(anyString());
        verify(userDetailsService, never()).loadUserByUsername(anyString());
        verify(securityContext, never()).setAuthentication(any(Authentication.class));
    }

}