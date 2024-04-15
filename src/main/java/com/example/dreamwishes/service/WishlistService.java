package com.example.dreamwishes.service;

import com.example.dreamwishes.entity.Items;
import com.example.dreamwishes.entity.Users;
import com.example.dreamwishes.entity.Wishlist;
import com.example.dreamwishes.model.WishesModel;
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


   public List<WishesModel> getWishesByUsername(String username) {
    // Fetch the user's wishlist by username
    List<Wishlist> wishlists = wishlistRepository.findByUserUsername(username);
    // Extract wishes from items in wishlists
    List<WishesModel> wishes = new ArrayList<>();
    for (Wishlist wishlist : wishlists) {
        Items item = wishlist.getItem();
        if (item != null) {
            // Convert each Wishlist to a WishesModel
            WishesModel wishModel = new WishesModel(wishlist.getId(), wishlist.getUser().getId(), item.getItemId(), wishlist.getPriority(), wishlist.getTimestamp());
            wishModel.setItemName(item.getItemName());
            wishModel.setDescription(item.getDescription());
            wishModel.setPrice(item.getPrice());
            // Add the WishesModel to the list
            wishes.add(wishModel);
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
    public Collection<? extends WishesModel> getUserWishes() {
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

   public List<WishesModel> getWishesByWishlistId(Long wishlistId) {
    Optional<Wishlist> wishlistOptional = wishlistRepository.findById(wishlistId);
    if (wishlistOptional.isPresent()) {
        Wishlist wishlist = wishlistOptional.get();
        Items item = wishlist.getItem();
        WishesModel wishModel = new WishesModel(wishlist.getWishlistId(), (Long) wishlist.getUser().getId(), item.getItemId(), wishlist.getPriority(), wishlist.getTimestamp());
        wishModel.setItemName(item.getItemName());
        wishModel.setDescription(item.getDescription());
        wishModel.setPrice(item.getPrice());
        return Collections.singletonList(wishModel);
    } else {
        return Collections.emptyList();
    }
}
}
