package com.example.dreamwishes.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class WishlistModel {

    private Long wishlistId;
    private Long userId;
    private String itemName;
    private String description;
    private BigDecimal price;
    private Boolean available;
    private String category;
    private Integer priority;
    private Timestamp timestamp;

    public WishlistModel(Long wishlistId, Long userId, String itemName, String description, BigDecimal price, Boolean available, String category, Integer priority, Timestamp timestamp) {
        this.wishlistId = wishlistId;
        this.userId = userId;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.available = available;
        this.category = category;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
}