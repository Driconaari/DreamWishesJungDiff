package com.example.dreamwishes.service;

import com.example.dreamwishes.dto.WishlistDTO;
import com.example.dreamwishes.entity.Users;
import com.example.dreamwishes.entity.Wishlist;
import com.example.dreamwishes.model.WishlistModel;
import com.example.dreamwishes.repository.UserRepository;
import com.example.dreamwishes.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository, UserRepository userRepository) {
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
    }

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

    public List<WishlistModel> getWishesByWishlistId(Long wishlistId) {
        Optional<Wishlist> wishlistOptional = wishlistRepository.findById(wishlistId);
        if (wishlistOptional.isPresent()) {
            Wishlist wishlist = wishlistOptional.get();
            Date date = wishlist.getTimestamp();
            Timestamp sqlTimestamp = new Timestamp(date.getTime());

            WishlistModel wishModel = new WishlistModel(wishlist.getWishlistId(), (Long) wishlist.getUser().getUserID(), wishlist.getItem_name(), wishlist.getDescription(), wishlist.getPrice(), wishlist.getAvailable(), wishlist.getCategory(), wishlist.getPriority(), sqlTimestamp);

            return Collections.singletonList(wishModel);
        } else {
            return Collections.emptyList();
        }
    }

    public WishlistDTO getWishlistForUser(Long UserID) {
        // Fetch the user
        Users user = userRepository.findById(UserID).orElse(null);

        // Fetch the user's wishlist by user
        List<Wishlist> wishlists = null;
        if (user != null) {
            wishlists = wishlistRepository.findByUser(user);
        }

        // Create a new WishlistDTO object
        WishlistDTO wishlistDTO = new WishlistDTO();

        // Convert each Wishlist to a WishesModel and add it to the WishlistDTO
        if (wishlists != null) {
            for (Wishlist wishlist : wishlists) {
                // Convert each Wishlist to a WishesModel
                Date date = wishlist.getTimestamp();
                Timestamp sqlTimestamp = new Timestamp(date.getTime());

                WishlistModel wishModel = new WishlistModel(wishlist.getWishlistId(), (Long) wishlist.getUser().getUserID(), wishlist.getItem_name(), wishlist.getDescription(), wishlist.getPrice(), wishlist.getAvailable(), wishlist.getCategory(), wishlist.getPriority(), sqlTimestamp);

                // Add the WishesModel to the WishlistDTO
                wishlistDTO.addWish(wishModel);
            }
        }

        return wishlistDTO;
    }

    public void addWish(WishlistDTO wishlistDTO, Long userId) {
        // Fetch the user
        Users user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            // Create a new Wishlist object and set its fields based on the WishlistDTO
            Wishlist wishlist = new Wishlist();
            wishlist.setUser(user);
            wishlist.setItem_name(wishlistDTO.getItemName());
            wishlist.setDescription(wishlistDTO.getDescription());
            wishlist.setPrice(wishlistDTO.getPrice());
            wishlist.setPriority(wishlistDTO.getPriority());
            wishlist.setCategory(wishlistDTO.getCategory());

            // Set available to true by default when adding a new wish
            wishlist.setAvailable(true);

            // Save the new Wishlist object to the database
            wishlistRepository.save(wishlist);
        }
    }

    public List<Wishlist> getWishlistsByUserId(Long userID) {
        return wishlistRepository.findByUser_UserID(userID);
    }


    public Wishlist save(Wishlist existingWishlist) {
        return wishlistRepository.save(existingWishlist);
    }
}