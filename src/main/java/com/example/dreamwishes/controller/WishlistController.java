package com.example.dreamwishes.controller;

import com.example.dreamwishes.dto.WishlistDTO;
import com.example.dreamwishes.service.WishlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

// WishlistController.java
@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping
        public ResponseEntity<WishlistDTO> createWishlist(@RequestBody WishlistDTO wishlistDTO) {
        WishlistDTO createdWishlist = wishlistService.createWishlist(wishlistDTO);
        return ResponseEntity.created(URI.create("/api/wishlists/" + createdWishlist.getId())).body(createdWishlist);
    }

    @GetMapping("/{wishlistId}")
    public ResponseEntity<Long> getWishlist(@PathVariable Long wishlistId) {
        Long wishlistDTO = wishlistService.getWishlistById(wishlistId);
        return ResponseEntity.ok(wishlistDTO);
    }

    // Add other CRUD endpoints as needed
}
