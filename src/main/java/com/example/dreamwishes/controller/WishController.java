package com.example.dreamwishes.controller;

import com.example.dreamwishes.entity.Wishlist;
import com.example.dreamwishes.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wishes/add")
public class WishController {


    @GetMapping
    public String getAddWishPage() {
        return "addwish";
    }

    private final WishlistService wishlistService;

    public WishController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("/add")
    public String addWish(@ModelAttribute Wishlist wishlist) {
        Wishlist createdWishlist = wishlistService.createWishlist(wishlist);
        if (createdWishlist != null) {
            return "redirect:/wishes"; // Redirect to the wishes page after successful creation
        } else {
            return "error"; // Return an error page if the wish could not be created
        }
    }

    //old one
    /*@PostMapping("/add")
    public ResponseEntity<Wishlist> addWish(@RequestBody Wishlist wishlist) {
        Wishlist createdWishlist = wishlistService.createWishlist(wishlist);
        if (createdWishlist != null) {
            return ResponseEntity
                    .created(URI.create("/api/wishlists/" + createdWishlist.getWishlistId()))
                    .body(createdWishlist);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

     */
}
