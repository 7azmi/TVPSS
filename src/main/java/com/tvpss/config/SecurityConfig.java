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
        http.csrf().disable()//.cors().and()
                .authorizeRequests()
                .antMatchers("/", "/auth/login", "/auth/register").permitAll()
                .antMatchers("/auth/logout").authenticated()
                .antMatchers("/dashboard").hasAnyAuthority("ADMIN", "SCHOOL_REP", "STUDENT") // Allow all roles to access the dashboard
                .antMatchers("/content/upload").hasAuthority("SCHOOL_REP") // Only SCHOOL_REP can upload
                .antMatchers("/content/manage", "/content/manage/*").hasAuthority("ADMIN") // Only ADMIN can manage content
                .antMatchers("/content/library").authenticated() // Any authenticated user can view the content library
                .antMatchers("/equipment/update").hasAuthority("SCHOOL_REP")
                .antMatchers("equipment/schools").hasAnyAuthority("ADMIN", "SCHOOL_REP")
                .anyRequest().authenticated(); // Restrict all other endpoints
        return http.build();
    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                // Publicly accessible endpoints
//                .antMatchers("/", "/auth/login", "/auth/register", "/auth/dashboard").permitAll()
//
//                // Dashboard accessible to all authenticated roles
//                .antMatchers("/dashboard").hasAnyAuthority("ADMIN", "SCHOOL_REP", "STUDENT")
//
//                // Role-specific access
//                .antMatchers("/content/library").hasAnyAuthority("ADMIN", "SCHOOL_REP", "STUDENT")
//                .antMatchers("/content/upload").hasAuthority("SCHOOL_REP")
//                .antMatchers("/content/manage", "/content/manage/*/approve", "/content/manage/*/reject").hasAuthority("ADMIN")
//
//                // All other endpoints require authentication
//                .anyRequest().authenticated()
//                .and()
//                .sessionManagement()
//                .maximumSessions(1)
//                .maxSessionsPreventsLogin(false);
//        return http.build();
//    }


    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}


