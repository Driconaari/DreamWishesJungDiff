package com.example.dreamwishes.dto;


import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;

public class ItemsDTO {
    private SingularAttribute<AbstractPersistable, Serializable> id;
    private String name;
    private String description;
    private double price;

    // Getters and setters
    // You can also include additional constructors and methods as needed

    public SingularAttribute<AbstractPersistable, Serializable> getId() {
        return id;
    }

    public void setId(SingularAttribute<AbstractPersistable, Serializable> id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
