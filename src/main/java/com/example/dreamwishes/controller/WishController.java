package com.example.dreamwishes.controller;

import com.example.dreamwishes.entity.Users;
import com.example.dreamwishes.entity.Wishlist;
import com.example.dreamwishes.repository.WishlistRepository;
import com.example.dreamwishes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/wishes/add")
public class WishController {

    @Autowired
    private UserService userService;
    @Autowired
    private WishlistRepository wishlistRepository;

    @GetMapping("/add")
    public String getAddWishPage() {
        return "addwish";
    }

    @PostMapping("/add")
    public String addWish(@ModelAttribute Wishlist newWish, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            Users currentUser = userService.getUserById(userId).orElse(null);
            newWish.setUser(currentUser); // Set the user of the new wish
            wishlistRepository.save(newWish); // Save the new wish
            return "redirect:/wishes"; // Redirect back to the wishes page
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping
    public String showUserWishes(HttpSession session, Model model) {
        if (session.getAttribute("loggedIn") != null && (Boolean) session.getAttribute("loggedIn")) {
            Long userId = (Long) session.getAttribute("userId");
            Users currentUser = userService.getUserById(userId).orElse(null);
            List<Wishlist> userWishes = wishlistRepository.findByUser(currentUser);
            model.addAttribute("user", currentUser);
            model.addAttribute("wishes", userWishes);
            return "wishes";
        } else {
            return "redirect:/login";
        }
    }
}