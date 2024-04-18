package com.example.dreamwishes.entity;


import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Users")
@Component
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Long userID;

    @Column(name = "Username", unique = true)
    @NotNull
    @NotEmpty
    private String username;

    @Column(name = "Email", unique = true)
    @NotNull
    @NotEmpty
    private String email;


    @Column(name = "Password")
    @NotNull
    @NotEmpty
    private String password;

    // Constructors
    public Users() {
    }

    public Users(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getters and setters


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserID() {
        return userID;
    }
    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public boolean isEnabled() {
        return this.enabled();
    }

    private boolean enabled() {
        return true;
    }

    public Long getId() {
        return this.userID;
    }

    public Long getUserId() {
        return this.userID;
    }
}