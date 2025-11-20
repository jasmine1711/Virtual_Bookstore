package com.bookstore.controller;

import com.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.security.Principal;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    // 1. Add to Cart
    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long bookId) {
        cartService.addBook(bookId);
        return "redirect:/cart";
    }

    // 2. View Cart Page
    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cartItems", cartService.getItems());
        model.addAttribute("total", cartService.getTotal());
        return "cart";
    }

    // 3. Remove Item
    @GetMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable Long id) {
        cartService.removeBook(id);
        return "redirect:/cart";
    }

    // 4. Checkout (Place Order)
    @PostMapping("/checkout")
    public String checkout(Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }
       BigDecimal finalTotal = cartService.getTotal();
        
        cartService.checkout(principal.getName());
        
        model.addAttribute("total", finalTotal);
        return "order-success";
    }
}