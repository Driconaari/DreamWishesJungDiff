package com.example.dreamwishes.controller;

//import ch.qos.logback.core.model.Model;

import com.example.dreamwishes.dto.WishlistDTO;
import com.example.dreamwishes.entity.Wishlist;
import com.example.dreamwishes.model.WishesModel;
import com.example.dreamwishes.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

// WishlistController.java
@Controller
@RequestMapping("/wishlists")
public class WishlistController {

    private final WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }


    @GetMapping("/{wishlistId}/wishes")
public ResponseEntity<List<WishesModel>> getWishesByWishlistId(@PathVariable Long wishlistId) {
    List<WishesModel> wishes = wishlistService.getWishesByWishlistId(wishlistId);
    if (wishes.isEmpty()) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(wishes);
}

    @GetMapping("/wishlist")
    public String getWishlistPage(Model model) {
        WishlistDTO wishlistDTO = new WishlistDTO();
        // Populate wishlistDTO with data from your service or database
        // For example:
        // wishlistDTO.setItems(wishlistService.getAllItems());

        model.addAttribute("wishlistDTO", wishlistDTO);
        return "wishlist";
    }



    @PostMapping
    public ResponseEntity<Wishlist> createWishlist(@RequestBody Wishlist wishlist) {
        Wishlist createdWishlist = wishlistService.createWishlist(wishlist);
        if (createdWishlist != null) {
            return ResponseEntity
                    .created(URI.create("/api/wishlists/" + createdWishlist.getWishlistId()))
                    .body(createdWishlist);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Wishlist>> getAllWishlists() {
        List<Wishlist> wishlists = wishlistService.getAllWishlists();
        return ResponseEntity.ok(wishlists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wishlist> getWishlistById(@PathVariable Long id) {
        Wishlist wishlist = wishlistService.getWishlistById(id);
        if (wishlist == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(wishlist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWishlist(@PathVariable Long id) {
        wishlistService.deleteWishlist(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Wishlist> addWish(@RequestBody Wishlist wishlist) {
        Wishlist createdWishlist = wishlistService.createWishlist(wishlist);
        if (createdWishlist != null) {
            return ResponseEntity
                    .created(URI.create("/api/wishlists/" + createdWishlist.getWishlistId()))
                    .body(createdWishlist);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}