package com.example.dreamwishes.service;

import com.example.dreamwishes.dto.WishlistDTO;
import com.example.dreamwishes.entity.Items;
import com.example.dreamwishes.entity.Users;
import com.example.dreamwishes.entity.Wishlist;
import com.example.dreamwishes.model.WishesModel;
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



    public List<WishesModel> getWishesByWishlistId(Long wishlistId) {
        Optional<Wishlist> wishlistOptional = wishlistRepository.findById(wishlistId);
        if (wishlistOptional.isPresent()) {
            Wishlist wishlist = wishlistOptional.get();
            Items item = wishlist.getItem();
            java.util.Date date = wishlist.getTimestamp();
            java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(date.getTime());

            WishesModel wishModel = new WishesModel(wishlist.getWishlistId(), (Long) wishlist.getUser().getId(), item.getItemId(), wishlist.getPriority(), sqlTimestamp);
            wishModel.setItemName(item.getItemName());
            wishModel.setDescription(item.getDescription());
            wishModel.setPrice(item.getPrice());
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
            wishlists = wishlistRepository.findByUser_UserID(user.getUserID());
        }

        // Create a new WishlistDTO object
        WishlistDTO wishlistDTO = new WishlistDTO();

        // Convert each Wishlist to a WishesModel and add it to the WishlistDTO
        if (wishlists != null) {
            for (Wishlist wishlist : wishlists) {
                Items item = wishlist.getItem();
                if (item != null) {
                    // Convert each Wishlist to a WishesModel
                    Date date = wishlist.getTimestamp();
                    Timestamp sqlTimestamp = new Timestamp(date.getTime());

                    WishesModel wishModel = new WishesModel(wishlist.getId(), wishlist.getUser().getId(), item.getItemId(), wishlist.getPriority(), sqlTimestamp);
                    wishModel.setItemName(item.getItemName());
                    wishModel.setDescription(item.getDescription());
                    wishModel.setPrice(item.getPrice());

                    // Add the WishesModel to the WishlistDTO
                    wishlistDTO.addWish(wishModel);
                }
            }
        }

        return wishlistDTO;
    }
}

