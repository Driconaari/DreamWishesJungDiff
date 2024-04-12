package com.example.dreamwishes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers("/", "/home").permitAll() // Allow access to homepage without authentication
                                .anyRequest().authenticated() // Require authentication for all other requests
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login") // Specify the custom login page URL if you have one
                                .permitAll() // Allow access to the login page without authentication
                );
    }
}
