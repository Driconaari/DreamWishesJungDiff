package com.example.dreamwishes.repository;

import com.example.dreamwishes.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Items, Long> {

    // Custom query methods can be defined here based on your requirements
    // For example:

    // Find an item by its name
    Items findByItemName(String name);

    // Find items by their category
    List<Items> findByCategory(String category);

    // Find items by their price less than a certain value
    List<Items> findByPriceLessThan(double price);

    // Find items by their price greater than a certain value
    List<Items> findByPriceGreaterThan(double price);

    // Find items by their price between a range
    List<Items> findByPriceBetween(double minPrice, double maxPrice);

    // Find items by their availability status
    List<Items> findByAvailable(boolean available);

    // Find items by their name or description containing a keyword
    List<Items> findByItemNameContainingOrDescriptionContaining(String nameKeyword, String descriptionKeyword);

    // You can define more query methods as needed for your application
}
