package com.example.dreamwishes.dto;


import com.example.dreamwishes.model.WishlistModel;

public class WishlistDTO {
    private Long id;
    private Long userId;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void addWish(WishlistModel wishModel) {
    }




}
