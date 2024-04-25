package com.example.MyVet.User.config;

import com.example.MyVet.User.JWT.*;

import com.example.MyVet.User.users.UserService;
import com.example.MyVet.User.users.role.Role;
import io.swagger.models.HttpMethod;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Map;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
@OpenAPIDefinition(servers = {@Server(url = "http://localhost:8081")})
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class WebSecurityConfig {

    private final UserService userService;

    private final JwtAuthEntryPoint unauthorizedHandler;

    private static final String[] SWAGGER_WHITELIST = {
            "/api-docs/**", "/swagger-ui/**", "/swagger-ui.html"
    };

    private static final Map<HttpMethod, String[]> WHITELIST = Map.of(
            HttpMethod.GET, new String[]{
                    "/api/v1/drugs",
                    "/api/v1/drugs/{id}",
                    "/api/v1/users/{id}",
                    "/api/v1/drugs/{id}",
                    "/api/v1/drugs",
                    "/api/v1/consumptions",
                    "/api/v1/consumptions/{id}",
                    "/api/v1/consumptions/{drugId}/{year}",
                    "/api/v1/consumptions/{drugId}/",
                    "/api/v1/appointments",
                    "/api/v1/appointments/{id}",
                    "/api/v1/appointments/{petOwnerId}/{year}/{month}",
                    "/api/v1/appointments/{petOwnerId}/{year}/{month}/{day}",
                    "/api/v1/appointments/{petOwnerId}/{year}/{month}/{day}/{hour}:{minute}",
                    "/api/v1/DrugStocks/{id}"
            },
            HttpMethod.PUT, new String[]{
                    "/api/v1/users/{id}"
            }
    );

    private static final Map<HttpMethod, String[]> PETOWNER_PERMISSIONS = Map.of(
            HttpMethod.GET, new String[]{
                    "/api/v1/users/{id}",
                    "/api/v1/pets/{id}",
                    "/api/v1/pets",
                    "/api/v1/petOwners/{id}",
                    "/api/v1/drugs",
                    "/api/v1/drugs/{id}",
                    "/api/v1/medicalHistories/{id}",
                    "/api/v1/appointments",
                    "/api/v1/appointments/{id}",
                    "/api/v1/appointments/{petOwnerId}/{year}/{month}",
                    "/api/v1/appointments/{petOwnerId}/{year}/{month}/{day}",
                    "/api/v1/appointments/{petOwnerId}/{year}/{month}/{day}/{hour}:{minute}"
            },
            HttpMethod.POST, new String[]{
                    "/api/v1/pets",
                    "/api/v1/appointments"
            },
            HttpMethod.PUT, new String[]{
                    "/api/v1/users/{id}",
                    "/api/v1/pets/{id}",
                    "/api/v1/petOwners/{id}",
                    "/api/v1/appointments/{id}",
            },
            HttpMethod.DELETE, new String[]{
                    "/api/v1/pets/{id}",
                    "/api/v1/appointments/{id}",
            }
    );

    private static final Map<HttpMethod, String[]> VET_PERMISSIONS = Map.of(
            HttpMethod.GET, new String[]{
                    "/api/v1/medicalHistories/{id}",
                    "/api/v1/medicalHistories",
            },
            HttpMethod.POST, new String[]{
                    "/api/v1/medicalHistories",
            },
            HttpMethod.PUT, new String[]{
                    "/api/v1/medicalHistories/{id}",
            },
            HttpMethod.DELETE, new String[]{
                    "/api/v1/medicalHistories/{id}",
            }
    );

    private static final Map<HttpMethod, String[]> STAFF_PERMISSIONS = Map.of(
            HttpMethod.GET, new String[]{
                    "/api/v1/users/{id}",
                    "/api/v1/drugs/{id}",
                    "/api/v1/drugs",
                    "/api/v1/consumptions",
                    "/api/v1/consumptions/{id}",
                    "/api/v1/consumptions/{drugId}/{year}",
                    "/api/v1/consumptions/{drugId}/",
                    "/api/v1/appointments",
                    "/api/v1/appointments/{id}",
                    "/api/v1/appointments/{petOwnerId}/{year}/{month}",
                    "/api/v1/appointments/{petOwnerId}/{year}/{month}/{day}",
                    "/api/v1/appointments/{petOwnerId}/{year}/{month}/{day}/{hour}:{minute}",
                    "/api/v1/DrugStocks/{id}"
            },
            HttpMethod.POST, new String[]{
                    "/api/v1/",
                    "/api/v1/drugs"
            },
            HttpMethod.PUT, new String[]{
                    "/api/v1/users/{id}",
                    "/api/v1/drugs"
            },
            HttpMethod.DELETE, new String[]{
                    "/api/v1/drugs/{id}",
            }
    );

    private static final Map<HttpMethod, String[]> ADMIN_PERMISSIONS = Map.of(
            HttpMethod.GET, new String[]{
                    "/api/v1/users",
                    "/api/v1/petOwners",
                    "/api/v1/medicalStaff/{id}",
                    "/api/v1/medicalStaff",
                    "/api/v1/drugSuppliers",
                    "/api/v1/drugSuppliers/{id}",
                    "/api/v1/cabinet",
                    "/api/v1/cabinet/{id}",
                    "/api/v1/DrugStocks",
            },
            HttpMethod.POST, new String[]{
                    "/api/v1/drugSuppliers/{id}",
                    "/api/v1/consumptions",
                    "/api/v1/cabinet",
            },
            HttpMethod.PUT, new String[]{
                    "/api/v1/medicalStaff/{id}",
                    "/api/v1/drugSuppliers/{id}",
                    "/api/v1/consumptions/{id}",
                    "/api/v1/cabinet/{id}",
            },
            HttpMethod.DELETE, new String[]{
                    "/api/v1/users/{id}",
                    "/api/v1/drugSuppliers/{id}",
                    "/api/v1/consumptions/{id}",
                    "/api/v1/cabinet/{id}",
            }
    );

    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig)
            throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("content-type", "Authorization" , "Accept"));
        configuration.setAllowedMethods(
                Arrays.asList(
                        HttpMethod.GET.toString(),
                        HttpMethod.POST.toString(),
                        HttpMethod.PUT.toString(),
                        HttpMethod.DELETE.toString()));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.exceptionHandling().authenticationEntryPoint(unauthorizedHandler);

        http.authorizeHttpRequests()
                .requestMatchers("/error")
                .permitAll()
                //SWAGGER
                .requestMatchers(SWAGGER_WHITELIST)
                .permitAll()
                //AUTH
                .requestMatchers("/api/v1/auth/**")
                .permitAll()
                //WHITELIST
                .requestMatchers(org.springframework.http.HttpMethod.GET ,WHITELIST.get(HttpMethod.GET))
                .permitAll()
                .requestMatchers(org.springframework.http.HttpMethod.PUT ,WHITELIST.get(HttpMethod.PUT))
                .permitAll()
                //PET OWNER
                .requestMatchers(org.springframework.http.HttpMethod.GET ,PETOWNER_PERMISSIONS.get(HttpMethod.GET))
                .hasAuthority(Role.ERole.ROLE_PetOwner.name())
                .requestMatchers(org.springframework.http.HttpMethod.POST,PETOWNER_PERMISSIONS.get(HttpMethod.POST))
                .hasAuthority(Role.ERole.ROLE_PetOwner.name())
                .requestMatchers(org.springframework.http.HttpMethod.PUT,PETOWNER_PERMISSIONS.get(HttpMethod.PUT))
                .hasAuthority(Role.ERole.ROLE_PetOwner.name())
                .requestMatchers(org.springframework.http.HttpMethod.DELETE,PETOWNER_PERMISSIONS.get(HttpMethod.DELETE))
                .hasAuthority(Role.ERole.ROLE_PetOwner.name())
                //VET
                .requestMatchers(org.springframework.http.HttpMethod.GET,VET_PERMISSIONS.get(HttpMethod.GET))
                .hasAuthority(Role.ERole.ROLE_VET.name())
                .requestMatchers(org.springframework.http.HttpMethod.POST,VET_PERMISSIONS.get(HttpMethod.POST))
                .hasAuthority(Role.ERole.ROLE_VET.name())
                .requestMatchers(org.springframework.http.HttpMethod.PUT,VET_PERMISSIONS.get(HttpMethod.PUT))
                .hasAuthority(Role.ERole.ROLE_VET.name())
                .requestMatchers(org.springframework.http.HttpMethod.DELETE,VET_PERMISSIONS.get(HttpMethod.DELETE))
                .hasAuthority(Role.ERole.ROLE_VET.name())
                //MEDICAL STAFF
                .requestMatchers(org.springframework.http.HttpMethod.GET, STAFF_PERMISSIONS.get(HttpMethod.GET))
                .hasAnyAuthority(Role.ERole.ROLE_VET.name(),Role.ERole.ROLE_Assistant.name())
                .requestMatchers(org.springframework.http.HttpMethod.POST, STAFF_PERMISSIONS.get(HttpMethod.POST))
                .hasAnyAuthority(Role.ERole.ROLE_VET.name(),Role.ERole.ROLE_Assistant.name())
                .requestMatchers(org.springframework.http.HttpMethod.PUT, STAFF_PERMISSIONS.get(HttpMethod.PUT))
                .hasAnyAuthority(Role.ERole.ROLE_VET.name(),Role.ERole.ROLE_Assistant.name())
                .requestMatchers(org.springframework.http.HttpMethod.DELETE, STAFF_PERMISSIONS.get(HttpMethod.DELETE))
                .hasAnyAuthority(Role.ERole.ROLE_VET.name(),Role.ERole.ROLE_Assistant.name())
                //ADMIN
                .requestMatchers(org.springframework.http.HttpMethod.GET, ADMIN_PERMISSIONS.get(HttpMethod.GET))
                .hasAuthority(Role.ERole.ROLE_ADMIN.name())
                .requestMatchers(org.springframework.http.HttpMethod.POST, ADMIN_PERMISSIONS.get(HttpMethod.POST))
                .hasAuthority(Role.ERole.ROLE_ADMIN.name())
                .requestMatchers(org.springframework.http.HttpMethod.PUT, ADMIN_PERMISSIONS.get(HttpMethod.PUT))
                .hasAuthority(Role.ERole.ROLE_ADMIN.name())
                .requestMatchers(org.springframework.http.HttpMethod.DELETE, ADMIN_PERMISSIONS.get(HttpMethod.DELETE))
                .hasAuthority(Role.ERole.ROLE_ADMIN.name())
                //Authenticated
                .anyRequest()
                .authenticated();

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(
                authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
