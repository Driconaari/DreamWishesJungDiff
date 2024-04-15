package com.example.dreamwishes.controller;

import com.example.dreamwishes.dto.UserDTO;
import com.example.dreamwishes.entity.Users;
import com.example.dreamwishes.entity.Wishlist;
import com.example.dreamwishes.repository.WishlistRepository;
import com.example.dreamwishes.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private WishlistRepository wishlistRepository;

    @GetMapping("/login")
    public String showLoginPage() {
        // Logic to show the login page
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
@GetMapping("/wishes")
public String showUserWishes(HttpSession session, Model model) {
    Long userId = (Long) session.getAttribute("userId");
    if (userId != null) {
        Users currentUser = userService.getUserById(userId).orElse(null);
        List<Wishlist> userWishes = wishlistRepository.findByUser(currentUser); // Assuming you have a method in your repository to get wishes by user
        model.addAttribute("user", currentUser);
        model.addAttribute("wishes", userWishes);
        return "wishes"; // Return the wishes view
    } else {
        return "redirect:/login";
    }
}
    @PostMapping("/wishes/add")
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


    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserDTO userDTO) {
        Users user = new Users();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
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
}


