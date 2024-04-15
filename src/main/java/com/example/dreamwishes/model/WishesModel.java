package com.example.dreamwishes.model;

import java.sql.Timestamp;

public class WishesModel {

    private Long wishlistId;
    private Long userId;
    private Long itemId;
    private Integer priority;
    private Timestamp timestamp;
    private String itemName;
    private String description;
    private double price;



   public WishesModel(Long wishlistId, Object userId, Long itemId, Integer priority, java.sql.Timestamp timestamp) {
    this.wishlistId = wishlistId;
    if (userId instanceof Long) {
        this.userId = (Long) userId;
    } else {
        throw new IllegalArgumentException("userId must be of type Long");
    }
    this.itemId = itemId;
    this.priority = priority;
    this.timestamp = timestamp;
}

    // Getters and setters
    public Long getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Long wishlistId) {
        this.wishlistId = wishlistId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

public Timestamp getTimestamp() {
    return timestamp;
}

public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
}
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }
}
