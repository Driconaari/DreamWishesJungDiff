package com.example.dreamwishes.service;

import com.example.dreamwishes.dto.WishlistDTO;
import com.example.dreamwishes.entity.Wishlist;
import com.example.dreamwishes.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public Wishlist addToWishlist(Wishlist wishlistItem) {
        return wishlistRepository.save(wishlistItem);
    }

    public List<Wishlist> getWishlistItems() {
        return wishlistRepository.findAll();
    }
    public void removeFromWishlist(Long itemId) {
        wishlistRepository.deleteById(itemId);
    }


    public WishlistDTO createWishlist(WishlistDTO wishlistDTO) {
        return wishlistDTO;
    }

    public Long getWishlistById(Long wishlistId) {
        return  wishlistId;
    }

    // Other methods as needed for wishlist operations
}
