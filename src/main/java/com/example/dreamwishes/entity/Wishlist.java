package com.example.dreamwishes.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

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

    @ManyToOne
    @JoinColumn(name = "ItemID", referencedColumnName = "ItemID")
    private Items item;

    @Column(name = "Priority")
    private Integer priority;

    @Column(name = "Timestamp", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.sql.Timestamp timestamp;

    public Wishlist() {
    }

    public Wishlist(Users user, Items item, Integer priority) {
        this.user = user;
        this.item = item;
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

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
public Long getId() {
        return this.wishlistId;
    }

}