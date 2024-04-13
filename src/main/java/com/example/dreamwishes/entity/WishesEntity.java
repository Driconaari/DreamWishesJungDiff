package com.example.dreamwishes.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "wishes")
public class WishesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WishID")
    private Long wishId;

    @ManyToOne
    @JoinColumn(name = "ItemID", referencedColumnName = "ItemID")
    private Items item;

    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    private Users user;

    @Column(name = "Priority")
    private Integer priority;

    @Column(name = "Timestamp", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp timestamp;

    public WishesEntity(Long wishId, Items item, Users user, Integer priority, Timestamp timestamp) {
        this.wishId = wishId;
        this.item = item;
        this.user = user;
        this.priority = priority;
        this.timestamp = timestamp;
    }

    // Constructors
    public WishesEntity() {
    }

    public WishesEntity(Items item, Users user, Integer priority) {
        this.item = item;
        this.user = user;
        this.priority = priority;
    }

    // Getters and setters
    public Long getWishId() {
        return wishId;
    }

    public void setWishId(Long wishId) {
        this.wishId = wishId;
    }

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
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
