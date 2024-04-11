package com.example.dreamwishes.entity;

import jakarta.persistence.*;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Entity
@Table(name = "Items")
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ItemID")
    private Long itemId;

    @Column(name = "ItemName")
    private String itemName;

    @Column(name = "Description")
    private String description;

    @Column(name = "Price")
    private double price;
    private String name;


    @Column (name = "Available")
    private boolean available;

    // Constructors, getters, and setters
    // You can generate these using your IDE or manually write them

    // Constructors
    public Items() {
    }

    public Items(String itemName, String description, double price) {
        this.itemName = itemName;
        this.description = description;
        this.price = price;
    }

    // Getters and setters
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SingularAttribute<AbstractPersistable, Serializable> getId() {
        return id;
    }

    public String getName() {
        // Replace this with the logic to retrieve the name from your DTO object
        return name;
    }

}
