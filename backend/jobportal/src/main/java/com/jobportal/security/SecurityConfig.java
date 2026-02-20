package com.jobportal.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.
UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> {})
            .authorizeHttpRequests(auth -> auth

                // PUBLIC
                .requestMatchers("/api/auth/**").permitAll()

                // ADMIN ONLY
                .requestMatchers("/api/admin/**").hasRole("ADMIN")

                // EMPLOYER ONLY
                .requestMatchers("/api/employer/**").hasRole("EMPLOYER")

                // USER
                .requestMatchers("/api/applications/**").hasAnyRole("USER","EMPLOYER")

                .anyRequest().authenticated()
            );

        return http.build();
    }
}
