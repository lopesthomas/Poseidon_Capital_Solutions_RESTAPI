package com.nnk.springboot.config;

import java.io.IOException;
import java.util.Set;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Configuration class for Spring Security settings.
 * This class sets up security filters, password encoding, and authentication manager beans.
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * Configures the HTTP security filter chain.
     * Defines the authorization rules, login/logout behavior, and exception handling.
     *
     * @param http the HttpSecurity object used to customize web-based security
     * @return the configured SecurityFilterChain
     * @throws Exception in case of any configuration error
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .successHandler(customAuthenticationSuccessHandler())
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            )
            .exceptionHandling(exception -> exception
                .accessDeniedPage("/403")
            );

        return http.build();
    }

    /**
     * Custom authentication success handler that redirects users based on their roles.
     * Redirects to different pages for ADMIN and USER roles.
     *
     * @return an AuthenticationSuccessHandler instance
     */
    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request,
                                                HttpServletResponse response,
                                                Authentication authentication) throws IOException, ServletException {

                Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

                if (roles.contains("ROLE_ADMIN")) {
                    response.sendRedirect("/user/list");
                } else if (roles.contains("ROLE_USER")) {
                    response.sendRedirect("/bidList/list");
                } else {
                    response.sendRedirect("/");
                }
            }
        };
    }

    /**
     * Bean definition for the password encoder.
     * Uses BCrypt hashing algorithm.
     *
     * @return a BCryptPasswordEncoder instance
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean definition for the AuthenticationManager.
     *
     * @param config the AuthenticationConfiguration provided by Spring
     * @return the configured AuthenticationManager
     * @throws Exception in case of error retrieving the AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}