package com.bookstore.controller;

import com.bookstore.dto.UserDto;
import com.bookstore.entity.Book;
import com.bookstore.service.BookService;
import com.bookstore.service.RecommendationService;
import com.bookstore.service.UserService; // Make sure to import this
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private UserService userService; // Injected to handle registration
    
    @GetMapping("/")
    public String home(Model model) {
        List<Book> featuredBooks = bookService.getFeaturedBooks();
        model.addAttribute("featuredBooks", featuredBooks);
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            List<Book> popularBooks = recommendationService.getPopularBooks();
            model.addAttribute("recommendedBooks", popularBooks);
        } else {
            List<Book> popularBooks = recommendationService.getPopularBooks();
            model.addAttribute("popularBooks", popularBooks);
        }
        return "index";
    }
    
    @GetMapping("/login")
    public String loginPage() {
        // No logic needed here. 
        // Spring Security intercepts the POST, so we only need to show the page on GET.
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    // ✅ ADDED: This handles the "Submit" button on the Register page
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userDto") UserDto userDto, Model model) {
        try {
            // Assuming your UserService has a save method
            userService.save(userDto); 
            return "redirect:/login?success"; // Redirect to login page
        } catch (Exception e) {
            // If email already exists or other error
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            return "register"; // Stay on register page and show error
        }
    }
}