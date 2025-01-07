package com.tvpss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/auth/login", "/auth/register").permitAll()
                .antMatchers("/content/library", "/content/upload", "/content/manage").permitAll() // Allow all access for development
                .anyRequest().authenticated();
        return http.build();
    }
}
