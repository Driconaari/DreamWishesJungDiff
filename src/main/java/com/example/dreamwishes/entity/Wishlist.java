package com.example.dreamwishes.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "wishlist")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WishlistID")
    private Long wishlistId;

    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    private Users user;

    @Column(name = "Item_name")
    private String item_name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "Available")
    private Boolean available;

    @Column(name = "Category")
    private String category;

    @Column(name = "Priority")
    private Integer priority;

    @Column(name = "Timestamp", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp timestamp;

    public Wishlist() {
    }

    public Wishlist(Users user, String item_name, String description, BigDecimal price, Boolean available, String category, Integer priority) {
        this.user = user;
        this.item_name = item_name;
        this.description = description;
        this.price = price;
        this.available = available;
        this.category = category;
        this.priority = priority;
    }

    // Getters and setters
    public Long getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Long wishlistId) {
        this.wishlistId = wishlistId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
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