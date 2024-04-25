package com.example.MyVet.User.config;

import com.example.MyVet.User.JWT.JwtAuthEntryPoint;
import com.example.MyVet.User.JWT.JwtAuthTokenFilter;
import com.example.MyVet.User.users.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.bson.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(WebSecurityConfig.class)
@ContextConfiguration(classes = {WebSecurityConfig.class})
class WebSecurityConfigTest {
    @MockBean
    private UserService userService;

    @MockBean
    private JwtAuthEntryPoint unauthorizedHandler;

    @MockBean
    private JwtAuthTokenFilter jwtAuthTokenFilter;

    @InjectMocks
    private WebSecurityConfig webSecurityConfig;
    @MockBean
    private AuthenticationManager authenticationManager;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;
//    @Test
//    @WithMockUser(username = "testuser", roles = "USER")
//    void testSecurityFilterChain() throws Exception {
//        mockMvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(springSecurity())
//                .build();
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/auth/v1/signin")
//                        .with(SecurityMockMvcRequestPostProcessors.user("testuser").roles("USER")))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }


    @Test
    void securityFilterChainTest() throws Exception {
        WebSecurityConfig webSecurityConfig = new WebSecurityConfig(userService, unauthorizedHandler);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(webSecurityConfig)
                .addFilters(webSecurityConfig.authenticationJwtTokenFilter())
                .build();

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().is4xxClientError()); //de schimbat sa astepte isOK 200
    }
    @Test
    void authenticationJwtTokenFilterTest() {
        assertNotNull(webSecurityConfig.authenticationJwtTokenFilter());
    }

    @Test
    void authenticationProviderTest() {
        assertNotNull(webSecurityConfig.authenticationProvider());
    }

    @Test
    void passwordEncoderTest() {
        assertNotNull(webSecurityConfig.passwordEncoder());
    }
    @Test
    public void testAuthenticationManager() throws Exception {
        AuthenticationConfiguration authConfig = mock(AuthenticationConfiguration.class);
        when(authConfig.getAuthenticationManager()).thenReturn(authenticationManager);

        AuthenticationManager result = webSecurityConfig.authenticationManager(authConfig);

        assertEquals(authenticationManager, result);
    }

    @Test
    void daoAuthenticationProviderTest() {
        assertNotNull(webSecurityConfig.authenticationProvider());
        assertTrue(webSecurityConfig.authenticationProvider() instanceof DaoAuthenticationProvider);
    }

    @Test
    void testCorsConfigurationSource() {
        // Create the WebSecurityConfig instance
        WebSecurityConfig webSecurityConfig = new WebSecurityConfig(userService, unauthorizedHandler);

        // Create a MockHttpServletRequest
        MockHttpServletRequest request = new MockHttpServletRequest();

        // Set request properties if needed
        request.setMethod("GET");
        request.addHeader("Origin", "http://localhost:4200");

        // Invoke the corsConfigurationSource() method
        CorsConfigurationSource corsConfigurationSource = webSecurityConfig.corsConfigurationSource();

        // Get the CorsConfiguration based on the request
        CorsConfiguration configuration = ((UrlBasedCorsConfigurationSource) corsConfigurationSource)
                .getCorsConfiguration(request);

        // Assert the result is not null
        assertNotNull(configuration);

        // Assert the allowed origins
        List<String> expectedOrigins = Arrays.asList("*");
        assertEquals(expectedOrigins, configuration.getAllowedOrigins());

        // Assert the allowed headers
        List<String> expectedHeaders = Arrays.asList("content-type");
        assertEquals(expectedHeaders, configuration.getAllowedHeaders());

        // Assert the allowed methods
        List<String> expectedMethods = Arrays.asList(
                HttpMethod.GET.toString(),
                HttpMethod.POST.toString(),
                HttpMethod.PUT.toString(),
                HttpMethod.DELETE.toString());
        assertEquals(expectedMethods, configuration.getAllowedMethods());
    }
//    @Test
//    void testSecurityFilterChain() throws Exception {
//        WebSecurityConfig webSecurityConfig = new WebSecurityConfig(mock(UserService.class), mock(JwtAuthEntryPoint.class));
//        HttpSecurity httpSecurity = mock(HttpSecurity.class);
//        when(httpSecurity.cors()).thenReturn(mock(CorsConfigurer.class));
//        when(httpSecurity.csrf());
//        when(httpSecurity.sessionManagement()).thenReturn(mock(SessionManagementConfigurer.class));
//        when(httpSecurity.exceptionHandling()).thenReturn(mock(ExceptionHandlingConfigurer.class));
//        when(httpSecurity.addFilterBefore(any(), eq(UsernamePasswordAuthenticationFilter.class)))
//                .thenReturn(httpSecurity);
//
//        webSecurityConfig.securityFilterChain(httpSecurity);
//
//        // Verify method calls
//        verify(httpSecurity).cors();
//        verify(httpSecurity).csrf();
//        verify(httpSecurity).sessionManagement();
//        verify(httpSecurity).exceptionHandling();
//        verify(httpSecurity).addFilterBefore(any(), eq(UsernamePasswordAuthenticationFilter.class));
//        verify(httpSecurity).authorizeRequests();
//        verify(httpSecurity).build();
//    }
}
