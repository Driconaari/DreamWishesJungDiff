package com.example.dreamwishes.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Table(name = "Users")
@Component
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Long userID;

    @Column(name = "Username", unique = true)
    private String username;

    @Column(name = "Email", unique = true)
    private String email;

    @Column(name = "Password")
    private String password;

    // Constructors, getters, and setters
    // You can generate these using your IDE or manually write them

    // Constructors
    public Users() {
    }

    public Users(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getters and setters
    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

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

    public Object getId() {
        return userID;
    }

/*
    @OneToMany(mappedBy = "user")
    private List<WishesEntity> wishes;

    public List<WishesEntity> getWishes() {
        return wishes;
    }

    public void setWishes(List<WishesEntity> wishes) {
        this.wishes = wishes;
    }

 */
}

