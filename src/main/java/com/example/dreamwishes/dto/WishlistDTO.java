package com.example.dreamwishes.dto;


import java.util.List;

public class WishlistDTO {
    private Long id;
    private Long userId;
    private List<ItemDTO> items;

    // Getters and setters
    // You can also include additional constructors and methods as needed

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

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }
}
