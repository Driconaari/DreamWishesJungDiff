package com.example.dreamwishes.dto;


import com.example.dreamwishes.model.WishlistModel;

import java.math.BigDecimal;

public class WishlistDTO {
    private Long id;
    private Long userId;
    private String itemName;
    private String description;
    private Double price;
    private Boolean available;
    private String category;
    private Integer priority;

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return BigDecimal.valueOf(price);
    }

    public String getCategory() {
        return category;
    }

    public Integer getPriority() {
        return priority;
    }


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
    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
