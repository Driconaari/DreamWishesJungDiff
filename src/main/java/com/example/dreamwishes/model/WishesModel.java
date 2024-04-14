package com.example.dreamwishes.model;

public class WishesModel {

    private Long wishlistId;
    private Long userId;
    private Long itemId;
    private Integer priority;
    private String timestamp; // You might want to use a proper data type for timestamp


    // Constructor with fields
    public WishesModel(Long wishlistId, Long userId, Long itemId, Integer priority, String timestamp) {
        this.wishlistId = wishlistId;
        this.userId = userId;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
