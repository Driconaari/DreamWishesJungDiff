package com.example.dreamwishes.repository;

import com.example.dreamwishes.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    // You can add custom query methods as needed
}
