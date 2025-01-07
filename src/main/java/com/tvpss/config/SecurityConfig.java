package com.tvpss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/auth/login", "/auth/register").permitAll()
                .antMatchers("/dashboard","/content/library", "/content/upload", "/content/manage", "/content/manage/*/approve", "/content/manage/*/reject").permitAll() // Allow all access for development
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .maximumSessions(1) // Allow one session per user
                .maxSessionsPreventsLogin(false); // Allow new logins to invalidate old sessions
        return http.build();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
