package com.example.dreamwishes.dto;


import com.example.dreamwishes.model.WishesModel;

import java.util.List;

public class WishlistDTO {
    private Long id;
    private Long userId;
    private List<ItemsDTO> items;
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

    public List<ItemsDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemsDTO> items) {
        this.items = items;
    }

    public void addWish(WishesModel wishModel) {
    }




}
