package com.example.dreamwishes.controller;

import com.example.dreamwishes.dto.WishlistDTO;
import com.example.dreamwishes.service.UserService;
import com.example.dreamwishes.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
//hmm
@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private WishlistService wishlistService;


    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            // Redirect to home page for not logged in users
            return "index";
        }
        WishlistDTO wishlistDTO = wishlistService.getWishlistForUser(userId);
        model.addAttribute("wishlistDTO", wishlistDTO);
        return "index";
    }
}