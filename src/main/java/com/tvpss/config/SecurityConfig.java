package com.tvpss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Disable CSRF for simplicity in development
                .authorizeRequests()
                .antMatchers("/", "/auth/login", "/auth/register").permitAll() // Allow public access to login and registration
                .anyRequest().authenticated(); // Restrict all other endpoints
        return http.build();
    }
}
