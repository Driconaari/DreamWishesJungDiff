    package com.example.dreamwishes.entity;

    import com.example.dreamwishes.model.Wishes;

    import javax.persistence.*;
    import java.util.List;

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

        @Column(name = "Available")
        private boolean available;

        @Column(name = "Category")
        private String category;

        // Getters and Setters
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

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        @OneToMany(mappedBy = "item")
        private List<Wishes> wishes;

        public List<Wishes> getWishes() {
            return wishes;
        }

        public void setWishes(List<Wishes> wishes) {
            this.wishes = wishes;
        }
    }
