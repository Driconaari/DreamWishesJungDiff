package com.example.dreamwishes.dto;

public class UserDTO {
    private Long id;
    private String username;
    private String email;

    // Getters and setters
    // You can also include additional constructors and methods as needed

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


}
