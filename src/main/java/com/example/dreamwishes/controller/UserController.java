package com.example.dreamwishes.controller;

import com.example.dreamwishes.entity.Users;
import com.example.dreamwishes.entity.Wishlist;
import com.example.dreamwishes.service.UserService;
import com.example.dreamwishes.service.WishlistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final WishlistService wishlistService;

    @Autowired
    public UserController(UserService userService, WishlistService wishlistService) {
        this.userService = userService;
        this.wishlistService = wishlistService;
    }


    @GetMapping("/login")
    public String showLoginPage(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model) {

        if (userService.login(username, password)) {
            session.setAttribute("loggedIn", true);
            session.setAttribute("userId", userService.getUserId(username).orElse(null));
            return "redirect:/"; // Redirect to index page after successful login
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }


    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Users user) {
        // set other fields as needed
        logger.info("Attempting to register user: {}", user.getUsername());
        userService.createUser(user);
        logger.info("Registered user: {}", user.getUsername());
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String showUserProfile(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            Users currentUser = userService.getUserById(userId).orElse(null);
            model.addAttribute("user", currentUser);
            return "profile";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute Users updatedUser) {
        userService.updateUser(updatedUser.getUserID(), updatedUser);
        return "redirect:/profile";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "dashboard";
    }

    @GetMapping("/search")
    public String searchUser(@RequestParam("username") String username, Model model) {
        Optional<Users> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            List<Wishlist> wishlists = wishlistService.getWishlistsByUserId(user.get().getUserId());
            model.addAttribute("user", user.get());
            model.addAttribute("wishlists", wishlists);
            return "profile"; // return the name of the Thymeleaf template for user profile
        } else {
            model.addAttribute("error", "User not found");
            return "search"; // return the name of the Thymeleaf template for search
        }
    }
}


