package com.example.dreamwishes.service;

import com.example.dreamwishes.entity.Items;
import com.example.dreamwishes.entity.Users;
import com.example.dreamwishes.entity.Wishlist;
import com.example.dreamwishes.model.Wishes;
import com.example.dreamwishes.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public List<Wishes> getWishesByUsername(String username) {
        // Fetch the user's wishlist by username
        List<Wishlist> wishlists = wishlistRepository.findByUserUsername(username);

        // Extract wishes from items in wishlists
        List<Wishes> wishes = new ArrayList<>();
        for (Wishlist wishlist : wishlists) {
            Items item = wishlist.getItem();
            if (item != null) {
                wishes.addAll(item.getWishes());
            }
        }

        return wishes;
    }


    // Original methods from your old WishlistService class

    public List<Wishlist> getAllWishlists() {
        return wishlistRepository.findAll();
    }

    public Wishlist getWishlistById(Long id) {
        return wishlistRepository.findById(id).orElse(null);
    }

    public Wishlist createWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    public void deleteWishlist(Long id) {
        wishlistRepository.deleteById(id);
    }

    // Method to get all wishes from all wishlists
    public Collection<? extends Wishes> getUserWishes() {
        // Get the username of the currently logged-in user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Fetch wishes by username
        return getWishesByUsername(username);
    }

    // New methods from your updated WishlistService class

    public List<Wishlist> getAllItemsInWishlistForUser(Users user) {
        return wishlistRepository.findByUser(user);
    }

    public Optional<Wishlist> getItemInWishlistById(Long wishlistId) {
        return wishlistRepository.findById(wishlistId);
    }

    public Wishlist addItemToWishlist(Users user, Items item, int priority) {
        Wishlist wishlistItem = new Wishlist();
        wishlistItem.setUser(user);
        wishlistItem.setItem(item);
        wishlistItem.setPriority(priority);
        return wishlistRepository.save(wishlistItem);
    }

    public void removeItemFromWishlist(Long wishlistId) {
        wishlistRepository.deleteById(wishlistId);
    }

    public List<Wishes> getWishesByWishlistId(Long wishlistId) {
        Optional<Wishlist> wishlistOptional = wishlistRepository.findById(wishlistId);
        if (wishlistOptional.isPresent()) {
            Wishlist wishlist = wishlistOptional.get();
            Items item = wishlist.getItem();
            // Assuming there's a method to retrieve wishes from an item
            return item.getWishes();
        } else {
            return Collections.emptyList(); // Wishlist not found, return an empty list
        }
    }


}
