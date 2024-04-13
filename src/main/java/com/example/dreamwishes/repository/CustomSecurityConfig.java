package com.example.dreamwishes.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
public class CustomSecurityConfig implements SecurityConfigurer<DefaultSecurityFilterChain, HttpSecurity> {

    @Override
    public void init(HttpSecurity http) throws Exception {
        // Custom security configuration initialization
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // Custom security configuration
    }
}
