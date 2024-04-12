package com.example.dreamwishes;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll() // Allow access to homepage without authentication
                .anyRequest().authenticated() // Require authentication for all other requests
                .and()
                .formLogin() // Enable form-based authentication
                .loginPage("/login") // Specify the custom login page URL if you have one
                .permitAll(); // Allow access to the login page without authentication
    }
}
