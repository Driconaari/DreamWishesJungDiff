package com.example.dreamwishes.service;

import com.example.dreamwishes.entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final Users user;

    public CustomUserDetails(Users user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // You can return the authorities granted to the user here
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // You can return whether the user's account is expired here
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // You can return whether the user's account is locked here
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // You can return whether the user's credentials (password) are expired here
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    // Add any additional methods you need to access additional user information
    public Long getId() {
        return user.getId();
    }
}
