package com.example.dreamwishes.controller;

//import ch.qos.logback.core.model.Model;

import com.example.dreamwishes.dto.WishlistDTO;
import com.example.dreamwishes.entity.Users;
import com.example.dreamwishes.entity.Wishlist;
import com.example.dreamwishes.model.WishlistModel;
import com.example.dreamwishes.repository.WishlistRepository;
import com.example.dreamwishes.service.UserService;
import com.example.dreamwishes.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;

    }

    @Autowired
    private UserService userService;
    @Autowired
    private WishlistRepository wishlistRepository;

    @GetMapping("/{wishlistId}/wishes")
    public ResponseEntity<List<WishlistModel>> getWishesByWishlistId(@PathVariable Long wishlistId) {
        List<WishlistModel> wishes = wishlistService.getWishesByWishlistId(wishlistId);
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

    @GetMapping("/add")
    public String getAddWishPage(Model model) {
        model.addAttribute("newWish", new Wishlist()); // Add an empty Wishlist object to the model
        return "addwish"; // Return the view name
    }

    @PostMapping("/add/session")
    public String addWish(@ModelAttribute Wishlist newWish, HttpSession session) {
        Long userID = (Long) session.getAttribute("userID");
        if (userID != null) {
            Users currentUser = userService.getUserById(userID).orElse(null);
            if (currentUser != null) {
                newWish.setUser(currentUser); // Set the user of the new wish
                newWish.setTimestamp(new Timestamp(new Date().getTime())); // Set the current date and time as the timestamp
                wishlistRepository.save(newWish); // Save the new wish
                return "redirect:/wishes"; // Redirect back to the wishes page
            }
        }
        return "redirect:/login";
    }

    @Controller
    public class WishesController {

        @Autowired
        private UserService userService;
        @Autowired
        private WishlistRepository wishlistRepository;

        @GetMapping("/wishes")
        public String showUserWishes(HttpSession session, Model model) {
            Boolean loggedIn = Boolean.valueOf(String.valueOf(session.getAttribute("loggedIn")));
            if (loggedIn != null && loggedIn) {
                Long userID = (Long) session.getAttribute("userId");
                Users currentUser = userService.getUserById(userID).orElse(null);
                if (currentUser != null) {
                    List<Wishlist> userWishes = wishlistRepository.findByUser(currentUser);
                    if (userWishes != null && !userWishes.isEmpty()) {
                        // Add the first wishlist to the model
                        model.addAttribute("wishlist", userWishes.get(0));
                    } else {
                        // Handle the case where there are no wishlists
                        // This could be by creating a new wishlist, showing an error message, etc.
                    }
                    model.addAttribute("user", currentUser);
                    return "wishes";
                }
            }
            return "redirect:/login";
        }
    }
@GetMapping("/user/{userID}")
public ResponseEntity<List<Wishlist>> getWishlistsByUserId(@PathVariable Long userID) {
    List<Wishlist> wishlists = wishlistService.getWishlistsByUserId(userID);
    if (wishlists == null || wishlists.isEmpty()) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(wishlists);
}
}
