package com.example.dreamwishes.repository;

import com.example.dreamwishes.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {



    // Find a wishlist by user ID
    Wishlist findByUserId(Long userId);

    // Find wishlists by user's username
    List<Wishlist> findByUserUsername(String username);

    // Find wishlists by user's email
    List<Wishlist> findByUserEmail(String email);

    // Find wishlists by user's name containing a keyword
    List<Wishlist> findByUserNameContaining(String keyword);

    // Find wishlists by user's city
    List<Wishlist> findByUserCity(String city);

    // Find wishlists by user's country
    List<Wishlist> findByUserCountry(String country);

    // You can define more query methods as needed for your application
}

