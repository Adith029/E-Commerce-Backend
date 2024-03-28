package com.ecommercebackend.onlineshoping_backend.api.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class WebSecurityConfig {

   public WebSecurityConfig(JWTRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }
private JWTRequestFilter jwtRequestFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http.csrf().disable().cors().disable();
        http.addFilterBefore(jwtRequestFilter, AuthorizationFilter.class);
        http.authorizeHttpRequests()
        .requestMatchers("/products","/auth/login","/auth/reg","/auth/verify").permitAll()
        .anyRequest().authenticated();
        return http.build();
    }
}
