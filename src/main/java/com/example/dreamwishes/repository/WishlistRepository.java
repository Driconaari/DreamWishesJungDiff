package com.example.dreamwishes.repository;

import com.example.dreamwishes.entity.Users;
import com.example.dreamwishes.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    List<Wishlist> findByUser(Users user);

    List<Wishlist> findByUserUsername(String username);
}

