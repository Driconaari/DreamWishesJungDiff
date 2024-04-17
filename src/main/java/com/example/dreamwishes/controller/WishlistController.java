package com.example.dreamwishes.controller;

//import ch.qos.logback.core.model.Model;

import com.example.dreamwishes.entity.Users;
import com.example.dreamwishes.entity.Wishlist;
import com.example.dreamwishes.model.WishlistModel;
import com.example.dreamwishes.repository.WishlistRepository;
import com.example.dreamwishes.service.CustomUserDetails;
import com.example.dreamwishes.service.UserService;
import com.example.dreamwishes.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.net.URI;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


// WishlistController.java
@Controller
@RequestMapping("/api/wishlists")
public class WishlistController {

    private final WishlistService wishlistService;
    private final UserService userService;
    private final WishlistRepository wishlistRepository;

    @Autowired
    public WishlistController(WishlistService wishlistService, UserService userService, WishlistRepository wishlistRepository) {
        this.wishlistService = wishlistService;
        this.userService = userService;
        this.wishlistRepository = wishlistRepository;
    }

    @GetMapping("/{wishlistId}/wishes")
    public ResponseEntity<List<WishlistModel>> getWishesByWishlistId(@PathVariable Long wishlistId) {
        List<WishlistModel> wishes = wishlistService.getWishesByWishlistId(wishlistId);
        if (wishes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(wishes);
    }

    @GetMapping("/user")
    public String getWishlistsByUser(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            Long userId = userDetails.getId();
            List<Wishlist> wishlists = wishlistService.getWishlistsByUserId(userId);
            model.addAttribute("wishlists", wishlists);
        }
        return "wishlist"; // return the name of the Thymeleaf template
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

    @GetMapping("/all")
    public ResponseEntity<List<Wishlist>> getAllWishlists() {
        List<Wishlist> wishlists = wishlistRepository.findAll();
        if (wishlists.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
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

    @GetMapping("/add")
    public String getAddWishPage(Model model) {
        model.addAttribute("newWish", new Wishlist()); // Add an empty Wishlist object to the model
        return "addwish"; // Return the view name
    }


    @PostMapping("/add/session")
    public String addWish(@ModelAttribute Wishlist newWish, HttpSession session) {
        Long userID = (Long) session.getAttribute("userId");
        System.out.println("UserID from session: " + userID); // Log the userID
        if (userID != null) {
            Users currentUser = userService.getUserById(userID).orElse(null);
            System.out.println("User from userService: " + currentUser); // Log the User object
            if (currentUser != null) {
                newWish.setUser(currentUser);
                newWish.setTimestamp(new Timestamp(new Date().getTime()));
                Wishlist savedWish = wishlistRepository.save(newWish);
                System.out.println("Saved wish: " + savedWish); // Log the saved wish
                if (savedWish != null) {
                    return "redirect:/";
                }
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Wishlist>> getWishlistsByUserId(@PathVariable Long userId) {
        List<Wishlist> wishlists = wishlistService.getWishlistsByUserId(userId);
        if (wishlists.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(wishlists);
    }

}
//hmmm
