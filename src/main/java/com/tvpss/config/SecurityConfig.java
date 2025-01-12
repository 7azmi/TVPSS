package com.tvpss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()//.cors().and()
                .authorizeRequests()
                .antMatchers("/", "/auth/login", "/auth/register", "/icons/404-icon.svg", "/403").permitAll()
                .antMatchers("/auth/logout").authenticated()
                .antMatchers("/dashboard").hasAnyAuthority("ADMIN", "SCHOOL_REP", "STUDENT") // Allow all roles to access the dashboard
                .antMatchers("/content/upload").hasAuthority("SCHOOL_REP") // Only SCHOOL_REP can upload
                .antMatchers("/content/manage", "/content/manage/*").hasAuthority("ADMIN") // Only ADMIN can manage content
                .antMatchers("/content/library").authenticated() // Any authenticated user can view the content library
                .antMatchers("/profile/equipment/update").hasAuthority("SCHOOL_REP")
                .antMatchers("profile/schools").hasAnyAuthority("ADMIN", "SCHOOL_REP")
                .anyRequest().authenticated() // Restrict all other endpoints
                .and()
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler()) // Use custom handler
                .and()
                .logout()
                .logoutUrl("/auth/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .sessionManagement()
                .maximumSessions(1) // Limit to 1 session per user
                .expiredUrl("/"); // Redirect if session expires
        return http.build();
    }

    @Bean
    public AccessDeniedHandler customAccessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.sendRedirect("/403");
        };
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}


